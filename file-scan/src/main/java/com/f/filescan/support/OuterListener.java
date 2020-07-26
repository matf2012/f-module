package com.f.filescan.support;

import com.f.filescan.FileScanProperty;
import com.f.filescan.exception.FileScanException;
import com.f.filescan.IFileScanOuter;
import com.f.filescan.ScanFile;
import com.f.filescan.constant.FileScanConstant;

import java.util.List;

/**
 * 输出监听器，
 * 当扫描完成时，执行输出，输出优先级较低
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class OuterListener extends DefaultListener {

    private IFileScanOuter outer;

    @Override
    public boolean afterScan(FileScanProperty property, List<ScanFile> list) {
        initOuter(property);
        outer.output(list);
        return super.afterScan(property, list);
    }

    /**
     * 初始化输出：文件或控制台
     * @param property
     */
    public void initOuter(FileScanProperty property) {
        if (FileScanConstant.SCAN_FILE_OUTER_CONSOLE.equals(property.getOutType())) {
            this.outer = new ConsoleOuter();
        } else if (FileScanConstant.SCAN_FILE_OUTER_FILE.equals(property.getOutType())) {
            this.outer = new FileOuter(property.getOutPath());
        } else {
            throw new FileScanException(" out type not found ! " + property.getOutType());
        }
    }

    @Override
    public int order() {
        return 10;
    }
}
