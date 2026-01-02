package com.snack.mapper;

import com.snack.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BannerMapper {
    List<Banner> findAll();
    Banner findById(Integer id);
    void insert(Banner banner);
    void update(Banner banner);
    void delete(Integer id);
}
