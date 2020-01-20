package com.springboot.robot.modules.common.controller;

import com.springboot.robot.modules.common.entity.BaseBean;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Connor 2019年11月20日
 * Controller公共组件
 * 没有删除需求的时候继承这个类
 */
@RestController
public class BaseController {

    public Long getUserId() {
        return null;
    }

    public BaseBean getUser() {
        return null;
    }
}
