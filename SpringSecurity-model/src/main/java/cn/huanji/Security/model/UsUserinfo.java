package cn.huanji.Security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 猪肉佬
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UsUserinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 上次登录时间
     */
    private LocalDate lasttime;

    /**
     * 状态
     */
    private Long state;


}
