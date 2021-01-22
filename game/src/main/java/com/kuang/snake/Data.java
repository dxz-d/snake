package com.kuang.snake;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/***
 * @description  存放外部资源数据
 * @author diaoxiuze
 * @date 2021/1/19 22:00
 * @param
 * @return
 */
public class Data {

    /**
     * 头部的图片  URL：定位图片的位置
     */
    public static URL headerURL = Data.class.getResource("/static/header.png");
    public static URL upUrl = Data.class.getResource("/static/up.png");
    public static URL downUrl = Data.class.getResource("/static/down.png");
    public static URL leftUrl = Data.class.getResource("/static/left.png");
    public static URL rightUrl = Data.class.getResource("/static/right.png");

    public static ImageIcon header = new ImageIcon(headerURL);
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);

    /**
     * 身体
     */
    public static URL bodyURL = Data.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    /**
     * 食物
     */
    public static URL foolUrl = Data.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foolUrl);
}
