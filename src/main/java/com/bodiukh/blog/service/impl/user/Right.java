package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Right {

    READ,
    WRITE,
    CREATE,
    DELETE,
    ADMINISTER;

    public static List<String> getValuesOf(EnumSet<Right> userRights) {
        ArrayList<String> values = new ArrayList<>();
        for (Right userRight : userRights) {
            values.add(userRight.name().toLowerCase());
        }
        return values;
    }

}
