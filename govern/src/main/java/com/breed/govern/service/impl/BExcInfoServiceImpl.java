package com.breed.govern.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.breed.govern.dto.vo.ExcInfoList;
import com.breed.govern.entity.BExcInfo;
import com.breed.govern.mapper.BExcInfoMapper;
import com.breed.govern.service.IBExcInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.breed.govern.service.IBExcLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 异常信息表 服务实现类
 * </p>
 *
 * @author 王进
 * @since 2022-12-10
 */
@Service
public class BExcInfoServiceImpl extends ServiceImpl<BExcInfoMapper, BExcInfo> implements IBExcInfoService {

    @Override
    public List<ExcInfoList> getExcInfoList() {
        List<ExcInfoList> excInfoList = baseMapper.getExcInfoList();
        return excInfoList;
    }
}
