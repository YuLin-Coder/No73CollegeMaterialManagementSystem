package cn.huanzi.qch.baseadmin.sys.sysuser.vo;

import cn.huanzi.qch.baseadmin.annotation.Like;
import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserVo extends PageCondition implements Serializable {
    private Integer userId;//用户id

    private String img;

    private String loginName;
    @Like
    private String userName;

    private String password;

    private String valid;

    private Date expiredTime;

    private Date lastChangePwdTime;

    private Date createTime;

    private Date updateTime;

    private String roleName;

    private String oldPassword;
    private String newPassword;
}
