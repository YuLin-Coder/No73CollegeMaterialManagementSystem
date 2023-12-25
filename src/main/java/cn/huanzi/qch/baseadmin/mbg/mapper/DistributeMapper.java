package cn.huanzi.qch.baseadmin.mbg.mapper;

import cn.huanzi.qch.baseadmin.mbg.model.Distribute;
import cn.huanzi.qch.baseadmin.mbg.model.DistributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DistributeMapper {
    int countByExample(DistributeExample example);

    int deleteByExample(DistributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Distribute record);

    int insertSelective(Distribute record);

    List<Distribute> selectByExample(DistributeExample example);

    Distribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Distribute record, @Param("example") DistributeExample example);

    int updateByExample(@Param("record") Distribute record, @Param("example") DistributeExample example);

    int updateByPrimaryKeySelective(Distribute record);

    int updateByPrimaryKey(Distribute record);
}