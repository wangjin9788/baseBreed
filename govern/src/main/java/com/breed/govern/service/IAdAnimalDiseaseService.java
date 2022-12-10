package com.breed.govern.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.breed.govern.dto.vo.AdNatureListVo;
import com.breed.govern.dto.vo.ExcInfoDisease;
import com.breed.govern.entity.AdAnimalDisease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 动物病理信息表 服务类
 * </p>
 *
 * @author 王进
 * @since 2022-12-04
 */
public interface IAdAnimalDiseaseService extends IService<AdAnimalDisease> {
    int createAdAnimalDisease(AdAnimalDisease data);

    int updateAdAnimalDisease(AdAnimalDisease data);

    Integer deleteAdAnimalDisease(long id);


    AdAnimalDisease getAdAnimalDiseaseById(Integer id);

    List<AdNatureListVo> getAdAnimalDiseaseList();

    ExcInfoDisease getDiseaseExcInfo(String data);
}
