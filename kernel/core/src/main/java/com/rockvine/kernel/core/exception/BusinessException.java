package com.rockvine.kernel.core.exception;

import com.rockvine.kernel.core.enums.ErrorCodeEnum;

/**
 * @author rocky
 * @date 2022-05-20 22:58
 * @description 业务异常类
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 3171750604383407149L;

    private int errorCode = ErrorCodeEnum.SYSTEM_ERROR.getCode();

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc());
        this.errorCode = errorCodeEnum.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
