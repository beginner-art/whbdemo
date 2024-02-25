package com.example.whbdemo.dao;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.SearchRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface SearchRecordDao extends BaseMapper<SearchRecord> {

    @Insert("INSERT INTO search_statistics (search_query,clicked_product_id) VALUES (#{searchQuery}, #{clickedproductId})")
    int insertSearchRecord(SearchRecord searchRecord);
}
