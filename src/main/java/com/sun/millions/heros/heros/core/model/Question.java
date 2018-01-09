package com.sun.millions.heros.heros.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <b><code>Question</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/1/9 23:13
 *
 * @author Sun Jinpeng
 * @version 0.1.0
 * @since millions-heros-core-be 0.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    /**
     * The Title.
     *
     * @since millions-heros-core-be 0.1.0
     */
    private String title;

    /**
     * The Answers.
     *
     * @since millions-heros-core-be 0.1.0
     */
    private List<String> answers;
}
