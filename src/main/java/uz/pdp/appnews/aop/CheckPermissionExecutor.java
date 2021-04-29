package uz.pdp.appnews.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnews.entity.User;
import uz.pdp.appnews.exception.ForbiddenExceptions;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMethod( CheckPermission checkPermission){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exists = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())){
                exists = true;
                break;
            }
        }
        if (!exists) throw new ForbiddenExceptions(checkPermission.value(), "You are not authorized to complete this transaction");
    }
}



