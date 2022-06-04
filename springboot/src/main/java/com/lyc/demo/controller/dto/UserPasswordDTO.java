package com.lyc.demo.controller.dto;

import lombok.Data;

/**
 * @author 刘叶超~
 * @version 1.0
 */
@Data
public class UserPasswordDTO {
    private String username;
    private String phone;
    private String password;
    private String newPassword;
}
