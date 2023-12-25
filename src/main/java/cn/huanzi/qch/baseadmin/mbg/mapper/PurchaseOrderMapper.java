package cn.huanzi.qch.baseadmin.mbg.mapper;

import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrder;
import cn.huanzi.qch.baseadmin.mbg.model.PurchaseOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurchaseOrderMapper {
    int countByExample(PurchaseOrderExample example);

    int deleteByExample(PurchaseOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    List<PurchaseOrder> selectByExample(PurchaseOrderExample example);

    PurchaseOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderExample example);

    int updateByExample(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderExample example);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);
}