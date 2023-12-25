package cn.huanzi.qch.baseadmin.sys.sysauthority.repository;

import cn.huanzi.qch.baseadmin.common.repository.*;
import cn.huanzi.qch.baseadmin.sys.sysauthority.pojo.SysAuthority;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAuthorityRepository extends CommonRepository<SysAuthority, Integer> {
    SysAuthority findByAuthorityName(String role_user);
}
