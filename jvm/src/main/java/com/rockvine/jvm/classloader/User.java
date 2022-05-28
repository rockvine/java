package com.rockvine.jvm.classloader;

import com.rockvine.kernel.core.model.BaseModel;

/**
 * @author rocky
 * @date 2022-05-18 22:46
 * @description 自定义类加载器，需加密用户实体类源码
 */
public class User extends BaseModel {
    private static final long serialVersionUID = 8754571892906447021L;

    private String name = "Rocky";
    private int age = 18;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
