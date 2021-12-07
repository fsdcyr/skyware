package com.fsdcyr.sky.authorization.config;

import com.fsdcyr.sky.authorization.context.AuthContextGenerator;
import com.fsdcyr.sky.common.exception.SkywareException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-12-07 4:48 下午
 */
@Slf4j
public class AuthenticationInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // 启动时检查
        log.info("》》》》Authentication启动时检查开始");
        try {
            applicationContext.getBean(AuthContextGenerator.class);
        } catch (BeansException beansException) {
            log.error("", beansException);
            throw new SkywareException("必须声明com.fsdcyr.sky.authorization.context.AuthContextGenerator");
        } finally {
            log.info("《《《《 Authentication启动时检查结束");
        }
    }

}
