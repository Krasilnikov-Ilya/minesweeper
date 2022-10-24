package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrameEngine engine = new JFrameEngine();
        engine.setScreenSize(10,10);

        engine.setCellColor(1,1, Color.BLACK);
        engine.setCellColor(2,1, Color.BLACK);
        engine.setCellColor(3,2, Color.BLACK);

    }
}
