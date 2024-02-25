package com.example.whbdemo.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchRecord {
    @TableId(value = "search_id",type = IdType.AUTO )
    private Long searchId;
    private String searchQuery;
    private LocalDateTime searchTimestamp = LocalDateTime.now();
    private Long clickedproductId;

}
