package com.hxt.test.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int id;
    private String name;
    private String password;
    private String sfkg;
    private Date brithday;

    public String getSfkg() {
        if (sfkg != "" && Integer.valueOf(sfkg) == 0) {
            return "否";
        } else if (sfkg != "" && Integer.valueOf(sfkg) == 1) {
            return "是";
        } else {
            return sfkg;
        }
    }
}
