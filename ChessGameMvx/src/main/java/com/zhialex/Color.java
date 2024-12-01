package com.zhialex;

//цвета фигур на доске
public enum Color {
    WHITE,
    BLACK;

    public Color opposite () {
        return this == WHITE ? BLACK : WHITE;
    }
}
