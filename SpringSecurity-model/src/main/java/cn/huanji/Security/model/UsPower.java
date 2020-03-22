package cn.huanji.Security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 猪肉佬
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("权限表")
public class UsPower implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限表id
     */
    @ApiModelProperty("权限表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级权限id
     */
    @ApiModelProperty("父级权限id")
    private Long pid;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String powername;

    /**
     * 权限值
     */
    @ApiModelProperty("权限值")
    private String powervalue;

    /**
     * 类型:0>第一菜单/目录;1>第二菜单;3>第三菜单;4>按钮
     */
    @ApiModelProperty("类型:0>第一菜单/目录;1>第二菜单;3>第三菜单;4>按钮")
    private Long type;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String inco;

    /**
     * 路径
     */
    @ApiModelProperty("路径")
    private String url;

    /**
     * 状态:1>默认有效;0>失效
     */
    @ApiModelProperty("状态:1>默认有效;0>失效")
    private Integer state;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createtime;


}
