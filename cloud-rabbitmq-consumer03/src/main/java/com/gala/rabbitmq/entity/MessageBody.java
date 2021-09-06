package com.gala.rabbitmq.entity;

import lombok.Data;

/**
 * @author YJ
 * @date 2021/9/2
 * @description MessageBody
 */
@Data
public class MessageBody {

    private String messageId;
    private String messageData;
    private String createTime;
}
