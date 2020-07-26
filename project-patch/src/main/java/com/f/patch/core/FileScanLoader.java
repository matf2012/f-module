package com.f.patch.core;

import com.f.common.utils.CollectionUtils;
import com.f.common.utils.MD5Utils;
import com.f.filescan.FileScanProperty;
import com.f.filescan.ScanFile;
import com.f.filescan.constant.FileScanConstant;
import com.f.filescan.support.DefaultListener;
import com.f.filescan.support.FileScanCore;
import com.f.filescan.support.ListenerManager;
import com.f.filescan.support.PropertyBuilder;
import com.f.patch.IFileLoader;
import com.f.patch.PatchFile;
import com.f.patch.PatchProject;
import com.f.patch.constant.FileType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
public class FileScanLoader implements IFileLoader {
    public Map<String, PatchFile> loadProject(PatchProject project) {
        PropertyBuilder builder = new PropertyBuilder()
//                .parallel(FileScanConstant.SCAN_FILE_PARALLEL_TRUE)
                .outType(FileScanConstant.SCAN_FILE_OUTER_CONSOLE)
                .basePath(project.getComparePath());
        FileScanCore core = new FileScanCore(builder.build());
        final List<ScanFile> scanList = new ArrayList<ScanFile>();
        ListenerManager.register(new DefaultListener(){
            @Override
            public boolean afterScan(FileScanProperty property, List<ScanFile> list) {
                scanList.addAll(list);
                return super.afterScan(property, list);
            }
        });
//        ListenerManager.register(new OuterListener());
        core.scan();
        return convert(scanList,project);
    }

    protected Map<String, PatchFile> convert(List<ScanFile> scanList, PatchProject project){
        Map<String, PatchFile> ret = new HashMap<String, PatchFile>();
        for(ScanFile scanFile : scanList){
            if(scanFile.getFile().isDirectory()){
                if(CollectionUtils.isNotEmpty(scanFile.getChildren())){
                    ret.putAll(convert(scanFile.getChildren(),project));
                }
                continue;
            }
            String oriName = scanFile.getName();
            String name = oriName;
            String type = "";
            if(oriName.indexOf(".")>=0){
                type = oriName.substring(oriName.lastIndexOf(".")+1);
                name = oriName.substring(0,oriName.lastIndexOf("."));
            }
            PatchFile pFile = new PatchFile();
            pFile.setPath(scanFile.getPath().replace(project.getComparePath(),""));
            String key = MD5Utils.MD5(pFile.getPath());
            pFile.setName(name);
            pFile.setRealPath(scanFile.getPath());
            pFile.setType(FileType.get(type));
            pFile.setOriName(oriName);
            ret.put(key,pFile);
        }
        return ret;
    }
}
