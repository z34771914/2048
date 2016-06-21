package com.example.zhoujiazhen.mpandroidcharttest.exception;

/**
 * Created by zhoujiazhen on 16/6/20.
 *
 * 异常 图标数据空
 */

public class DataSizeZeroException extends Exception {
    public DataSizeZeroException() {
        System.out.println("data size cannot be zero");
    }

    public DataSizeZeroException(String detailMessage) {
        super(detailMessage);
    }
}
