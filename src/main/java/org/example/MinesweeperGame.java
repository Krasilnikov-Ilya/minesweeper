package org.example;

import java.awt.*;
import java.util.ArrayList;

public class MinesweeperGame extends JFrameEngine {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";

    private boolean isGameStopped;
    private Cell[][] gameField = new Cell[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles = SIDE * SIDE;
    private int score;



}
