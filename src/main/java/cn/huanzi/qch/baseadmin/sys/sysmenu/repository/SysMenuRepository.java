package cn.huanzi.qch.baseadmin.sys.sysmenu.repository;

import cn.huanzi.qch.baseadmin.common.repository.*;
import cn.huanzi.qch.baseadmin.sys.sysmenu.pojo.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuRepository extends CommonRepository<SysMenu, Integer> {
    List<SysMenu> findByMenuNameIn(List<String> list);

}
