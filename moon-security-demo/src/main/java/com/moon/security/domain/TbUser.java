package com.moon.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2019-12-25 10:09
 */
@Data
public class TbUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String roles;
    private boolean enable;
    private List<GrantedAuthority> authorities;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
