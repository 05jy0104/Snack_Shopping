package com.snack.service;

import com.snack.entity.Banner;
import java.util.List;

public interface BannerService {
    List<Banner> findAll();
    Banner findById(Integer id);
    void add(Banner banner);
    void update(Banner banner);
    void delete(Integer id);
}
