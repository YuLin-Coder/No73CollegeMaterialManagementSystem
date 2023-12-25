package cn.huanzi.qch.baseadmin.sys.sysuserauthority.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import cn.huanzi.qch.baseadmin.sys.sysauthority.vo.SysAuthorityVo;
import cn.huanzi.qch.baseadmin.sys.sysuser.vo.SysUserVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserAuthorityVo extends PageCondition implements Serializable {
    private Integer userAuthorityId;//用户权限表id

    private Integer userId;//用户id

    private Integer authorityId;//权限id

    private SysUserVo sysUser;//用户

    private SysAuthorityVo sysAuthority;//权限

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private String authorityIdList;//新增、修改用户权限时权限id集合
}
