package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@TableName("message")
public class Message {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private String parentNickname;

    private String content;

    @TableField(exist = false)
    private List<Message> children;

    @TableField(value = "publish_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date publishTime;

    @TableField(value = "reply_id")
    private Integer replyId;

    private String nickname;

}
