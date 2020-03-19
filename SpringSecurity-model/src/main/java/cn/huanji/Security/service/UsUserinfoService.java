package cn.huanji.Security.service;

import cn.huanji.Security.model.UsPower;
import cn.huanji.Security.model.UsUserinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 猪肉佬
 * @since 2020-03-18
 */
public interface UsUserinfoService extends IService<UsUserinfo> {


    /**
     * 根据用户名查找用户
     */
    UsUserinfo getUserByUsername(String username);

    /**
     * 根据用户id查找用户权限
     */
    List<UsPower> getUspowerByUserId(String userId);
}
