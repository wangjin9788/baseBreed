package com.breed.govern.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BreedListVo {
    private long bid;
    //投入时间
    private String inputTime;
    //投入重量
    private float inputWeight;
    //产出时间
    private String proTime;
    //产出重量
    private float proWeight;
    //养殖模式
    private String pattern;
    //状态
    private int status;
    //栏位编号
    private int number;
    //类型(0:室内  1：室外)
    private int type;
}
