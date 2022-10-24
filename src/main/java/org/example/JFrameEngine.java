package org.example;

import javax.swing.*;
import java.awt.*;

public class JFrameEngine extends JFrame {

    private int height = 10;
    private int width = 10;
    private Container container = getContentPane();

    public void initialize() {
    }

    public void setScreenSize(int h, int w) {
        this.height = h;
        this.width = w;
        setBounds(h, w, h*70, w*70);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container.setLayout(new GridLayout(h,w, 0, 0));

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                container.add(new Cell(x,y));
            }
        }

        setVisible(true);
    }

    public void onMouseLeftClick(int x, int y) {
    }

    public void onMouseRightClick(int x, int y) {
    }

    public void setCellColor(int v, int h, Color color) {
        container.getComponent((v - 1) * width + h - 1).setBackground(color);
    }
}

class Cell extends JButton {
    private int y;
    private int x;
    private boolean isOpen;
    private boolean isFlag;
    private boolean isBomb;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public void setIsFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }
}

