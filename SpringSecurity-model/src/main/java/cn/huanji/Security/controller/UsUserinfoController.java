package cn.huanji.Security.controller;


import cn.huanji.Security.model.UsUserinfo;
import cn.huanji.Security.service.UsUserinfoService;
import cn.huanji.api.CommonResult;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 猪肉佬
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/usUserinfo")
@ApiOperation(value = "用户表操作接口")
public class UsUserinfoController {

    @Autowired
    private UsUserinfoService usUserinfoService;

    /**
     * 登录接口
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @PostMapping
    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名"),
            @ApiImplicitParam(name = "password",value = "密码")
    })
    public ResponseEntity<JSON> login(String username, String password){
        String token = usUserinfoService.login(username, password);
        if (StrUtil.isEmpty(token)){
            return ResponseEntity.ok(JSONUtil.parse(CommonResult.loginFail("请检查用户名或密码")));
        }
        return ResponseEntity.ok(JSONUtil.parse(CommonResult.loginSuccess(token)));
    }

    /**
     * 注册接口
     * @param usUserinfo 用户信息
     * @return 操作结果
     */
    @PostMapping
    @ApiOperation(value = "注册接口")
    public ResponseEntity<JSON>  register(UsUserinfo usUserinfo){
        if (usUserinfoService.register(usUserinfo)){
            return ResponseEntity.ok(JSONUtil.parse(CommonResult.success("注册成功")));
        }
        return ResponseEntity.ok(JSONUtil.parse(CommonResult.failed("注册失败")));
    }
}

