package com.lee.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private static int B_WIDTH = 500;
    private static int B_HEIGHT = 500;
    private static int DOTS_SIZE = 10;

    private Image head;
    private Image tail;
    private Image apple;


    public GamePanel() {

        initBoard();

    }

    private void initBoard() {
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setBackground(Color.BLACK);

        initImages();
        initGame();
    }

    public void paintComponent(Graphics g) {

    }

    private void initImages() {


    }

    private void initGame() {

    }

    private void move() {

    }

    private void checkApple() {

    }

    private void checkCollision() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
