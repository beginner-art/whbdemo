package com.example.whbdemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Data
public class SearchStatistic {
    @TableId("searchId")
    private Integer searchId;
    private String searchQuery;
    private Integer count;
}