package cn.huanji.Security.dao;

import cn.huanji.Security.model.UsPower;
import cn.huanji.Security.model.UsUserinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 猪肉佬
 * @since 2020-03-18
 */
public interface UsUserinfoMapper extends BaseMapper<UsUserinfo> {

    /**
     * 根据用户id查询该用户权限列表
     */
    List<UsPower> getUsPowerByUserId(@Param("userId")String userId);

}
