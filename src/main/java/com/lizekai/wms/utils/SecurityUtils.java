package com.lizekai.wms.utils;

import com.lizekai.wms.domain.entity.LoginUser;
import com.lizekai.wms.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * 获取用户的userid
     **/
    public static LoginUser getLoginUser() {
        Object principal = getAuthentication().getPrincipal();
        if("anonymousUser".equals(principal)){
            return new LoginUser(new User(),null);
        }
        return (LoginUser) principal;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 指定userid为1的用户就是网站管理员
     * @return
     */
    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
