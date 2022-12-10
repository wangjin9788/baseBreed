package com.breed.govern.dto.vo;

import lombok.Data;

@Data
public class ExcInfoDisease {
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
}
