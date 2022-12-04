package com.breed.govern.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 王进
 * @since 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BBreed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 养殖表
     */
    @TableId(value = "b_id", type = IdType.AUTO)
    private Long bId;

    /**
     * 养殖模式
     */
    private Long bmId;

    /**
     * 标志（0：健康  1：一般  2：有问题）
     */
    private Integer mark;

    /**
     * 养殖编号
     */
    private Integer number;

    /**
     * 投入时间
     */
    private LocalDateTime inputTime;

    /**
     * 状态（0：养殖中 1：结束）
     */
    private Integer status;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 投入重量
     */
    private Double inputWeight;

    /**
     * 产出重量
     */
    private Double outWeight;

    /**
     * 养殖面积
     */
    private Double extent;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 类型(0:室内  1：室外)
     */
    private Integer type;

}
