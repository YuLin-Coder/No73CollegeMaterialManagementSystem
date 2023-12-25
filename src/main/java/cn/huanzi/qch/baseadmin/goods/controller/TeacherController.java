package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.TeacherService;
import cn.huanzi.qch.baseadmin.mbg.model.Teacher;
import cn.huanzi.qch.baseadmin.sys.sysuser.service.SysUserService;
import cn.huanzi.qch.baseadmin.sys.sysuser.vo.SysUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/teacher/index");
    }

    @PostMapping("/page")
    public Result<PageInfo<Teacher>> page(Teacher teacher) {
        List<Teacher> teacherList = teacherService.page(teacher);
        com.github.pagehelper.PageInfo<Teacher> pageInfo = new com.github.pagehelper.PageInfo<>(teacherList);
        return Result.of(PageInfo.of(pageInfo));
    }

    @PostMapping("/save")
    public Result<Void> save(Teacher teacher) {
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(teacher, sysUserVo);
        sysUserVo.setCreateTime(new Date());
        sysUserVo.setUpdateTime(sysUserVo.getCreateTime());
        Result<SysUserVo> save = sysUserService.save(sysUserVo);
        teacher.setUserId(save.getData().getUserId());
        teacherService.save(teacher);
        return Result.of(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        teacherService.delete(id);
        return Result.of(null);
    }

    @GetMapping("query")
    public Result<List<Teacher>> info(String dept) {
        return Result.of(teacherService.query(dept));
    }
}
