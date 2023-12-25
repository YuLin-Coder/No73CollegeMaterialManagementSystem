package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.StoreGoodsService;
import cn.huanzi.qch.baseadmin.mbg.model.InStore;
import cn.huanzi.qch.baseadmin.mbg.model.StoreGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("store")
public class StoreGoodsController {

    @Autowired
    private StoreGoodsService storeGoodsService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/store/index");
    }

    @PostMapping("/page")
    public Result<PageInfo<StoreGoods>> page(StoreGoods storeGoods) {
        List<StoreGoods> storeGoodsList = storeGoodsService.page(storeGoods);
        com.github.pagehelper.PageInfo<StoreGoods> pageInfo = new com.github.pagehelper.PageInfo<>(storeGoodsList);
        return Result.of(PageInfo.of(pageInfo));
    }
}