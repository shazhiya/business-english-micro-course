package org.shazhi.businessEnglishMicroCourse.util;
import lombok.Data;
import lombok.experimental.Accessors;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Accessors(chain = true)
@Data
public class IdUser implements UserDetails {

    UserEntity userInfo;
    User user;

    public IdUser(String username, String password, Boolean userEnable, boolean accountNonExpired, boolean credentialsNonExpired,
                  boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.user = new User(username,password,userEnable,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
    }

    public IdUser(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
