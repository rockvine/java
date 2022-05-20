package com.rockvine.kernel.core.model.result;

import com.rockvine.kernel.core.model.AbstractPersistable;

/**
 * @author rocky
 * @date 2022-05-20 10:43
 * @description 基础结果类
 */
public class BaseResult extends AbstractPersistable {
    private static final long serialVersionUID = -7941819669694099902L;

    /**
     * 是否处理成功
     */
    protected boolean success = true;
    /**
     * 错误码
     */
    protected int errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    /**
     * 构建请求成功结果
     * @return 返回结果
     */
    public static BaseResult buildSuccessResult() {
        BaseResult result = new BaseResult();
        result.success = true;
        return result;
    }

    /**
     * 构建请求失败的结果
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     * @return  返回结果
     */
    public static BaseResult buildFailResult(int errorCode, String errorMsg) {
        BaseResult result = new BaseResult();
        result.success = false;
        result.errorCode = errorCode;
        result.errorMsg = errorMsg;
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
