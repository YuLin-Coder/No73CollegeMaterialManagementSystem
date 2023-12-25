package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.DistributeService;
import cn.huanzi.qch.baseadmin.mbg.mapper.DistributeMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.InStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.StoreGoodsMapper;
import cn.huanzi.qch.baseadmin.mbg.model.*;
import org.apache.catalina.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/distribute")
public class DistributeController {

    @Autowired
    private DistributeService distributeService;
    @Autowired
    private StoreGoodsMapper storeGoodsMapper;
    @Autowired
    private DistributeMapper distributeMapper;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/distribute/index");
    }

    @PostMapping("/page")
    public Result<PageInfo<Distribute>> page(Distribute distribute) {
        List<Distribute> distributeList = distributeService.page(distribute);
        com.github.pagehelper.PageInfo<Distribute> pageInfo = new com.github.pagehelper.PageInfo<>(distributeList);
        return Result.of(PageInfo.of(pageInfo));
    }

    @PostMapping("/save")
    public Result<Void> save(Distribute distribute) {
        String type = distribute.getType();
        String goodsName = distribute.getGoodsName();
        Integer count = distribute.getCount();
        String dept = distribute.getDept();
        Integer teacherId = distribute.getTeacherId();
        if(StringUtils.isEmpty(type)) {
            return Result.of(null, false, "物资类型不能为空");
        }
        if(StringUtils.isEmpty(goodsName)) {
            return Result.of(null, false, "物资名称不能为空");
        }
        if(count == null) {
            return Result.of(null, false, "请填写物资数量");
        }
        if(StringUtils.isEmpty(dept)) {
            return Result.of(null, false, "请选择院部");
        }
        if(teacherId == null) {
            return Result.of(null, false, "请选择教师");
        }

        StoreGoodsExample storeGoodsExample = new StoreGoodsExample();
        storeGoodsExample.createCriteria().andGoodsNameEqualTo(distribute.getGoodsName());
        List<StoreGoods> storeGoods = storeGoodsMapper.selectByExample(storeGoodsExample);
        if(CollectionUtils.isEmpty(storeGoods)) {
            return Result.of(null, false, "仓库无该商品，无法发放");
        }
        int sum = storeGoods.stream().mapToInt(StoreGoods::getCount).sum();

        DistributeExample distributeExample = new DistributeExample();
        distributeExample.createCriteria().andGoodsNameEqualTo(distribute.getGoodsName());
        List<Distribute> distributeList = distributeMapper.selectByExample(distributeExample);
        int sum1 = distributeList.stream().mapToInt(Distribute::getCount).sum();
        if(sum1 >= sum) {
            return Result.of(null, false, "该商品库存数量不足，无法入库");
        }




        distributeService.save(distribute);
        return Result.of(null);
    }

    @PostMapping("/ck/{id}")
    public Result<Void> ck(@PathVariable Integer id) {
        distributeService.ck(id);
        return Result.of(null);
    }
}