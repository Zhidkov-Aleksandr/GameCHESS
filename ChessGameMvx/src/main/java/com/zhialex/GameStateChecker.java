package com.zhialex;

import com.zhialex.board.Board;

//класс для определения состояния игры: Игра - Пат - Мат
public abstract class GameStateChecker {
    public abstract GameState check (Board board, Color color);
}
