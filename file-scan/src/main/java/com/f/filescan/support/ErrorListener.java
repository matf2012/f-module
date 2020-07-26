package com.f.filescan.support;

import com.f.filescan.FileScanProperty;

/**
 * 异常监听
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class ErrorListener extends DefaultListener {
    @Override
    public boolean error(FileScanProperty property, Exception exception) {
        exception.printStackTrace();
        return super.error(property, exception);
    }
}
