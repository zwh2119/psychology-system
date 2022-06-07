package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@TableName("notice")
public class Notice {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String user;

    @TableField(value = "publish_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date publishTime;

    private String tittle;

    private String content;
}
