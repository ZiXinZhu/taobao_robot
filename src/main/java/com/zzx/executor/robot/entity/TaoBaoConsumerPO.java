package com.zzx.executor.robot.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaoBaoConsumerPO {
    private int id;
    private String account;
    private String password;
    private String payPassword;
    private int isFailuer;
    private String time;
}
