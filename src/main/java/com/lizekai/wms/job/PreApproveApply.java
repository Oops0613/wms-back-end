package com.lizekai.wms.job;

import com.lizekai.wms.domain.entity.LoginUser;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PreApproveApply {
    @Autowired
    private RecordService recordService;
    @Scheduled(cron= "0/30 * * * * ?")//30s执行一次
    public void preApprove(){
        //由于定时任务是独立的线程，无法获取当前上下文的用户信息
        //需要另外创建一个临时的Authentication
        LoginUser loginUser=new LoginUser(new User(),null);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        loginUser,
                        null,
                        AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")
                )
        );
        try{
            recordService.preApprove();
        }
        finally {
            SecurityContextHolder.clearContext();
        }
    }
}
