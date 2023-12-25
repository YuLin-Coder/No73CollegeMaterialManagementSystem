package cn.huanzi.qch.baseadmin.sys.sysshortcutmenu.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SysShortcutMenuVo extends PageCondition implements Serializable {
    private Integer shortcutMenuId;//用户快捷菜单id

    private String shortcutMenuName;//用户快捷菜单名称

    private String shortcutMenuPath;//用户快捷菜单路径

    private Integer userId;//用户id

    private Integer shortcutMenuParentId;//上级id

    private Integer sortWeight;//同级排序权重：0-10

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private List<SysShortcutMenuVo> children = new ArrayList<>();//如果是父类，这里存孩子节点
}
