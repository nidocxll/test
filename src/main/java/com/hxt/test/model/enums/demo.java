package com.hxt.test.model.enums;

public enum demo {

    FALSE(0, "否"),
    TRUE(1, "是");

    private Integer num;
    private String msg;

    public Integer getNum() {
        return num;
    }

    public String getMsg() {
        return msg;
    }

    demo(Integer num, String msg) {
        this.num = num;
        this.msg = msg;
    }

    public static String fromTypeName(Object typeName) {

        for (demo type : demo.values()) {
            if (type.getNum().equals(typeName)) {
                return type.getMsg();
            }
        }
        return "";
    }
}