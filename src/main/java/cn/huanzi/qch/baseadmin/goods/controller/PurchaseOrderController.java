package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.PurchaseService;
import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("goods")
public class PurchaseOrderController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/goods/index");
    }


    @PostMapping("/page")
    public Result<PageInfo<PurchaseOrder>> page(PurchaseOrder purchaseOrder) {
        List<PurchaseOrder> purchaseOrderList = purchaseService.page(purchaseOrder);
        com.github.pagehelper.PageInfo<PurchaseOrder> pageInfo = new com.github.pagehelper.PageInfo<>(purchaseOrderList);
        return Result.of(PageInfo.of(pageInfo));
    }

    @PostMapping("/save")
    public Result<Void> save(PurchaseOrder purchaseOrder) {
        purchaseService.save(purchaseOrder);
        return Result.of(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        purchaseService.delete(id);
        return Result.of(null);
    }
}