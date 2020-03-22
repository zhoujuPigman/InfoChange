package cn.huanji.Security.service.impl;

import cn.huanji.Security.model.UsPower;
import cn.huanji.Security.model.UsUserinfo;
import cn.huanji.Security.dao.UsUserinfoMapper;
import cn.huanji.Security.service.UsUserinfoService;
import cn.huanji.utils.JWTTokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 猪肉佬
 * @since 2020-03-18
 */
@Service
public class UsUserinfoServiceImpl extends ServiceImpl<UsUserinfoMapper, UsUserinfo> implements UsUserinfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsUserinfoServiceImpl.class);

    private UsUserinfoMapper usUserinfoMapper;

    @Autowired
    private UserDetailsService adminUserDetailService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsUserinfo getUserByUsername(String username) {
        QueryWrapper<UsUserinfo> qw = new QueryWrapper<>();
        qw.eq("username",username);
        return baseMapper.selectOne(qw);
    }

    @Override
    public List<UsPower> getUspowerByUserId(String userId) {
        return baseMapper.getUsPowerByUserId(userId);
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        try{
            UserDetails userDetails = adminUserDetailService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(UsUserinfo usUserinfo) {
        //要判断是否有用户名重复
        QueryWrapper<UsUserinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",usUserinfo.getUsername());
        List<UsUserinfo> list = baseMapper.selectList(queryWrapper);
        if (list.size() > 0){
            return false;
        }
        String encodePass = passwordEncoder.encode(usUserinfo.getPassword());
        usUserinfo.setPassword(encodePass);
        int resultcode = baseMapper.insert(usUserinfo);
        if (resultcode > 0){
            return true;
        }
        return false;
    }
}
