package cn.huanzi.qch.baseadmin.sys.sysuserauthority.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.sys.sysuserauthority.pojo.SysUserAuthority;
import cn.huanzi.qch.baseadmin.sys.sysuserauthority.vo.SysUserAuthorityVo;

import java.util.List;

public interface SysUserAuthorityService extends CommonService<SysUserAuthorityVo, SysUserAuthority, Integer> {
    Result<List<SysUserAuthorityVo>> findByUserId(Integer userId);

    Result<Boolean> saveAllByUserId(Integer userId, String authorityIdList);
}
