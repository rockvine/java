package com.rockvine.oom;

/**
 * @author rocky
 * @date 2022-05-17 22:53
 * @description 栈溢出
 */
@SuppressWarnings("all")
public class StackSOF {
    /*
     * 可添加JVM参数限制，如：-Xss512k
     */
    public static void main(String[] args) {
        StackSOF.testStackLeak1();

//        StackSOF.testStackLeak2();
    }


    private int stackLength = 1;

    private static void testStackLeak1() {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.stackLeak1();
        } catch (Throwable e) {
            System.out.println(stackSOF.stackLength);
            e.printStackTrace();
        }
    }

    private void stackLeak1(){
        stackLength++;
        stackLeak1();
    }



    private static void testStackLeak2() {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.stackLeak2("123456789123456789", "abcdefgabcdefgabcdefgabcdefg");
        } catch (Throwable e) {
            System.out.println(stackSOF.stackLength);
            e.printStackTrace();
        }
    }

    private void stackLeak2(String arg1, String arg2) {
        stackLength++;
        stackLeak2(arg1, arg2);
    }
}