package com.gala.rabbitmq.entity;

import lombok.Data;

/**
 * @author YJ
 * @date 2021/9/1
 * @description DirectMessage
 */
@Data
public class DirectMessage {
    private String msgId;
    private String msgDate;
    private String msgText;
}
