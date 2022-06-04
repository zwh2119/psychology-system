package com.lyc.demo.service;

import com.lyc.demo.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyc
 * @since 2022-04-27
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
