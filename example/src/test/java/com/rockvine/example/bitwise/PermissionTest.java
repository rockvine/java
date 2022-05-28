package com.rockvine.example.bitwise;

/**
 * @author rocky
 * @date 2022-05-19 21:28
 * @description 位运算符测试类
 */
public class PermissionTest {
    public static void main(String[] args) {
        // 默认给curd全部权限
        int flag = 15;

        Permission permission = new Permission();
        permission.setPer(flag);

        // 删除其新增及删除权限
        permission.disable(Permission.ALLOW_INSERT | Permission.ALLOW_DELETE);

        // 查询其是否含有curd等权限
        System.out.println("select = " + permission.isAllow(Permission.ALLOW_SELECT));   //true
        System.out.println("update = " + permission.isAllow(Permission.ALLOW_UPDATE));   //true
        System.out.println("insert = " + permission.isAllow(Permission.ALLOW_INSERT));   //false
        System.out.println("delete = " + permission.isAllow(Permission.ALLOW_DELETE));   //false
    }
}
