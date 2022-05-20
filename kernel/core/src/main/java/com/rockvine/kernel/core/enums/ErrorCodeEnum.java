package com.rockvine.kernel.core.enums;

/**
 * @author rocky
 * @date 2022-05-20 22:42
 * @description 系统错误码
 */
public enum ErrorCodeEnum {

    SUCCESS(0, "成功"),
    SYSTEM_ERROR(-1, "系统错误"),

    PROCESS_FAIL(100, "处理失败，请重试"),
    PARAM_ERROR(101, "参数错误"),
    PERMISSION_DENY(102, "权限不足"),
    UNKNOWN_ERROR(103, "未知错误"),

    ;

    private final int code;
    private final String desc;

    ErrorCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
