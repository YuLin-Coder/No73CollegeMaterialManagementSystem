package cn.huanzi.qch.baseadmin.goods.controller;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.goods.service.ReceiveService;
import cn.huanzi.qch.baseadmin.mbg.mapper.TeacherMapper;
import cn.huanzi.qch.baseadmin.mbg.model.Distribute;
import cn.huanzi.qch.baseadmin.mbg.model.Teacher;
import cn.huanzi.qch.baseadmin.mbg.model.TeacherExample;
import cn.huanzi.qch.baseadmin.sys.sysuser.pojo.SysUser;
import cn.huanzi.qch.baseadmin.util.SysUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("receive")
public class ReceiveController {

    @Autowired
    private ReceiveService receiveService;

    @Autowired
    private TeacherMapper teacherMapper;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("goods/receive/index");
    }
    @GetMapping("history")
    public ModelAndView history() {
        ModelAndView mv = new ModelAndView("goods/receive/history");
        mv.addObject("role", SysUserUtil.getSysUser().getRoleName());
        return mv;
    }

    @PostMapping("/page")
    public Result<PageInfo<Distribute>> page(Distribute distribute) {
        String roleName = SysUserUtil.getSysUser().getRoleName();
        if("ROLE_TEACHER".equals(roleName)) {
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andUserIdEqualTo(SysUserUtil.getSysUser().getUserId());
            Teacher teacher = teacherMapper.selectByExample(teacherExample).get(0);
            distribute.setTeacherId(teacher.getId());
        }
        List<Distribute> distributeList = receiveService.page(distribute);
        com.github.pagehelper.PageInfo<Distribute> pageInfo = new com.github.pagehelper.PageInfo<>(distributeList);
        return Result.of(PageInfo.of(pageInfo));
    }

    @PostMapping("/history/page")
    public Result<PageInfo<Distribute>> historyPage(Distribute distribute) {
        String roleName = SysUserUtil.getSysUser().getRoleName();
        if("ROLE_TEACHER".equals(roleName)) {
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andUserIdEqualTo(SysUserUtil.getSysUser().getUserId());
            Teacher teacher = teacherMapper.selectByExample(teacherExample).get(0);
            distribute.setTeacherId(teacher.getId());
        }
        List<Distribute> distributeList = receiveService.historyPage(distribute);
        com.github.pagehelper.PageInfo<Distribute> pageInfo = new com.github.pagehelper.PageInfo<>(distributeList);
        return Result.of(PageInfo.of(pageInfo));
    }

    @PostMapping("/receive/{id}")
    public Result<Void> receive(@PathVariable Integer id) {
        receiveService.receive(id);
        return Result.of(null);
    }
}