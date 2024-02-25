package com.example.whbdemo.domain;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Data
public class SearchStatistic {
    private String searchQuery;
    private Integer count;
}