package cn.huanzi.qch.baseadmin.util;

import cn.huanzi.qch.baseadmin.sys.sysuser.vo.SysUserVo;

import java.util.concurrent.ConcurrentHashMap;

public class SysUserUtil {

    //使用线程安全的ConcurrentHashMap来存储系统设置
    private static ConcurrentHashMap<String, SysUserVo> sysUserMap = new ConcurrentHashMap<>();

    //从公用静态集合sysUserMap获取系统设置
    public static SysUserVo getSysUser(){
        return sysUserMap.get("sysUser");
    }

    //更新公用静态集合sysUserMap
    public static void setSysUserMap(SysUserVo sysUserVo){
        if(sysUserMap.isEmpty()){
            sysUserMap.put("sysUser",sysUserVo);
        }else{
            sysUserMap.replace("sysUser",sysUserVo);
        }
    }
}
