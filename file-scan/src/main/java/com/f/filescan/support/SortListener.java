package com.f.filescan.support;

import com.f.filescan.FileScanProperty;
import com.f.filescan.ScanFile;
import com.f.filescan.utils.FileScannerUtils;

import java.util.Comparator;
import java.util.List;

/**
 * 排序监听器
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class SortListener extends DefaultListener {


    private Comparator comparator;

    public SortListener(){
        super();
        init(SORT.SIZE_DESC);
    }

    public SortListener(SORT sort){
        super();
        init(sort);
    }

    protected void init(SORT sort){
        switch (sort){
            case SIZE_ASC:
                this.comparator = Comparator.comparingLong(ScanFile::size);
                break;
            case COUNT_ASC:
                this.comparator = Comparator.comparingLong(ScanFile::count);
                break;
            case COUNT_DESC:
                this.comparator = Comparator.comparingLong(ScanFile::count).reversed();
                break;
            default:
                this.comparator = Comparator.comparingLong(ScanFile::size).reversed();
        }
    }

    @Override
    public boolean afterScan(FileScanProperty property, List<ScanFile> list) {
        sort(list);
        return super.afterScan(property, list);
    }

    /**
     * 排序：根据大小倒序
     * @param list
     */
    protected void sort(List<ScanFile> list){
        if(FileScannerUtils.isNotEmpity(list)){
            // 根据大小 倒叙
            list.sort(this.comparator);
            list.forEach(file->{
                // 文件夹下文件排序
                if(FileScannerUtils.isNotEmpity(file.getChildren())){
                    this.sort(file.getChildren());
                }
            });
        }
    }

    public static enum SORT{
        SIZE_ASC,SIZE_DESC,COUNT_ASC,COUNT_DESC
    }
}
