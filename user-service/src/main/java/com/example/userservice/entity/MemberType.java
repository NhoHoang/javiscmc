package com.example.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum MemberType {
    SILVER("s"),
    GOLD("g"),
    PLATINUM("m");

    private String value;

    private static final Map<String, MemberType> typeMap = new HashMap<>();

    static {
        for (MemberType listType : MemberType.values()) {
            typeMap.put(listType.value, listType);
        }
    }

    public static MemberType of(String s) {
        return typeMap.get(s);
    }
}
