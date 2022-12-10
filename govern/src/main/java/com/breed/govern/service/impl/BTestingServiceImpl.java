package com.breed.govern.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.breed.govern.dto.vo.ExcInfoDisease;
import com.breed.govern.dto.vo.TestingListVo;
import com.breed.govern.entity.AdAnimalDisease;
import com.breed.govern.entity.BExcInfo;
import com.breed.govern.entity.BExcSummary;
import com.breed.govern.entity.BTesting;
import com.breed.govern.mapper.BExcInfoMapper;
import com.breed.govern.mapper.BTestingMapper;
import com.breed.govern.service.IAdAnimalDiseaseService;
import com.breed.govern.service.IBExcSummaryService;
import com.breed.govern.service.IBTestingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 检测表 服务实现类
 * </p>
 *
 * @author 王进
 * @since 2022-12-10
 */
@Service
public class BTestingServiceImpl extends ServiceImpl<BTestingMapper, BTesting> implements IBTestingService {
    @Autowired
    private IBExcSummaryService summaryService;
    @Autowired
    private IAdAnimalDiseaseService diseaseService;

    @Resource
    private BExcInfoMapper excInfoMapper;

    @Override
    public int createTesting(BTesting data) {
        data.setType(0);
        if(data.getOId()>0){
            data.setType(1);
        }
        data.setCreateTime(LocalDateTime.now());
        data.setTestingTime(LocalDateTime.now());
        int dataCount = baseMapper.insert(data);
        if(dataCount>0){
            checkExcInfo(data);
        }
        return dataCount;
    }

    @Override
    public int updateTesting(BTesting data) {
        int byId = baseMapper.updateById(data);
        return byId;
    }

    @Override
    public List<TestingListVo> getTestingList(long bid) {
        List<TestingListVo> data = baseMapper.getTestingList(bid);
        for (TestingListVo vo:
                data) {
            if(vo.getSymptom()!=null){
                List<Integer> strings = JSONObject.parseArray(vo.getSymptom().toString(), Integer.class);
                vo.setSymptomName(baseMapper.getLabelInfo(strings));
            }
        }
        return data;
    }

    @Override
    public BTesting getTestingById(long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Integer deleteTesting(long id) {
        return baseMapper.deleteById(id);
    }

    private void checkExcInfo(BTesting data){
        String labelInfo=null;
        //获取对应的标签
        if(data.getSymptom()!=null) {
            List<Integer> strings = JSONObject.parseArray(data.getSymptom().toString(), Integer.class);
             labelInfo = baseMapper.getLabelInfo(strings);
        }
        //获取总结表信息
        ExcInfoDisease excInfoDisease = summaryService.getSummaryByLabel(labelInfo);
        if(excInfoDisease==null){
            //获取疾病信息表
            excInfoDisease = diseaseService.getDiseaseExcInfo(labelInfo);
        }
        if(excInfoDisease!=null){
            BExcInfo excInfo = new BExcInfo();
            excInfo.setBId(data.getBId());
            excInfo.setLabelName(labelInfo);
            excInfo.setType(data.getType());
            excInfo.setCreateTime(LocalDateTime.now());
            excInfo.setPathogeny(excInfoDisease.getPathogeny());
            excInfo.setSymptom(excInfoDisease.getSymptom());
            excInfo.setTreatment(excInfoDisease.getTreatment());
            excInfo.setDiseaseName(excInfoDisease.getDiseaseName());
            excInfo.setStatus(1);
            excInfo.setOId(data.getOId());
            excInfoMapper.insert(excInfo);
        }

    }
}
