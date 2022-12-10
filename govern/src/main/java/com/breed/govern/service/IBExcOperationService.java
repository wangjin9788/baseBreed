package com.breed.govern.service;

import com.breed.govern.dto.vo.ExcOperationListVo;
import com.breed.govern.dto.vo.OperationListVo;
import com.breed.govern.dto.vo.SelectOperationList;
import com.breed.govern.entity.BExcOperation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.breed.govern.entity.BOperation;

import java.util.List;

/**
 * <p>
 * 异常操作表 服务类
 * </p>
 *
 * @author 王进
 * @since 2022-12-10
 */
public interface IBExcOperationService extends IService<BExcOperation> {
    int createExcOperation(BExcOperation data);

    int updateExcOperation(BExcOperation data);

    List<ExcOperationListVo> getExcOperationList(long id);

    BExcOperation getExcOperationById(long id);


    Integer deleteExcOperation(long id);

}
