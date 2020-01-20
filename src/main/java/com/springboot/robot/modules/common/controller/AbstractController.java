package com.springboot.robot.modules.common.controller;

import com.springboot.robot.common.annotation.WebLog;
import com.springboot.robot.common.utils.Result;
import com.springboot.robot.modules.common.entity.BaseBean;
import com.springboot.robot.modules.common.example.BaseExample;
import com.springboot.robot.modules.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author Connor 2019年11月20日
 * Controller公共组件
 * 其中特例集成了通用删除，有删除需求的集成这个类即可
 */
@RestController
public abstract class AbstractController<T extends BaseBean, E extends BaseExample> extends BaseController {

    @Autowired
    private BaseService<T, E> baseService;

    @WebLog
    @PostMapping("/delete")
    public Result delete(@NotNull(message = "id不能为空") Long id) {
        baseService.delete(id);
        return Result.ok();
    }
}
