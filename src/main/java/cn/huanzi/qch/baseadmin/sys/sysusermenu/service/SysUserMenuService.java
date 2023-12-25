package cn.huanzi.qch.baseadmin.sys.sysusermenu.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.sys.sysmenu.vo.SysMenuVo;
import cn.huanzi.qch.baseadmin.sys.sysusermenu.pojo.SysUserMenu;
import cn.huanzi.qch.baseadmin.sys.sysusermenu.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, Integer> {
    Result<List<SysMenuVo>> findByUserId(Integer userId);

    Result<Boolean> saveAllByUserId(Integer userId, String menuIdList);
}
