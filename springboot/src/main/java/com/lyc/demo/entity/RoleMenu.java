package com.lyc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘叶超~
 * @version 1.0
 */
@TableName("sys_role_menu")
@Data
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;
}
