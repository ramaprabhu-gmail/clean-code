package com.c.refactoring.menuexamples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MenuAccessTest {

    private static final Role MENU_A_READ_ROLE = new Role("MenuARead");
    private static final Role MENU_A_WRITE_ROLE = new Role("MenuAWrite");
    MenuItem MENU_ITEM_A = new MenuItem("A", "MenuARead", "MenuAWrite");

    MenuAccess menuAccess = new MenuAccess();

    @Test
    public void testSetAuthorizationsInEachMenus_userHasReadAndWriteRoles() {

        Role[] userRoles = {MENU_A_READ_ROLE, MENU_A_WRITE_ROLE};

        List<MenuItem> menuItems = Arrays.asList(MENU_ITEM_A);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertMenuItemWriteable(menuItems.get(0));

    }

    @Test
    public void testSetAuthorizationsInEachMenus_userHasOnlyReadRole() {

        Role[] userRoles = {MENU_A_READ_ROLE};

        List<MenuItem> menuItems = Arrays.asList(MENU_ITEM_A);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        assertMenuItemReadable(menuItems.get(0));

    }

    @Test
    public void testSetAuthorizationsInEachMenus_userHasOnlyWriteRole() {

        Role[] userRoles = {MENU_A_WRITE_ROLE};

        List<MenuItem> menuItems = Arrays.asList(MENU_ITEM_A);

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

		MenuItem actual = menuItems.get(0);
		assertMenuItemWriteable(actual);
	}

    private static void assertMenuItemWriteable(MenuItem actual) {
        assertEquals(Constants.WRITE, actual.getAccess());
        assertEquals(true, actual.isVisible());
    }

    private static void assertMenuItemReadable(MenuItem actual) {
        assertEquals(Constants.READ, actual.getAccess());
        assertEquals(true, actual.isVisible());
    }
}
