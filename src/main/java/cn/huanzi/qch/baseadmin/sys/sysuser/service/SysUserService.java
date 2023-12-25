package cn.huanzi.qch.baseadmin.sys.sysuser.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.sys.sysuser.pojo.SysUser;
import cn.huanzi.qch.baseadmin.sys.sysuser.vo.SysUserVo;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public interface SysUserService extends CommonService<SysUserVo, SysUser, Integer> {
    Result<SysUserVo> findByLoginName(String username);
    Result<SysUserVo> resetPassword(Integer userId);
    PersistentTokenRepository getPersistentTokenRepository2();
}
