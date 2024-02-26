package com.example.whbdemo.controller;


import com.example.whbdemo.dao.SearchRecordDao;
import com.example.whbdemo.domain.SearchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/mainsearch")
public class MainsearchController {

    private static final Logger logger = LoggerFactory.getLogger(MainsearchController.class);

    @Autowired
    private SearchRecordDao searchRecordDao;

    @PostMapping("/search")
    public Result search(@RequestParam String searchQuery,Long clickedproductId ) {
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setSearchQuery(searchQuery);
        searchRecord.setClickedproductId(clickedproductId);
        searchRecordDao.insertSearchRecord(searchRecord);
        return new Result(Code.SAVE_OK , searchQuery);
    }   



}
