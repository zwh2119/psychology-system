package com.lyc.demo.service;

import com.lyc.demo.controller.dto.UserDTO;
import com.lyc.demo.controller.dto.UserPasswordDTO;
import com.lyc.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyc
 * @since 2022-04-23
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

}
