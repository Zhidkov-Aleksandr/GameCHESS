package com.zhialex;

import com.zhialex.board.Board;

//����� ��� ����������� ��������� ����: ���� - ��� - ���
public abstract class GameStateChecker {
    public abstract GameState check (Board board, Color color);
}
