package com.lee.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int score;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image head;
    private Image tail;
    private Image apple;


    public GamePanel() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();

    }

    private void loadImages() {

        ImageIcon ihead = new ImageIcon("src/resources/head.png");
        head = ihead.getImage();

        ImageIcon itail = new ImageIcon("src/resources/dot.png");
        tail = itail.getImage();

        ImageIcon iapple = new ImageIcon("src/resources/apple.png");
        apple = iapple.getImage();
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(140, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                }else {
                    g.drawImage(tail, x[i], y[i], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }

    }

    private void checkApple() {
        //
        if (x[0] == apple_x && y[0] == apple_y) {
            dots++;
            score += 10;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {


        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (x[0] <= 0) {
            inGame = false;
        }

        if (y[0] <= 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }

        System.out.println("inGame status : " + inGame);

    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        String scores = "Scores : " + score;
        String retry = "Retry? press the enter";
        Font small = new Font("Helvetica", Font.BOLD, 14);

        FontMetrics metr = getFontMetrics(small);
        FontMetrics metr2 = getFontMetrics(small);
        FontMetrics metr3 = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.drawString(scores, (B_WIDTH - metr2.stringWidth(scores)) / 2, 170);
        g.drawString(retry, (B_WIDTH - metr3.stringWidth(retry)) / 2, 190);


    }



    // 사과 위치값만 설정
    private void locateApple() {

        int x = (int) (Math.random() * RAND_POS);
        apple_x = ((x * DOT_SIZE));

        int y = (int) (Math.random() * RAND_POS);
        apple_y = ((y * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() ==  KeyEvent.VK_RIGHT) {
            rightDirection = true;
            leftDirection = false;
            upDirection = false;
            downDirection = false;
        }

        if (e.getKeyCode() ==  KeyEvent.VK_LEFT) {
            rightDirection = false;
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        }

        if (e.getKeyCode() ==  KeyEvent.VK_UP) {
            rightDirection = false;
            leftDirection = false;
            upDirection = true;
            downDirection = false;
        }

        if (e.getKeyCode() ==  KeyEvent.VK_DOWN) {
            rightDirection = false;
            leftDirection = false;
            upDirection = false;
            downDirection = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!inGame) {
                inGame = true;

                rightDirection = true;
                leftDirection = false;
                upDirection = false;
                downDirection = false;

                initGame();
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}