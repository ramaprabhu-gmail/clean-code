package com.c.refactoring.menuexamples;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItems, Role[] roles) {

        if(roles == null)
            return;

        menuItems.stream()
                .forEach(menuItem -> setAccessForMenuItem(roles,menuItem));
    }

    private static void setAccessForMenuItem(Role[] roles, MenuItem menuItem) {
        if(hasAccess(roles, menuItem.getReadAccessRole())){
            menuItem.setAccess(Constants.READ);
            menuItem.setVisible(true);
        }

        if(hasAccess(roles, menuItem.getWriteAccessRole())){
            menuItem.setAccess(Constants.WRITE);
            menuItem.setVisible(true);
        }
    }

    private static boolean hasAccess(Role[] roles, String access) {
        return Arrays.stream(roles)
                .anyMatch(role -> role.getName().equals(access));
    }

}
