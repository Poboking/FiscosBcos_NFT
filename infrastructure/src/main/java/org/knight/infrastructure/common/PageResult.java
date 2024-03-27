package org.knight.infrastructure.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 11:47
 */
@Data
@Builder
@AllArgsConstructor
@SuppressWarnings("all")
public class PageResult<T> implements Serializable {
    /**
     * 当前页号
     */
    private long current;

    /**
     * 页面大小
     */
    private long pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 实际返回记录数
     */
    private long size;

    /**
     * 数据
     */
    private T data;

    public static <T> PageResult<T> convertFor(IPage<T> pageResult) {
        return PageResult.<T>builder()
                .current(pageResult.getCurrent())
                .total(pageResult.getTotal())
                .pages(pageResult.getPages())
                .size(pageResult.getSize())
                .data((T) pageResult.getRecords())
                .pageSize(pageResult.getSize())
                .build();
    }


    public static <T> PageResult<T> convertFor(IPage<T> pageResult, long pageSize) {
        return PageResult.<T>builder()
                .current(pageResult.getCurrent())
                .total(pageResult.getTotal())
                .pages(pageResult.getPages())
                .size(pageResult.getSize())
                .data((T) pageResult.getRecords())
                .pageSize(pageSize)
                .build();
    }

    public static <T> PageResult<T> convertFor(IPage<?> pageResult, List<T> resultList) {
        return PageResult.<T>builder()
                .current(pageResult.getCurrent())
                .total(pageResult.getTotal())
                .pages(pageResult.getPages())
                .size(pageResult.getSize())
                .data((T) resultList)
                .pageSize(pageResult.getSize())
                .build();
    }

    public static <T> PageResult<T> convertFor(IPage<?> pageResult, long pageSize, List<T> resultList) {
        return PageResult.<T>builder()
                .current(pageResult.getCurrent())
                .total(pageResult.getTotal())
                .pages(pageResult.getPages())
                .size(pageResult.getSize())
                .data((T) resultList)
                .pageSize(pageSize)
                .build();
    }

}
