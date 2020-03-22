package cn.huanji.Security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户表信息")
public class UsUserinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @ApiModelProperty(value = "用户表id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty("电子邮箱")
    private String email;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间;数据库已经设置默认插入时间戳")
    private LocalDateTime createtime;

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上一次登录时间,默认为空")
    private LocalDate lasttime;

    /**
     * 状态
     */
    @ApiModelProperty("账号可用状态;1>可用;0>不可用")
    private Long state;


}
