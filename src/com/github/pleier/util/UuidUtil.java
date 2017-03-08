package com.github.pleier.util;

import java.util.UUID;

/**
 * Created by PLEI on 2/10/2017.
 */
public class UuidUtil {
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
    public static void main(String[] args) {
        System.out.println(get32UUID());
    }
}
