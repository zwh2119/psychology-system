package com.lyc.demo.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.controller.dto.UserPasswordDTO;
import com.lyc.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyc
 * @since 2022-04-23
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);
}
