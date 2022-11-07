package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameEngine extends JFrame implements ActionListener, MouseInterface {

    Timer timer = new Timer(20, this);
    private int height = 10;
    private int width = 10;
    private final Container container = getContentPane();
    private final JPanel glass = (JPanel) getGlassPane();
    private int score;

    public int getRandomNumber(int num) {
        return (((int) (Math.random() * 100)) % num);
    }

    public void initialize() {
    }

    public void setScreenSize(int h, int w) {
        timer.start();
        this.height = h;
        this.width = w;
        this.score = 0;
        setBounds(h, w, height * 100, width * 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container.setLayout(new GridLayout(height, width, 0, 0));
        glass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickHandler(e);
            }
        });
        glass.setVisible(true);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                container.add(new GameObject(x, y, Color.DARK_GRAY));
            }
        }
        setVisible(true);
    }

    private void mouseClickHandler(MouseEvent e) {
        int button = e.getButton();
        if (button == 1) {
            onMouseLeftClick(e.getPoint().y / 100, e.getPoint().x / 100);
        } else if (button == 3) {
            onMouseRightClick(e.getPoint().y / 100, e.getPoint().x / 100);
        }
        //System.out.println(e.getButton() + " " + e.getX() + " " + e.getY());
    }

    public void onMouseLeftClick(int h, int v) {
    }

    public void onMouseRightClick(int h, int v) {
    }

    public void setCellColor(int v, int h, Color color) {
        if (height >= h && width >= v) {
            GameObject gameObject = (GameObject) container.getComponent((v) * width + h);
            gameObject.setBackground(color);
        }
    }

    public void setCellValue(int v, int h, String text) {
        if (height >= h && width >= v) {
            GameObject gameObject = (GameObject) container.getComponent((v) * width + h);
            gameObject.setText(text);
        }
    }

    public void setCellValueEx(int v, int h, Color color, String text) {
        GameObject gameObject = (GameObject) container.getComponent((v) * width + h);
        gameObject.setBackground(color);
        gameObject.setText(text);
    }

    public void setCellNumber(int v, int h, int num) {
        GameObject gameObject = (GameObject) container.getComponent((v) * width + h);
        gameObject.setText(String.valueOf(num));
    }

    public void setScore(int sc) {
        score = sc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
