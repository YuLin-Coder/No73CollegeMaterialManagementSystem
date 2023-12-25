package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.OutStoreService;
import cn.huanzi.qch.baseadmin.mbg.model.OutStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("out")
public class OutStoreController {

    @Autowired
    private OutStoreService outStoreService;


    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/out/index");
    }

    @PostMapping("/page")
    public Result<PageInfo<OutStore>> page(OutStore outStore) {
        List<OutStore> inStoreList = outStoreService.page(outStore);
        com.github.pagehelper.PageInfo<OutStore> pageInfo = new com.github.pagehelper.PageInfo<>(inStoreList);
        return Result.of(PageInfo.of(pageInfo));
    }
}