package cn.huanzi.qch.baseadmin.mbg.mapper;

import cn.huanzi.qch.baseadmin.mbg.model.OutStore;
import cn.huanzi.qch.baseadmin.mbg.model.OutStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutStoreMapper {
    int countByExample(OutStoreExample example);

    int deleteByExample(OutStoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OutStore record);

    int insertSelective(OutStore record);

    List<OutStore> selectByExample(OutStoreExample example);

    OutStore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OutStore record, @Param("example") OutStoreExample example);

    int updateByExample(@Param("record") OutStore record, @Param("example") OutStoreExample example);

    int updateByPrimaryKeySelective(OutStore record);

    int updateByPrimaryKey(OutStore record);
}