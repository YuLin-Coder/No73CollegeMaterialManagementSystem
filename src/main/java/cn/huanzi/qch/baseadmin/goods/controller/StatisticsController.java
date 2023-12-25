package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("in/index")
    public ModelAndView inIndex() {
        return new ModelAndView("goods/statistics/in");
    }

    @GetMapping("out/index")
    public ModelAndView outIndex() {
        return new ModelAndView("goods/statistics/out");
    }

    @RequestMapping("inPage")
    public Result<Map<String, Object>> in() {
        return Result.of(statisticsService.in());
    }
    @RequestMapping("outPage")
    public Result<Map<String, Object>> out() {
        return Result.of(statisticsService.out());
    }
}