package com.sun.millions.heros.heros.core;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class Application {

    /**
     * The Log.
     *
     * @since millions-heros-core-be 1.0.0
     */
    private static final Logger LOG = Logger.getLogger(Application.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @since millions-heros-core-be 1.0.0
     */
    public static void main(String[] args) {
        LOG.info("Application Start!");
        SpringApplication.run(Application.class, args);
    }
}
