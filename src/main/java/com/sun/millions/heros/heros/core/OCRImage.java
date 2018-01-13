package com.sun.millions.heros.heros.core;

import com.sun.millions.heros.heros.core.model.Question;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * The Log.
     *
     * @since millions-heros-core-be 1.0.0
     */
    private static final Logger LOG = Logger.getLogger(OCRImage.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        File imageFile = new File("D:\\Projects\\millionHeros\\cutPictures\\IMG_1515510305331.PNG");
        Tesseract instance = new Tesseract();
        try {
            // 设置语言为 简体中文
            instance.setLanguage("chi_sim");
            String ocrText = instance.doOCR(imageFile);
            String[] tmpArray = ocrText.split("\\n");
            List<String> textList = new LinkedList<>();
            for (String text : tmpArray) {
                if (!StringUtils.isEmpty(text)) {
                    textList.add(text);
                }
            }
            Question question = new Question();
            List<String> answers = new ArrayList<>();
            StringBuilder holeTitle = new StringBuilder();
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                if (i < textList.size() - 3) {
                    // 带题号的 title , title 可能有多行
                    holeTitle.append(text.trim());
                } else {
                    answers.add(text.trim());
                }
            }
            if (!StringUtils.isEmpty(holeTitle)) {
                // 去掉题号  1. 2008年奥运会开幕式主会场是? --> 2008年奥运会开幕式主会场是?
                String realTile = holeTitle.toString().contains(".") ?
                        holeTitle.toString().split("[1-9]\\d*\\.")[1].trim() : holeTitle.toString();
                question.setTitle(realTile);
            } else {
                LOG.error("识别 title 出错 ,title 为空!");
                return;
            }
            question.setAnswers(answers);
            LOG.info("本题题目为: " + question.getTitle());
            LOG.info("本题答案为: " + question.getAnswers());
            LOG.info("OCR共耗时: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (TesseractException e) {
            LOG.error("OCR识别出错! " + e.getMessage());
        }
    }
}
