package com.sun.millions.heros.heros.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
@Component
public class CutImage {

    /**
     * The Log.
     *
     * @since millions-heros-core-be 1.0.0
     */
    private static final Logger LOG = Logger.getLogger(CutImage.class);

    /**
     * The Question file path.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Value("${file.question.path}")
    private String questionFilePath;

    /**
     * Crop image buffered image.
     *
     * @param screenshotFile the file path
     * @param x              the x
     * @param y              the y
     * @param w              the w
     * @param h              the h
     * @return the buffered image
     * @since millions-heros-core-be 1.0.0
     */
    public File cropImage(File screenshotFile, int x, int y, int w, int h) {
        try {
            BufferedImage originalImage = ImageIO.read(screenshotFile);
            BufferedImage subImage = originalImage.getSubimage(x, y, w, h);
            long millTimes = System.currentTimeMillis();
            createFile(questionFilePath);
            String cutPicturePath = questionFilePath + "\\IMG_" + millTimes + ".PNG";
            File questionFile = new File(cutPicturePath);
            ImageIO.write(subImage, "PNG", questionFile);
            LOG.info("图片裁剪完成! filePath = " + cutPicturePath);
            return questionFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create file file.
     *
     * @param filePath the file path
     * @return the file
     * @since millions-heros-core-be 1.0.0
     */
    public File createFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.mkdir()) {
                LOG.info("创建文件成功: filePath = " + filePath);
            } else {
                LOG.info("创建文件失败: filePath = " + filePath);
            }
        }
        return file;
    }
}
