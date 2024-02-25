package com.example.whbdemo.controller;

import com.example.whbdemo.dao.SearchRecordDao;
import com.example.whbdemo.dao.SearchStatisticDao;
import com.example.whbdemo.domain.SearchStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@Transactional
@RequestMapping("/mainsearch")
public class Mainsearch0Controller {
    private static final Logger logger = LoggerFactory.getLogger(MainsearchController.class);

    @Autowired
    private SearchStatisticDao searchStatisticDao;
    @GetMapping("/today-keywords")
    public Result todayKeywords() {
        List<SearchStatistic> searchCount = searchStatisticDao.countTodaySearchKeywords();
        Integer code = Code.GET_OK;
        String msg = "Success";
        Result result = new Result(code, searchCount, msg);
        logger.info("Returning Result: {}", result);

        return result;      }

}
