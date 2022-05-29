package com.rockvine.kernel.core.model.result;

import com.rockvine.kernel.core.enums.ErrorCodeEnum;
import com.rockvine.kernel.core.model.BaseModel;

import java.util.List;

/**
 * @author rocky
 * @date 2022-05-20 09:49
 * @description 分页结果类
 */
public class PageResult<T extends BaseModel> extends BaseResult {
    private static final long serialVersionUID = -7825579749875609254L;

    /**
     * 处理结果
     */
    private List<T> list;
    /**
     * 分页页码
     */
    private int pageNo;
    /**
     * 分页页数
     */
    private int pageSize;
    /**
     * 是否存在下一页
     */
    private boolean hasNext;
    /**
     * 结果数量
     */
    private int total;

    /**
     * 构建请求成功结果
     *
     * @param list 处理结果
     * @param <T>   结果类型
     * @return  返回结果
     */
    public static <T extends BaseModel> PageResult<T> buildSuccessResult(List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.list = list;
        return result;
    }

    /**
     * 构建请求失败的结果
     *
     * @param errorCodeEnum 错误码
     * @return 返回结果
     */
    public static <T extends BaseModel> PageResult<T> buildFailResult(ErrorCodeEnum errorCodeEnum) {
        PageResult<T> result = new PageResult<>();
        result.success = false;
        result.errorCode = errorCodeEnum.getCode();
        result.errorMsg = errorCodeEnum.getDesc();
        return result;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
