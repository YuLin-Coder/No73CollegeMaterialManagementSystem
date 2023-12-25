package cn.huanzi.qch.baseadmin.sys.sysuser.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_user")
@Data
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//自增
    private Integer userId;//用户id

    private String img;

    private String loginName;

    private String userName;

    private String password;

    private String valid;

    private Date expiredTime;

    private Date lastChangePwdTime;

    private Date createTime;

    private Date updateTime;

}
