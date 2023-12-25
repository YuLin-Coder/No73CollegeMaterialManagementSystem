package cn.huanzi.qch.baseadmin.sys.sysshortcutmenu.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_shortcut_menu")
@Data
public class SysShortcutMenu implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//自增
    private Integer shortcutMenuId;//用户快捷菜单id

    private String shortcutMenuName;//用户快捷菜单名称

    private String shortcutMenuPath;//用户快捷菜单路径

    private Integer userId;//用户id

    private String shortcutMenuParentId;//上级id

    private Integer sortWeight;//同级排序权重：0-10

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

}
