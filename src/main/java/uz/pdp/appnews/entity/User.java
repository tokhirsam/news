package uz.pdp.appnews.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appnews.entity.enums.Permissions;
import uz.pdp.appnews.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails{
    @Column(nullable = false)
    private String fullName;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean enabled;
    private boolean AccountNonExpired = true;
    private boolean AccountNonLocked = true;
    private boolean CredentialsNonExpired = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Permissions> permissionsList = this.role.getPermissionsList();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permissions permissions : permissionsList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permissions.name()));
        }
        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
