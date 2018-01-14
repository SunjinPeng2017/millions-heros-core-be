package com.sun.millions.heros.heros.core;

import com.sun.millions.heros.heros.core.model.Question;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

/**
 * <b><code>Application</code></b>
 * <p>
 * 程序入口
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/9/12 15:17
 *
 * @author Sun Jinpeng
 * @version 1.0.0
 * @since millions-heros-core-be 1.0.0
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    /**
     * The Log.
     *
     * @since millions-heros-core-be 1.0.0
     */
    private static final Logger LOG = Logger.getLogger(Application.class);

    /**
     * The Monitoring file path.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Value("${file.monitor.path}")
    private String monitoringFilePath;

    /**
     * The Picture x.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Value("${picture.cut.x}")
    private int pictureX;

    /**
     * The Picture y.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Value("${picture.cut.y}")
    private int pictureY;

    /**
     * The Picture width.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Value("${picture.cut.width}")
    private int pictureWidth;

    /**
     * The Picture height.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Value("${picture.cut.height}")
    private int pictureHeight;

    /**
     * The Cut image.
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Autowired
    private CutImage cutImage;

    /**
     * Start.
     * 定时任务
     * 当该方法执行完，一秒钟后再次执行该方法(注意，不是每隔一秒钟执行一次)
     *
     * @since millions-heros-core-be 1.0.0
     */
    @Scheduled(fixedDelay = 1000)
    public void start() {
        LOG.info("定时任务启动");
        long startTime = System.currentTimeMillis();
        File monitoringFile = cutImage.createFile(monitoringFilePath);
        File[] screenshots = monitoringFile.listFiles();
        if (screenshots == null || screenshots.length == 0) {
            LOG.info("该文件下不存在截图文件");
        } else if (screenshots.length == 1) {
            File screenshot = screenshots[0];
            LOG.info(">>>>>>>>> 已扫描到截图文件，开始分析");
            // 裁剪图片
            File cropImage = cutImage.cropImage(screenshot, pictureX, pictureY, pictureWidth, pictureHeight);
            // 图片识别
            Question question = OCRImage.ocrImage(cropImage);
            if (question != null) {
                // 搜索答案
                SearchAnswer.getAnswer(question);
            }
            // 删除截图
            if (screenshot.delete()) {
                LOG.info("程序共耗时: " + (System.currentTimeMillis() - startTime) + "ms");
            }
        } else {
            LOG.error("请保证该文件下只有一张截图");
        }
    }

    /**
     * The entry point of application.
     * springBoot程序入口
     *
     * @param args the input arguments
     * @since millions-heros-core-be 1.0.0
     */
    public static void main(String[] args) {
        LOG.info("Application Start!");
        SpringApplication.run(Application.class, args);
    }
}
