package com.rockvine.kernel.core.model.result;

import com.rockvine.kernel.core.enums.ErrorCodeEnum;
import com.rockvine.kernel.core.model.BaseModel;

/**
 * @author rocky
 * @date 2022-05-20 22:41
 * @description 请求结果类
 */
public class CallResult<T extends BaseModel> extends BaseResult {
    private static final long serialVersionUID = -1663895267150635360L;

    /**
     * 处理结果
     */
    private T value;

    /**
     * 构建请求成功结果
     * @param value 处理结果
     * @param <T>   结果类型
     * @return  返回结果
     */
    public static <T extends BaseModel> CallResult<T> buildSuccessResult(T value) {
        CallResult<T> result = new CallResult<>();
        result.value = value;
        return result;
    }

    /**
     * 构建请求失败的结果
     * @param errorCodeEnum 错误码
     * @return 返回结果
     */
    public static <T extends BaseModel> CallResult<T> buildFailResult(ErrorCodeEnum errorCodeEnum) {
        CallResult<T> result = new CallResult<>();
        result.success = false;
        result.errorCode = errorCodeEnum.getCode();
        result.errorMsg = errorCodeEnum.getDesc();
        return result;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
