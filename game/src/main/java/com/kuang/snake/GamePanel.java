package com.kuang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/***
 * @description  面板
 * @author diaoxiuze
 * @date 2021/1/19 21:48
 * @param
 * @return
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    /**
     * 蛇的长度
      */
    int length;

    /**
     * 蛇的x坐标
     */
    int[] snakeX = new int[600];

    /**
     * 蛇的y坐标
     */
    int[] snakeY = new int[500];

    /**
     * R:右
     * L:左
     * U:上
     * D:下
     */
    String direction;

    /**
     * 游戏是否开始
     */
    boolean isStart = false;

    /**
     * 定时器
     */
    Timer timer = new Timer(80, this);

    /** 1.定义一个食物  */
    int foodX;
    int foodY;
    Random random = new Random();

    /** 死亡判断 */
    boolean isFail = false;

    /** 积分系统 */
    int score;

    /** 构造方法 */
    public GamePanel() {
        init();
        // 获取键盘的监听时间
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    /**
     * 初始化
     */
    public void init() {
        length = 3;
        // 头部坐标
        snakeX[0] = 100;
        snakeY[0] = 100;
        // 第一截身体坐标
        snakeX[1] = 75;
        snakeY[1] = 100;
        // 第二截身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;
        direction = "R";

        // 2.初始化食物
        foodX = 25 + 25 * random.nextInt(25);
        foodY = 75 + 25 * random.nextInt(25);

        // 初始化积分
        score = 0;
    }

    /**
     * 画板：画界面，画蛇
     * @param g 画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent(g);
        // 设置背景颜色
        this.setBackground(Color.orange);
        // 绘制头部的广告栏
        Data.header.paintIcon(this, g, 25, 11);
        // 绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        // 画一条静态的小蛇
        Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        if (direction.equals("R")){
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        }else if (direction.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        }else if (direction.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        }else {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 3.画食物的坐标
        Data.food.paintIcon(this, g, foodX, foodY);

        // 画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" +length, 750, 35);
        g.drawString("分数：" +score, 750, 55);

        // 游戏提示：是否开始
        if(isStart == false) {
            // 画一个文字，String
            // 设置画笔的颜色
            g.setColor(Color.WHITE);
            // 设置字体
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }

        // 失败提醒
        if (isFail) {
            // 画一个文字，String
            // 设置画笔的颜色
            g.setColor(Color.RED);
            // 设置字体
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏失败，按下空格重新开始", 200, 300);
        }
    }


    /**
     * 接受键盘的输入：监听
     * 键盘按下，未释放
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // 获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();
        // 如果按下的是空格键
        if (keyCode == KeyEvent.VK_SPACE) {
            // 失败，游戏再来一遍
            if (isFail) {
                isFail = false;
                // 重新初始化游戏
                init();
            }else {
                isStart =! isStart;
            }
            // 刷新界面
            repaint();
        }

        // 键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT) {
            direction = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = "R";
        }else if (keyCode == KeyEvent.VK_UP) {
            direction = "U";
        }else {
            direction = "D";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 如果游戏处于开始状态,并且游戏没有结束
        if (isStart && isFail == false) {
            // 右移
            for (int i = length-1; i > 0; i--) {
                // 除了脑袋，身体向前移动
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }

            // 通过控制方法让头部移动
            if (direction.equals("R")) {
                // 头部移动
                snakeX[0] = snakeX[0] + 25;
                // 边界判断
                if (snakeX[0] > 850) {
                    snakeX[0] =25;
                }
            }else if (direction.equals("L")) {
                // 头部移动
                snakeX[0] = snakeX[0] - 25;
                // 边界判断
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            }else if (direction.equals("U")) {
                // 头部移动
                snakeY[0] = snakeY[0] -25;
                // 边界判断
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            }else {
                // 头部判断
                snakeY[0] = snakeY[0] + 25;
                // 边界判断
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            // 4.如果小蛇的投河食物坐标重合
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                //重新生成食物
                foodX = 25 + 25 * random.nextInt(25);
                foodY = 75 + 25 * random.nextInt(20);

                // 长度+1
                length ++;

                // 分数+10
                score = score + 10;
            }

            // 结束判断
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }

            // 刷新界面
            repaint();
        }
        // 让时间动起来
        timer.start();
    }

    /**
     * 键盘按下，弹起：敲击
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 释放某个键
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
