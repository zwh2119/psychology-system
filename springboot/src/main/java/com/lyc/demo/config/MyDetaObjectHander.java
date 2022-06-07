package com.lyc.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * 自动生成时间
 */
@Component
public class MyDetaObjectHander implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("publishTime", metaObject);
        if(ObjectUtils.isEmpty(createTime)) {
            this.setFieldValByName("publishTime",new Date(),metaObject);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("publishTime",new Date(),metaObject);

    }
}
