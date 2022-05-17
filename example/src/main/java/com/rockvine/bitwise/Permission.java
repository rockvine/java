package com.rockvine.bitwise;

/**
 * @author rocky
 * @date 2022-05-17 11:50
 * @description 位运算符
 */
@SuppressWarnings("unused")
public class Permission {

    // 是否允许查询，二进制第1位，0表示否，1表示是
    @SuppressWarnings("all")
    public static final int ALLOW_SELECT = 1 << 0; // 0001  = 1

    // 是否允许新增，二进制第2位，0表示否，1表示是
    public static final int ALLOW_INSERT = 1 << 1; // 0010  = 2

    // 是否允许修改，二进制第3位，0表示否，1表示是
    public static final int ALLOW_UPDATE = 1 << 2; // 0100  =4

    // 是否允许删除，二进制第4位，0表示否，1表示是
    public static final int ALLOW_DELETE = 1 << 3; // 1000  = 8

    // 存储目前的权限状态
    private int flag;

    //设置用户的权限
    public void setPer(int per) {
        flag = per;
    }

    //增加用户的权限（1个或者多个）
    public void enable(int per) {
        flag = flag | per;
    }

    //删除用户的权限（1个或者多个）
    public void disable(int per) {
        flag = flag & ~per;
    }

    //判断用户的权限
    public boolean isAllow(int per) {
        return ((flag & per) == per);
    }

    //判断用户没有的权限
    public boolean isNotAllow(int per) {
        return ((flag & per) == 0);
    }


    public static void main(String[] args) {
        //默认给curd全部权限
        int flag = 15;

        Permission permission = new Permission();
        permission.setPer(flag);

        //删除其插入及删除权限
        permission.disable(ALLOW_INSERT | ALLOW_DELETE);

        //查询其是否含有curd等权限
        System.out.println("select = " + permission.isAllow(ALLOW_SELECT));   //true
        System.out.println("update = " + permission.isAllow(ALLOW_UPDATE));   //true
        System.out.println("insert = " + permission.isAllow(ALLOW_INSERT));   //false
        System.out.println("delete = " + permission.isAllow(ALLOW_DELETE));   //false

    }
}
