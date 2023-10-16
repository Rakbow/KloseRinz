package com.rakbow.kloserinz.data;

import com.alibaba.fastjson2.JSON;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-16 23:45
 * @Description:
 */
public class ApiResult {

    public int code;//操作代码
    public int state;//操作状态 0-失败 1-成功
    public Object data;//响应数据
    public String message;//错误信息

    public ApiResult() {
        this.state = 1;
        this.message = "";
    }

    public ApiResult(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public void setSuccessMessage(String message) {
        this.message = message;
    }

    public void setErrorMessage(String error) {
        this.state = 0;
        this.message = error;
    }

    public void setErrorMessage(Exception e) {
        this.state = 0;
        this.message = e.getMessage();
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public boolean isSuccess() {
        return this.state == 1;
    }

}
