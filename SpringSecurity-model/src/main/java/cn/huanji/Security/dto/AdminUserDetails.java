package cn.huanji.Security.dto;

import cn.huanji.Security.model.UsPower;
import cn.huanji.Security.model.UsUserinfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 封装用户详情对象
 */
public class AdminUserDetails implements UserDetails {

    private UsUserinfo userInfo;

    private List<UsPower> powerList;

    public AdminUserDetails(UsUserinfo userInfo,List<UsPower> powerList){
        this.userInfo = userInfo;
        this.powerList = powerList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return powerList.stream()
                .filter(usPower -> usPower.getPowervalue() != null)
                .map(usPower -> new SimpleGrantedAuthority(usPower.getPowervalue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

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
        return userInfo.getState().equals(1L);
    }
}
