package com.rockvine.example.bitwise;

/**
 * @author rocky
 * @date 2022-05-17 11:50
 * @description 位运算符
 */
public class Permission {

    // 是否允许查询，二进制第1位，0表示否，1表示是
    public static final int ALLOW_SELECT = 1; // 0001  = 1

    // 是否允许新增，二进制第2位，0表示否，1表示是
    public static final int ALLOW_INSERT = 1 << 1; // 0010  = 2

    // 是否允许修改，二进制第3位，0表示否，1表示是
    public static final int ALLOW_UPDATE = 1 << 2; // 0100  =4

    // 是否允许删除，二进制第4位，0表示否，1表示是
    public static final int ALLOW_DELETE = 1 << 3; // 1000  = 8

    // 存储目前的权限状态
    private int flag;

    /**
     * 设置用户的权限
     * @param per   权限
     */
    public void setPer(int per) {
        flag = per;
    }

    /**
     * 增加用户的权限（1个或者多个）
     * @param per   权限
     */
    public void enable(int per) {
        flag = flag | per;
    }

    /**
     * 删除用户的权限（1个或者多个）
     * @param per   权限
     */
    public void disable(int per) {
        flag = flag & ~per;
    }

    /**
     * 判断用户是否存在该权限
     * @param per   权限
     * @return  是否存在该权限
     */
    public boolean isAllow(int per) {
        return ((flag & per) == per);
    }

    /**
     * 判断用户是否无权限
     * @param per   权限
     * @return  是否没有该权限
     */
    public boolean isNotAllow(int per) {
        return ((flag & per) == 0);
    }

}
