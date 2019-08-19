package com.core.shiro;

import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.mvc.entity.Permission;
import com.mvc.entity.Role;
import com.mvc.entity.User;
import com.mvc.service.IPermissionService;
import com.mvc.service.IRoleService;
import com.mvc.service.IUserService;
public class CustomRealm extends AuthorizingRealm{  
    @Resource
    private IUserService userService;
    @Resource
    private IPermissionService permissionService;
    @Resource
    private IRoleService roleService;
    /**
     * 添加角色
     * @param username
     * @param info
     */
    private void addRole(String username, SimpleAuthorizationInfo info) {
    	List<Role> roles = roleService.findByUser(username);
		if(roles!=null&&roles.size()>0){
			for (Role role : roles) {
			    info.addRole(role.getRoleName());
			}
		}
    }

    /**
     * 添加权限
     * @param username
     * @param info
     * @return
     */
    private SimpleAuthorizationInfo addPermission(String username,SimpleAuthorizationInfo info) {
		List<Permission> permissions = permissionService.findPermissionByName(username);
		for (Permission permission : permissions) {
		    info.addStringPermission(permission.getUrl());//添加权限  
		}
		return info;  
    }  
  
    
    /**
     * 获取授权信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        //用户名  
        String username = (String) principals.fromRealm(getName()).iterator().next(); 
        //根据用户名来添加相应的权限和角色
        if(!StringUtils.isEmpty(username)){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            addPermission(username,info);
            addRole(username, info);
            return info;
        }
        return null;  
    }

   
   /** 
    * 登录验证 
    */  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken ) throws AuthenticationException {  
        //令牌——基于用户名和密码的令牌  
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        //令牌中可以取出用户名密码  
        String accountName = token.getUsername();
        //让shiro框架去验证账号密码
        if(!StringUtils.isEmpty(accountName)){
            User user = userService.findUser(accountName);
            if(user != null){
        	return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
            }
        }
        
        return null;
    }  
  
}  
