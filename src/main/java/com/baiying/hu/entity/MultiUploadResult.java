package com.baiying.hu.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by jmx
 * 2017/10/19.
 */
@Data
public class MultiUploadResult {
    private int errno;
    private List<String> data;
}
