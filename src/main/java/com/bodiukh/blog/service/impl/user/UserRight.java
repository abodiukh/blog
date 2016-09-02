package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum UserRight {

    READ,
    WRITE,
    CREATE,
    DELETE;

    public static List<String> getValuesOf(EnumSet<UserRight> userRights) {
        ArrayList<String> values = new ArrayList<>();
        for (UserRight userRight : userRights) {
            values.add(userRight.name().toLowerCase());
        }
        return values;
    }

}
