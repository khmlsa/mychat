package com.tr.pojo;

import lombok.Data;

@Data
public class WsMessage {
    private Object msg;
    private boolean isSys;
    private String from;
    private String to;
}
