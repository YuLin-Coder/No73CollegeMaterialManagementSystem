package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.OutStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.model.InStoreExample;
import cn.huanzi.qch.baseadmin.mbg.model.OutStore;
import cn.huanzi.qch.baseadmin.mbg.model.OutStoreExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OutStoreService {

    @Autowired
    private OutStoreMapper outStoreMapper;


    public List<OutStore> page(OutStore outStore) {
        int page = outStore.getPage();
        int rows = outStore.getRows();
        PageHelper.startPage(page, rows);
        OutStoreExample outStoreExample = new OutStoreExample();
        OutStoreExample.Criteria criteria = outStoreExample.createCriteria();

        String purchaseOrderOutId = outStore.getPurchaseOrderOutId();
        if (!StringUtils.isEmpty(purchaseOrderOutId)) {
            criteria.andPurchaseOrderOutIdEqualTo(purchaseOrderOutId);
        }
        String type = outStore.getType();
        if (!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        String goodsName = outStore.getGoodsName();
        if (!StringUtils.isEmpty(goodsName)) {
            criteria.andGoodsNameLike(goodsName);
        }
        return outStoreMapper.selectByExample(outStoreExample);
    }
}