package org.knight.infrastructure.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 19:12
 */
public class DateUtil {

    public static List<String> generateDateRange(String start, String end) {
        if (start == null || end == null) {
            return null;
        }
        DateTimeFormatter formatter = NftConstants.SIMPLE_DATE_FORMAT;
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        List<String> dateList = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            dateList.add(startDate.format(formatter));
            startDate = startDate.plusDays(1);
        }
        return dateList;
    }

    public static String getToday() {
        DateTimeFormatter formatter = NftConstants.SIMPLE_DATE_FORMAT;
        return LocalDate.now().format(formatter);
    }

    public static String getYesterday() {
        DateTimeFormatter formatter = NftConstants.SIMPLE_DATE_FORMAT;
        return LocalDate.now().minusDays(1).format(formatter);
    }
}
