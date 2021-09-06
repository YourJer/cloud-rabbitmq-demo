package com.gala.rabbitmq.utils;

import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author YJ
 * @date 2021/8/31
 * @description CommonUtil
 */
public class CommonUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getMsgMap(String text) {
        Map<String, String> map = new HashMap<>(3);
        map.put("msgId", CommonUtil.getUUID());
        map.put("msgDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("msgText", text);
        return JSON.toJSONString(map);
    }
}
