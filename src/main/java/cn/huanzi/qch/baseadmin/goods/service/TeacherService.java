package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.TeacherMapper;
import cn.huanzi.qch.baseadmin.mbg.model.Teacher;
import cn.huanzi.qch.baseadmin.mbg.model.TeacherExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teacher> page(Teacher teacher) {
        int page = teacher.getPage();
        int rows = teacher.getRows();
        PageHelper.startPage(page, rows);
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        String loginName = teacher.getLoginName();
        if (!StringUtils.isEmpty(loginName)) {
            criteria.andLoginNameEqualTo(loginName);
        }
        return teacherMapper.selectByExample(teacherExample);
    }

    public void delete(Integer id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    public void save(Teacher teacher) {
        Integer id = teacher.getId();
        if(Objects.isNull(id)) {
            teacherMapper.insertSelective(teacher);
        } else {
            teacherMapper.updateByPrimaryKeySelective(teacher);
        }
    }

    public List<Teacher> query(String dept) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andDeptEqualTo(dept);
        return teacherMapper.selectByExample(teacherExample);
    }

}