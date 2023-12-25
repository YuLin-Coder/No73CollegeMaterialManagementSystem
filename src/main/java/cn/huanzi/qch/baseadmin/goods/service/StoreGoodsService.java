package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.StoreGoodsMapper;
import cn.huanzi.qch.baseadmin.mbg.model.InStore;
import cn.huanzi.qch.baseadmin.mbg.model.InStoreExample;
import cn.huanzi.qch.baseadmin.mbg.model.StoreGoods;
import cn.huanzi.qch.baseadmin.mbg.model.StoreGoodsExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("store")
public class StoreGoodsService {

    @Autowired
    private StoreGoodsMapper storeGoodsMapper;


    public List<StoreGoods> page(StoreGoods storeGoods) {
        int page = storeGoods.getPage();
        int rows = storeGoods.getRows();
        PageHelper.startPage(page, rows);
        StoreGoodsExample storeGoodsExample = new StoreGoodsExample();
        StoreGoodsExample.Criteria criteria = storeGoodsExample.createCriteria();

        String goodsName = storeGoods.getGoodsName();
        if (!StringUtils.isEmpty(goodsName)) {
            criteria.andGoodsNameLike(goodsName);
        }
        return storeGoodsMapper.selectByExample(storeGoodsExample);
    }

}