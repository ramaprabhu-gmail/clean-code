package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    public static final int MAX_LOCK_PERIOD_MILLIS = 60 * 60 * 1000;

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(boolean isFirstScreen, User user, List existingLocks) {

        if(existingLocks.size() == 0 || existingLocks.get(0) == null)
            return createWriteLock();

        Object[] existingLock = (Object[]) existingLocks.get(0);
        String userIdWithLock = (String) existingLock[0];
        Date lockTimestamp = (Date) existingLock[1];

        if(userIdWithLock == null)
            return createWriteLock();

        if (userIdWithLock.equalsIgnoreCase(user.getUserId())) {
            return createWriteLock();
        }

        long timeElapsedSinceLock = new Date().getTime() - lockTimestamp.getTime();
        if (isFirstScreen && timeElapsedSinceLock > MAX_LOCK_PERIOD_MILLIS) {
            return createWriteLock();
        }

        return createReadLockWithMessage(userIdWithLock);
    }

    private static Lock createReadLockWithMessage(String userIdWithLock) {
        String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@",
                userIdWithLock);
        Lock lock = new Lock();
        lock.setRead(true);
        lock.setLockReason(lockMsg);
        return lock;
    }

    private static Lock createWriteLock() {
        Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }
}