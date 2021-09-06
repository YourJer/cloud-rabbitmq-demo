package com.gala.rabbitmq.entity;

import lombok.Data;

/**
 * @author YJ
 * @date 2021/8/31
 * @description DirectMessage
 */
@Data
public class DirectMessage {
    private String msgId;
    private String msgDate;
    private String msgText;
}
