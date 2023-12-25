package cn.huanzi.qch.baseadmin.sys.sysmenu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_menu")
@Data
public class SysMenu implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//自增
    private Integer menuId;//菜单id

    private String menuName;//菜单名称

    private String menuPath;//菜单路径

    private Integer menuParentId;//上级id

    private Integer sortWeight;//同级排序权重：0-10

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

}
