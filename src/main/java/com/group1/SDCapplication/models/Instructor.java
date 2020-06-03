package com.group1.SDCapplication.models;

public class Instructor {
    private String instuctor_name;
    private Long instuctor_number;

    public Instructor(Long instuctor_number,String instuctor_name) {
        this.instuctor_name = instuctor_name;
        this.instuctor_number = instuctor_number;
    }

    public String getInstuctor_name() {
        return instuctor_name;
    }

    public void setInstuctor_name(String instuctor_name) {
        this.instuctor_name = instuctor_name;
    }

    public Long getInstuctor_number() {
        return instuctor_number;
    }

    public void setInstuctor_number(Long instuctor_number) {
        this.instuctor_number = instuctor_number;
    }
}
