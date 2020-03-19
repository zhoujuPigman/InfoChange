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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
