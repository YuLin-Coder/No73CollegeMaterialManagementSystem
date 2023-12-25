package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.DistributeMapper;
import cn.huanzi.qch.baseadmin.mbg.model.Distribute;
import cn.huanzi.qch.baseadmin.mbg.model.DistributeExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ReceiveService {

    @Autowired
    private DistributeMapper distributeMapper;


    public List<Distribute> historyPage(Distribute distribute) {
        int page = distribute.getPage();
        int rows = distribute.getRows();
        PageHelper.startPage(page, rows);
        DistributeExample distributeExample = new DistributeExample();
        DistributeExample.Criteria criteria = distributeExample.createCriteria();

        String type = distribute.getType();
        if (!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        String goodsName = distribute.getGoodsName();
        if (!StringUtils.isEmpty(goodsName)) {
            criteria.andGoodsNameLike(goodsName);
        }
        String dept = distribute.getDept();
        if (!StringUtils.isEmpty(dept)) {
            criteria.andDeptEqualTo(dept);
        }
        Integer teacherId = distribute.getTeacherId();
        if (Objects.nonNull(teacherId)) {
            criteria.andTeacherIdEqualTo(teacherId);
        }
        criteria.andStatusEqualTo("已领取");
        return distributeMapper.selectByExample(distributeExample);

    }

    public List<Distribute> page(Distribute distribute) {
        int page = distribute.getPage();
        int rows = distribute.getRows();
        PageHelper.startPage(page, rows);
        DistributeExample distributeExample = new DistributeExample();
        DistributeExample.Criteria criteria = distributeExample.createCriteria();

        String type = distribute.getType();
        if (!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        String goodsName = distribute.getGoodsName();
        if (!StringUtils.isEmpty(goodsName)) {
            criteria.andGoodsNameLike(goodsName);
        }
        Integer teacherId = distribute.getTeacherId();
        if (Objects.nonNull(teacherId)) {
            criteria.andTeacherIdEqualTo(teacherId);
        }
        criteria.andStatusEqualTo("已出库");
        return distributeMapper.selectByExample(distributeExample);
    }

    public void receive(Integer id) {
        Distribute distribute = distributeMapper.selectByPrimaryKey(id);
        distribute.setStatus("已领取");
        distributeMapper.updateByPrimaryKey(distribute);
    }
}