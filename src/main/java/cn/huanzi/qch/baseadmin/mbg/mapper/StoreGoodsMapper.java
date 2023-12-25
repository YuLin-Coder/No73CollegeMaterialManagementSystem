package cn.huanzi.qch.baseadmin.mbg.mapper;

import cn.huanzi.qch.baseadmin.mbg.model.StoreGoods;
import cn.huanzi.qch.baseadmin.mbg.model.StoreGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreGoodsMapper {
    int countByExample(StoreGoodsExample example);

    int deleteByExample(StoreGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreGoods record);

    int insertSelective(StoreGoods record);

    List<StoreGoods> selectByExample(StoreGoodsExample example);

    StoreGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StoreGoods record, @Param("example") StoreGoodsExample example);

    int updateByExample(@Param("record") StoreGoods record, @Param("example") StoreGoodsExample example);

    int updateByPrimaryKeySelective(StoreGoods record);

    int updateByPrimaryKey(StoreGoods record);
}