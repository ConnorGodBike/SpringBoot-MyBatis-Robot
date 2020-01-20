package com.springboot.robot.modules.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderJob {

    /**
     * 重置库存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @XxlJob("resetStock")
    public ReturnT<String> resetStock(String nothing) {
        return ReturnT.SUCCESS;
    }

}
