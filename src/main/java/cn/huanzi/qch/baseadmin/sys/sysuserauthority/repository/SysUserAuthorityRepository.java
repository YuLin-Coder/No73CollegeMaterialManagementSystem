package cn.huanzi.qch.baseadmin.sys.sysuserauthority.repository;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.sys.sysuserauthority.pojo.SysUserAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserAuthorityRepository extends CommonRepository<SysUserAuthority, Integer> {
    List<SysUserAuthority> findByUserId(Integer userId);

    SysUserAuthority findFirstByUserId(Integer userId);
}
