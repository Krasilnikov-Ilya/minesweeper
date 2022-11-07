package org.example;


import javax.swing.*;
import java.awt.*;

public class GameObject extends JButton {
    public int y;
    public int x;
    public boolean isOpen;
    public boolean isFlag;
    public boolean isMine;
    public int countMineNeighbors;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }

    public GameObject(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        setColor(color);
    }


    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public void setIsFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }
}
