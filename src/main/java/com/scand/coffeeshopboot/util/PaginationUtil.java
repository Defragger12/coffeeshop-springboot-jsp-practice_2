package com.scand.coffeeshopboot.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {

    public static String DEFAULT_PAGE_NUMBER = "1";
    public static String DEFAULT_COFFEE_SORT = "name,asc";
    public static String DEFAULT_ORDER_SORT = "createdDate,asc";
    public static String DEFAULT_PAGE_SIZE = "5";

    private static Map<String, Sort.Direction> sortStringMap = new HashMap<>();

    static {
        sortStringMap.put("asc", Sort.Direction.ASC);
        sortStringMap.put("desc", Sort.Direction.DESC);
    }

    public static PageRequest convertToPageRequest(String sort, String pageNumber, String pageSize) {

        String[] sortParams = sort.split(",");

        String sortDirection = sortParams[1];
        String sortField = sortParams[0];

        return PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize), sortStringMap.get(sortDirection), sortField);
    }
}
