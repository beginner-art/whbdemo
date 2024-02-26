package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.SearchStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface SearchStatisticDao extends BaseMapper<SearchStatistic> {
    @Select("SELECT search_query as searchQuery, COUNT(*) as count  FROM search_statistics  WHERE DATE(search_timestamp) BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND CURDATE() GROUP BY search_query;")
    List<SearchStatistic> countTodaySearchKeywords();
}
