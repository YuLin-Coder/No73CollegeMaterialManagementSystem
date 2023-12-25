package cn.huanzi.qch.baseadmin.mbg.mapper;

import cn.huanzi.qch.baseadmin.mbg.model.InStore;
import cn.huanzi.qch.baseadmin.mbg.model.InStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InStoreMapper {
    int countByExample(InStoreExample example);

    int deleteByExample(InStoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InStore record);

    int insertSelective(InStore record);

    List<InStore> selectByExample(InStoreExample example);

    InStore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InStore record, @Param("example") InStoreExample example);

    int updateByExample(@Param("record") InStore record, @Param("example") InStoreExample example);

    int updateByPrimaryKeySelective(InStore record);

    int updateByPrimaryKey(InStore record);
}