package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.PurchaseOrderMapper;
import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrder;
import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrderExample;
import cn.huanzi.qch.baseadmin.util.SysUserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    public List<PurchaseOrder> page(PurchaseOrder purchaseOrder) {
        int page = purchaseOrder.getPage();
        int rows = purchaseOrder.getRows();
        PageHelper.startPage(page, rows);
        PurchaseOrderExample purchaseOrderExample = new PurchaseOrderExample();
        PurchaseOrderExample.Criteria criteria = purchaseOrderExample.createCriteria();

        String type = purchaseOrder.getType();
        if (!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        String goodsName = purchaseOrder.getGoodsName();
        if (!StringUtils.isEmpty(goodsName)) {
            criteria.andGoodsNameLike(goodsName);
        }
        return purchaseOrderMapper.selectByExample(purchaseOrderExample);
    }

    public void delete(Integer id) {
        purchaseOrderMapper.deleteByPrimaryKey(id);
    }

    public void save(PurchaseOrder purchaseOrder) {
        Integer id = purchaseOrder.getId();
        purchaseOrder.setUserId(SysUserUtil.getSysUser().getUserId());
        purchaseOrder.setUserName(SysUserUtil.getSysUser().getUserName());
        if(Objects.isNull(id)) {
            purchaseOrderMapper.insertSelective(purchaseOrder);
        } else {
            purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
        }
    }
}