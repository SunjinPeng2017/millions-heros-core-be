package com.sun.millions.heros.heros.core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <b><code>CutImage</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/1/8 11:09
 * 40 150 670 690
 *
 * @author Sun Jinpeng
 * @version 1.0.0
 * @since millions-heros-core-be 1.0.0
 */
public class CutImage {

    private static BufferedImage cropImage(String filePath, int x, int y, int w, int h) {
        try {
            File file = new File(filePath);
            BufferedImage originalImage = ImageIO.read(file);
            return originalImage.getSubimage(x, y, w, h);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        BufferedImage bufferedImage = cropImage("D:\\Projects\\millionHeros\\screenshots\\IMG_0369.PNG", 40, 250, 670, 570);
        try {
            long millTimes = System.currentTimeMillis();
            ImageIO.write(bufferedImage, "PNG", new File("D:\\Projects\\millionHeros\\cutPictures\\IMG_" + millTimes + ".PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}