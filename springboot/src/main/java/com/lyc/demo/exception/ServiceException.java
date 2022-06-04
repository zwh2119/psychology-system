package com.lyc.demo.exception;

import lombok.Getter;

/**
 * @author 刘叶超~
 * @version 1.0
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
