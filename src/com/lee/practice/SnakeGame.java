package com.lee.practice;

import javax.swing.*;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        add(new GamePanel());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
