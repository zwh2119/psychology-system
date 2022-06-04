package com.lyc.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘叶超~
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;//请求失败或者成功
    private String msg;//失败的原因
    private Object data;//请求后台数据

    public static com.lyc.demo.common.Result success() {
        return new com.lyc.demo.common.Result(Constants.CODE_200, "", null);
    }

    public static com.lyc.demo.common.Result success(Object data) {
        return new com.lyc.demo.common.Result(Constants.CODE_200, "", data);
    }

    public static com.lyc.demo.common.Result error(String code, String msg) {
        return new com.lyc.demo.common.Result(code, msg, null);
    }

    public static com.lyc.demo.common.Result error() {
        return new com.lyc.demo.common.Result(Constants.CODE_500, "系统错误", null);
    }
}
