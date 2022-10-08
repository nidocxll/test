//package com.hxt.test.controller;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class xuexi {
//    public List<NewEnergyCityYdfhDayDto> selectYearCityDsl(String month, String energyType) {
//        List<NewEnergyCityYdfhDayDto> list = newEnergyCityYdfhDayMapper.selectSeasonCityDslAvg(month, energyType);
//        if (list.isEmpty()) {
//            return new ArrayList<>();
//        }
//        List<NewEnergyCityYdfhDayDto> resultList = new ArrayList<>();
//        Map<String, List<NewEnergyCityYdfhDayDto>> cityMap = list.stream()
//                .collect(Collectors.groupingBy(NewEnergyCityYdfhDayDto::getId));
//        for (List<NewEnergyCityYdfhDayDto> city : cityMap.values()) {
//            NewEnergyCityYdfhDayDto dto = new NewEnergyCityYdfhDayDto();
//            dto.setId(city.get(0).getId());
//            dto.setCity(city.get(0).getCity());
//            Map<String, List<NewEnergyCityYdfhDayDto>> seasonMap = city.stream()
//                    .collect(Collectors.groupingBy(NewEnergyCityYdfhDayDto::getSeason));
//            // 全年平均同时率
//            BigDecimal yearDslAvg = BigDecimal.valueOf(0);
//            for (List<NewEnergyCityYdfhDayDto> season : seasonMap.values()) {
//                BigDecimal seasonAvg = BigDecimal.valueOf(0);
//                for (NewEnergyCityYdfhDayDto o : season) {
//                    yearDslAvg = yearDslAvg.add(o.getDslAvg());
//                    seasonAvg = seasonAvg.add(o.getDslAvg());
//                }
//                seasonAvg = seasonAvg.divide(BigDecimal.valueOf(season.size()), 2, BigDecimal.ROUND_HALF_UP);
//                switch (season.get(0).getSeason()) {
//                    case "春季":
//                        dto.setSpringDslAvg(seasonAvg);
//                        break;
//                    case "夏季":
//                        dto.setSummerDslAvg(seasonAvg);
//                        break;
//                    case "秋季":
//                        dto.setAutumnDslAvg(seasonAvg);
//                        break;
//                    case "冬季":
//                        dto.setWinterDslAvg(seasonAvg);
//                        break;
//                }
//            }
//            dto.setYearDslAvg(yearDslAvg.divide(BigDecimal.valueOf(city.size()), 2, BigDecimal.ROUND_HALF_UP));
//            resultList.add(dto);
//        }
//        return resultList;
//    }
//}
