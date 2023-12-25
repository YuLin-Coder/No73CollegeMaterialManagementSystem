package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.InStoreService;
import cn.huanzi.qch.baseadmin.mbg.mapper.InStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.PurchaseOrderMapper;
import cn.huanzi.qch.baseadmin.mbg.model.InStore;
import cn.huanzi.qch.baseadmin.mbg.model.InStoreExample;
import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrder;
import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("in")
public class InStoreController {


    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private InStoreMapper inStoreMapper;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/in/index");
    }

    @PostMapping("/page")
    public Result<PageInfo<InStore>> page(InStore inStore) {
        List<InStore> inStoreList = inStoreService.page(inStore);
        for(InStore inStore1 : inStoreList) {
            Date createTime = inStore1.getCreateTime();
            Date expireTime = inStore1.getExpireTime();
            if(expireTime != null) {
                if(expireTime.after(createTime)) {
                    inStore1.setStatus("未过期");
                } else {
                    inStore1.setStatus("已过期");
                }
            } else {
                inStore1.setStatus("未过期");
            }
        }

        com.github.pagehelper.PageInfo<InStore> pageInfo = new com.github.pagehelper.PageInfo<>(inStoreList);
        return Result.of(PageInfo.of(pageInfo));
    }

    @PostMapping("/save")
    public Result<Void> save(InStore inStore) {
        PurchaseOrderExample purchaseOrderExample = new PurchaseOrderExample();
        purchaseOrderExample.createCriteria().andGoodsNameEqualTo(inStore.getGoodsName());
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectByExample(purchaseOrderExample);
        if(CollectionUtils.isEmpty(purchaseOrders)) {
            return Result.of(null, false, "无购买记录，无法入库");
        }
        int sum = purchaseOrders.stream().mapToInt(PurchaseOrder::getCount).sum();

        InStoreExample inStoreExample = new InStoreExample();
        inStoreExample.createCriteria().andGoodsNameEqualTo(inStore.getGoodsName());
        List<InStore> inStores = inStoreMapper.selectByExample(inStoreExample);
        int sum1 = inStores.stream().mapToInt(InStore::getCount).sum();
        if(sum1 >= sum) {
            return Result.of(null, false, "该商品购买数量不足，无法入库");
        }
        inStoreService.save(inStore);
        return Result.of(null);
    }

}