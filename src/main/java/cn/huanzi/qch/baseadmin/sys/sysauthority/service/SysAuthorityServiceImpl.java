package cn.huanzi.qch.baseadmin.sys.sysauthority.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.config.security.MyFilterInvocationSecurityMetadataSource;
import cn.huanzi.qch.baseadmin.sys.sysauthority.pojo.SysAuthority;
import cn.huanzi.qch.baseadmin.sys.sysauthority.repository.SysAuthorityRepository;
import cn.huanzi.qch.baseadmin.sys.sysauthority.vo.SysAuthorityVo;
import cn.huanzi.qch.baseadmin.sys.sysuserauthority.service.SysUserAuthorityService;
import cn.huanzi.qch.baseadmin.sys.sysuserauthority.vo.SysUserAuthorityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SysAuthorityServiceImpl extends CommonServiceImpl<SysAuthorityVo, SysAuthority, Integer> implements SysAuthorityService{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysAuthorityRepository sysAuthorityRepository;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @Autowired
    private SysUserAuthorityService sysUserAuthorityService;

    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    /**
     * 重写save方法，当新增、修改权限表后需要去更新权限集合
     */
    @Override
    public Result<SysAuthorityVo> save(SysAuthorityVo entityVo) {
        Result<SysAuthorityVo> result = super.save(entityVo);

        //更新权限集合
        List<SysAuthorityVo> authorityVoList = sysAuthorityService.list(new SysAuthorityVo()).getData();
        myFilterInvocationSecurityMetadataSource.setRequestMap(authorityVoList);
        return result;
    }

    /**
     * 重写delete方法
     */
    @Override
    public Result<Integer> delete(Integer id) {
        //删除权限之前，删除用户权限关联表对应数据
        SysUserAuthorityVo sysUserAuthorityVo = new SysUserAuthorityVo();
        sysUserAuthorityVo.setAuthorityId(id);
        sysUserAuthorityService.list(sysUserAuthorityVo).getData().forEach((vo)->{
            sysUserAuthorityService.delete(vo.getUserAuthorityId());
        });

        //再删除自己
        Result<Integer> result = super.delete(id);

        //更新权限集合
        List<SysAuthorityVo> authorityVoList = sysAuthorityService.list(new SysAuthorityVo()).getData();
        myFilterInvocationSecurityMetadataSource.setRequestMap(authorityVoList);
        return result;
    }
}
