package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.InStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.StoreGoodsMapper;
import cn.huanzi.qch.baseadmin.mbg.model.*;
import cn.huanzi.qch.baseadmin.util.SysUserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class InStoreService {

    @Autowired
    private InStoreMapper inStoreMapper;

    @Autowired
    private StoreGoodsMapper storeGoodsMapper;

    public List<InStore> page(InStore inStore) {
        int page = inStore.getPage();
        int rows = inStore.getRows();
        PageHelper.startPage(page, rows);
        InStoreExample inStoreExample = new InStoreExample();
        InStoreExample.Criteria criteria = inStoreExample.createCriteria();

        String purchaseOrderInId = inStore.getPurchaseOrderInId();
        if (!StringUtils.isEmpty(purchaseOrderInId)) {
            criteria.andPurchaseOrderInIdEqualTo(purchaseOrderInId);
        }
        String type = inStore.getType();
        if (!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        String goodsName = inStore.getGoodsName();
        if (!StringUtils.isEmpty(goodsName)) {
            criteria.andGoodsNameLike(goodsName);
        }
        return inStoreMapper.selectByExample(inStoreExample);
    }

    public void save(InStore inStore) {
        inStore.setStoreName("C1");
        inStore.setPurchaseOrderInId("rk"+(int)(Math.random() * 1000000));
        inStore.setOperatorId(SysUserUtil.getSysUser().getUserId());
        inStore.setOperator(SysUserUtil.getSysUser().getUserName());
        inStore.setCreateTime(new Date());
        inStoreMapper.insertSelective(inStore);

        StoreGoodsExample storeGoodsExample = new StoreGoodsExample();
        StoreGoodsExample.Criteria criteria = storeGoodsExample.createCriteria();
        criteria.andGoodsNameEqualTo(inStore.getGoodsName());
        List<StoreGoods> storeGoodsList = storeGoodsMapper.selectByExample(storeGoodsExample);
        if(!CollectionUtils.isEmpty(storeGoodsList)) {
            StoreGoods storeGoods = storeGoodsList.get(0);
            storeGoods.setCount(storeGoods.getCount() + inStore.getCount());
            storeGoodsMapper.updateByPrimaryKeySelective(storeGoods);
        } else {
            StoreGoods storeGoods = new StoreGoods();
            storeGoods.setStoreName("C1");
            storeGoods.setGoodsName(inStore.getGoodsName());
            storeGoods.setCount(inStore.getCount());
            storeGoodsMapper.insertSelective(storeGoods);
        }

    }
}