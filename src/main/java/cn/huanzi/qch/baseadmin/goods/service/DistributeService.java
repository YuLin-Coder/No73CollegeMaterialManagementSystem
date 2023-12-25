package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.DistributeMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.OutStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.StoreGoodsMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.TeacherMapper;
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
public class DistributeService {

    @Autowired
    private DistributeMapper distributeMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private OutStoreMapper outStoreMapper;

    @Autowired
    private StoreGoodsMapper storeGoodsMapper;

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
        String dept = distribute.getDept();
        if (!StringUtils.isEmpty(dept)) {
            criteria.andDeptEqualTo(dept);
        }
        String teacher = distribute.getTeacher();
        if (!StringUtils.isEmpty(teacher)) {
            criteria.andTeacherLike(teacher);
        }
        criteria.andStatusEqualTo("未领取");
        return distributeMapper.selectByExample(distributeExample);


    }

    public void save(Distribute distribute) {
        Integer teacherId = distribute.getTeacherId();
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        distribute.setTeacher(teacher.getUserName());
        distribute.setStatus("未领取");
        distributeMapper.insertSelective(distribute);
    }

    public void ck(Integer id) {
        Distribute distribute = distributeMapper.selectByPrimaryKey(id);
        distribute.setStatus("已出库");
        distributeMapper.updateByPrimaryKey(distribute);

        OutStore outStore = new OutStore();
        outStore.setType(distribute.getType());
        outStore.setStoreName("C1");
        outStore.setGoodsName(distribute.getGoodsName());
        outStore.setCount(distribute.getCount());
        outStore.setPurchaseOrderOutId("ck"+(int)(Math.random() * 1000000));
        outStore.setOperatorId(SysUserUtil.getSysUser().getUserId());
        outStore.setOperator(SysUserUtil.getSysUser().getUserName());
        outStore.setCreateTime(new Date());
        outStoreMapper.insertSelective(outStore);

        StoreGoodsExample storeGoodsExample = new StoreGoodsExample();
        StoreGoodsExample.Criteria criteria = storeGoodsExample.createCriteria();
        criteria.andGoodsNameEqualTo(outStore.getGoodsName());
        List<StoreGoods> storeGoodsList = storeGoodsMapper.selectByExample(storeGoodsExample);
        if(!CollectionUtils.isEmpty(storeGoodsList)) {
            StoreGoods storeGoods = storeGoodsList.get(0);
            storeGoods.setCount(storeGoods.getCount() - outStore.getCount());
            storeGoodsMapper.updateByPrimaryKeySelective(storeGoods);
        }
    }
}