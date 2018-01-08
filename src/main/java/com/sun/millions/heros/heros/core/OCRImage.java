package com.sun.millions.heros.heros.core;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * <b><code>OCRImage</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/1/8 11:56
 *
 * @author Sun Jinpeng
 * @version 0.1.0
 * @since MillionsHeros 0.1.0
 */
public class OCRImage {


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        File imageFile = new File("D:\\image\\IMG_aaaa.PNG");
        Tesseract instance = new Tesseract();
        try {
            instance.setLanguage("chi_sim");
            String result = instance.doOCR(imageFile);
            System.out.println(result);
            System.out.println("共耗时:" + (System.currentTimeMillis() - startTime));
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
