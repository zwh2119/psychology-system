package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("test_record")
public class Record {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField(value = "publish_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date publishTime;

    private Integer score;

    private String result;

}
