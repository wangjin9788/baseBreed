package com.breed.govern.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 异常信息表
 * </p>
 *
 * @author 王进
 * @since 2022-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BExcInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 异常表id
     */
    private Long eId;

    /**
     * 养殖表id
     */
    private Long bId;

    /**
     * 标签表
     */
    private String labelName;

    /**
     * 异常类型(0:未知 2：操作异常)
     */
    private Integer type;

    /**
     * 时间
     */
    private LocalDateTime createTime;
    /**
     * 病害名称
     */
    private String diseaseName;
    /**
     * 原因
     */
    private String pathogeny;

    /**
     * 症状
     */
    private String symptom;

    /**
     * 治疗方法
     */
    private String treatment;


    /**
     * 正常操作表id
     */
    private Long oId;

    /**
     * 治疗状态（0：治好 1：治疗中）
     */
    private Integer status;


}
