package com.sun.millions.heros.heros.core;

import com.sun.millions.heros.heros.core.model.Question;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * <b><code>SearchAnswer</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/1/13 22:51
 *
 * @author Sun Jinpeng
 * @version 1.0.0
 * @since millions-heros-core-be 1.0.0
 */
public class SearchAnswer {

    /**
     * The Log.
     *
     * @since millions-heros-core-be 1.0.0
     */
    private static final Logger LOG = Logger.getLogger(SearchAnswer.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @since millions-heros-core-be 1.0.0
     */
    public static void main(String[] args) {
        Question question = new Question();
        question.setTitle("2008年奥运会开幕式主会场是?");
        List<String> answers = new ArrayList<>();
        answers.add("水立方");
        answers.add("鸟巢");
        answers.add("五棵松");
        question.setAnswers(answers);

        try {
            String title = URLEncoder.encode(question.getTitle(), "UTF-8");
            // 调用百度 "简单搜索" APP接口，搜索结果更准确
            String url = "https://m.baidu.com/s?sa=ikb&word=" + title;
            String searchResult = GETRequest(url);
            Map<String, Integer> answerMap = new HashMap<>(16);
            if (!CollectionUtils.isEmpty(question.getAnswers())) {
                for (String answer : question.getAnswers()) {
                    answerMap.put(answer, getAnswerCount(searchResult, answer));
                }
            } else {
                LOG.error("识别答案出错 ,答案为空!");
                return;
            }
            getFinalAnswer(answerMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get request string.
     *
     * @param path the path
     * @return the string
     * @since millions-heros-core-be 1.0.0
     */
    private static String GETRequest(String path) {
        long startTime = System.currentTimeMillis();
        try {
            URL url = new URL(path);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            LOG.info("HTTP请求共耗时: " + (System.currentTimeMillis() - startTime) + "ms");
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Gets answer count.
     *
     * @param result the string
     * @param answer the a
     * @since millions-heros-core-be 1.0.0
     */
    private static Integer getAnswerCount(String result, String answer) {
        int i = result.length() - result.replace(answer, "").length();
        Integer count = i / answer.length();
        LOG.info("匹配 " + answer + " 个数 :" + count);
        return count;
    }

    /**
     * Gets final answer.
     *
     * @param result the result
     * @since millions-heros-core-be 1.0.0
     */
    private static void getFinalAnswer(Map<String, Integer> result) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(result.entrySet());
        // 按照答案匹配个数降序排列
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        String answer = entryList.get(0).getKey();
        LOG.info("本题建议答案为: " + answer);
    }
}
