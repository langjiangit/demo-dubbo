package com.wz.process;

import lombok.Getter;

/**
 * wangzhen23
 * 2018/11/8.
 */
@Getter
public class Person {
    private String id;
    private Integer age;
    private Son son;
    private String telephone;

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
