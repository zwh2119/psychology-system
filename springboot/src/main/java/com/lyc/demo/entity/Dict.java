package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘叶超~
 * @version 1.0
 */
@TableName("sys_dict")
@Data
public class Dict {

    private String name;
    private String value;
    private String type;
}
