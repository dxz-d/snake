package com.kuang.snake;

import javax.swing.*;

/***
 * @description  启动类
 * @author diaoxiuze
 * @date 2021/1/19 21:30
 * @param
 * @return
 */
public class StartGames {
    public static void main(String[] args) {
        // 绘制一个静态窗口 JFrame
        JFrame jFrame = new JFrame("狂神说java-贪吃蛇小游戏");
        // 设置界面的大小
        jFrame.setBounds(50, 50, 916, 730);
        // 窗口大小则不可以改变
        jFrame.setResizable(false);
        // 设置关闭事件，游戏可以关闭了
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 面板 Jpanel 可以加入到JFrame
        jFrame.add(new GamePanel());
        // 让窗口能够展现出来
        jFrame.setVisible(true);
    }
}
