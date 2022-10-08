package com.hxt.test.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sort {
    public static void main(String[] args) {
        LinkedHashMap<String, String> list = null;
        LinkedHashMap<String, String> stringStringLinkedHashMap = sortList(list);

    }


    // 数据完整性列表排序
    public static LinkedHashMap<String, String> sortList(LinkedHashMap<String, String> orderList) {
        LinkedHashMap<String, String> orderParams = new LinkedHashMap<>();
        List<String> colums = Arrays.asList("dkxBdzmc", "dkxXlmc", "dateTime", "sfkg", "sdsfwz",
                "loadNum", "nullNum", "lossPert", "totalKgNum", "zdhKgNum", "pnull", "inull");
        List<String> columsSql = Arrays.asList("DKX_BDZMC", "dkx_xlmc", "date", "sfkg", "sdsfwz",
                "load_num", "null_num", "loss_pert", "total_kg_num", "zdh_kg_num", "p_null", "i_null");
        for (Map.Entry<String, String> entry : orderList.entrySet()) {
            if (colums.contains(entry.getKey())) {
                if (entry.getValue() != null && !entry.getValue().equals("")) {
                    orderParams.put(columsSql.get(colums.indexOf(entry.getKey())),
                            entry.getValue().toLowerCase().contains("asc") ? "ASC" : "DESC");
                }
            }
        }
        return orderParams;
    }

}
