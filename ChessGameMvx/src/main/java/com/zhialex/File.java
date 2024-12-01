package com.zhialex;

public enum File {
    A, B, C, D, E, F, G, H; // буквы координат по горизонтали


    public static File fromChar(char c) {
        try {
            return File.valueOf(String.valueOf(c).toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
