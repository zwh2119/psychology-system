package com.lyc.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.demo.common.Constants;
import com.lyc.demo.controller.dto.UserDTO;
import com.lyc.demo.controller.dto.UserPasswordDTO;
import com.lyc.demo.entity.Menu;
import com.lyc.demo.entity.User;
import com.lyc.demo.exception.ServiceException;
import com.lyc.demo.mapper.RoleMapper;
import com.lyc.demo.mapper.RoleMenuMapper;
import com.lyc.demo.mapper.UserMapper;
import com.lyc.demo.service.IMenuService;
import com.lyc.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.demo.utils.TokenUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyc
 * @since 2022-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }

//        List<User> list = list(queryWrapper);
//        return list.size() != 0;
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one == null) {
            one = new User();

            BeanUtil.copyProperties(userDTO, one, true);
            one.setPassword(bCryptPasswordEncoder.encode(one.getPassword()));
            save(one); //把 copy完之后的用户对象存储到数据库
        } else {
            throw  new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return null;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {

//        userPasswordDTO.setPassword(bCryptPasswordEncoder.encode(userPasswordDTO.getPassword()));
//        userPasswordDTO.setNewPassword(bCryptPasswordEncoder.encode(userPasswordDTO.getNewPassword()));



        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userPasswordDTO.getUsername());
        User one = getOne(queryWrapper);
        final boolean matches = bCryptPasswordEncoder.matches(userPasswordDTO.getPassword(), one.getPassword());
        if(!matches)
            throw new ServiceException(Constants.CODE_600, "密码错误");
        one.setPassword(bCryptPasswordEncoder.encode(userPasswordDTO.getNewPassword()));
        saveOrUpdate(one);
        //        int update = userMapper.updatePassword(userPasswordDTO);
//        if (update < 1) {
//            throw new ServiceException(Constants.CODE_600, "密码错误");
//        }
    }


    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());

//        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息
            if(one==null)
                return one;

            final boolean matches = bCryptPasswordEncoder.matches(userDTO.getPassword(), one.getPassword());
            if(!matches)
                one = null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(Constants.CODE_500, "系统错误");

        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for(Menu menu : menus) {
            if(menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf() 移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
