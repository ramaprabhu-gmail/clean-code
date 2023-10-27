package com.c.refactoring.lock;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MyUserLoginCheckerRefactoredTest {
    public static final boolean IS_FIRST_SCREEN_TRUE = true;
    public static final boolean IS_FIRST_SCREEN_FALSE = false;
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    public void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] existingLocks = new Object[] { "TEST_USER_ID_1", new Date() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(IS_FIRST_SCREEN_TRUE,
                new User("TEST_USER_ID_2"), Arrays.asList(new Object[][] { existingLocks }));
        assertReadAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Object[] existingLocks = new Object[] { "TEST_USER_ID", new Date() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(IS_FIRST_SCREEN_TRUE,
                new User("TEST_USER_ID"), Arrays.asList(new Object[][] { existingLocks }));
        assertWriteAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] existingLocks = new Object[] { "TEST_USER_ID", new Date() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(IS_FIRST_SCREEN_FALSE,
                new User("TEST_USER_ID"), Arrays.asList(new Object[][] { existingLocks }));
        assertWriteAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] existingLocks = new Object[] { "TEST_USER_ID_1", threeHoursBefore() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(IS_FIRST_SCREEN_TRUE,
                new User("TEST_USER_ID_2"), Arrays.asList(new Object[][] { existingLocks }));
        assertWriteAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] existingLocks = new Object[] { "TEST_USER_ID_1", threeHoursBefore() };
        Lock lock = userLoginChecker.isUserAllowedToLogin(IS_FIRST_SCREEN_FALSE,
                new User("TEST_USER_ID_2"), Arrays.asList(new Object[][] { existingLocks }));
        assertReadAccess(lock);
    }

    public Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - 3 * 60 * 60 * 1000);
    }

    private static void assertReadAccess(Lock lock) {
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    private static void assertWriteAccess(Lock lock) {
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

}
