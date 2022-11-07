package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MinesweeperGame extends JFrameEngine implements MouseInterface {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";

    private boolean isGameStopped;
    private final GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles = SIDE * SIDE;
    private int score;


    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
            return;
        }
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void openTile(int x, int y) {
        if (!gameField[y][x].isOpen && !gameField[y][x].isFlag && !isGameStopped) {
            if (gameField[y][x].isMine) {
                setCellColor(x, y, Color.RED);
                setCellValue(x, y, MINE);
                gameOver();
            }
            if (gameField[y][x].countMineNeighbors != 0 && !gameField[y][x].isMine && !gameField[y][x].isFlag) {
                setCellNumber(x, y, gameField[y][x].countMineNeighbors);
            }
            if (gameField[y][x].countMineNeighbors == 0 && !gameField[y][x].isMine && !gameField[y][x].isFlag) {
                setCellValue(x, y, "");
            }
            if (!gameField[y][x].isMine && !gameField[y][x].isFlag) {
                gameField[y][x].isOpen = true;
                score = score + 5;
                setScore(score);
                setCellColor(x, y, Color.GREEN);
                countClosedTiles--;
                if (!gameField[y][x].isMine && gameField[y][x].countMineNeighbors == 0) {
                    java.util.List<GameObject> neighbors = getNeighbors(gameField[y][x]);
                    for (GameObject e : neighbors) {
                        if (!e.isOpen) {
                            openTile(e.x, e.y);
                        }
                    }
                }
            }
            if (countClosedTiles == countMinesOnField && !isGameStopped) {
                win();
            }
        }
    }

    private void markTile(int x, int y) {
        if (!gameField[y][x].isOpen) {
            if (!gameField[y][x].isFlag) {
                if (countFlags > 0) {
                    gameField[y][x].isFlag = true;
                    setCellValue(x, y, FLAG);
                    setCellColor(x, y, Color.YELLOW);
                    countFlags--;
                }
            } else {
                gameField[y][x].isFlag = false;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
                countFlags++;
            }
        }
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");
            }
        }
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
        score = 0;

    }

    private void gameOver() {
        isGameStopped = true;
        //showMessageDialog(Color.RED, "You lose!", Color.RED, 1);
        //dispose();
    }

    private void win() {
        isGameStopped = true;
        //showMessageDialog(Color.RED, "You win!", Color.GREEN, 1);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        setScore(score);
        countMinesOnField = 0;
        createGame();
    }

    private java.util.List<GameObject> getNeighbors(GameObject gameObject) {
        java.util.List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (!gameField[y][x].isMine) {
                    java.util.List<GameObject> neighbors = getNeighbors(gameField[y][x]);
                    int count = 0;
                    for (GameObject e : neighbors) {
                        if (e.isMine) {
                            count++;
                        }
                    }
                    gameField[y][x].countMineNeighbors = count;
                }
            }
        }
    }
}

