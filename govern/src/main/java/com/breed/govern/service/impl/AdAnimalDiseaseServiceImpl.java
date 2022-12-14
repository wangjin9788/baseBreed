package com.breed.govern.service.impl;

import com.breed.govern.dto.vo.AdNatureListVo;
import com.breed.govern.entity.AdAnimalDisease;
import com.breed.govern.mapper.AdAnimalDiseaseMapper;
import com.breed.govern.service.IAdAnimalDiseaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动物病理信息表 服务实现类
 * </p>
 *
 * @author 王进
 * @since 2022-12-04
 */
@Service
public class AdAnimalDiseaseServiceImpl extends ServiceImpl<AdAnimalDiseaseMapper, AdAnimalDisease> implements IAdAnimalDiseaseService {

    @Override
    public int createAdAnimalDisease(AdAnimalDisease data) {
        return baseMapper.insert(data);
    }

    @Override
    public int updateAdAnimalDisease(AdAnimalDisease data) {
        return baseMapper.updateById(data);
    }

    @Override
    public Integer deleteAdAnimalDisease(long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public AdAnimalDisease getAdAnimalDiseaseById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<AdNatureListVo> getAdAnimalDiseaseList() {
        return baseMapper.getAdAnimalDiseaseList();
    }

    @Override
    public long getDiseaseExcInfo(String data) {
        return baseMapper.getDiseaseExcInfo(data);
    }


}
