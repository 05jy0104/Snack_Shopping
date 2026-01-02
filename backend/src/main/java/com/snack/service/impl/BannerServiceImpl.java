package com.snack.service.impl;

import com.snack.entity.Banner;
import com.snack.mapper.BannerMapper;
import com.snack.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> findAll() {
        return bannerMapper.findAll();
    }

    @Override
    public Banner findById(Integer id) {
        return bannerMapper.findById(id);
    }

    @Override
    public void add(Banner banner) {
        banner.setCreateTime(new Date());
        bannerMapper.insert(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.update(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerMapper.delete(id);
    }
}
