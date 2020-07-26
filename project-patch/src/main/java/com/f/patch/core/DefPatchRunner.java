package com.f.patch.core;

import com.f.patch.*;
import com.f.patch.constant.PatchConstant;
import com.f.patch.constant.PatchType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
@Slf4j
public class DefPatchRunner extends PatchCore implements IPatchRunner {


    public boolean initPath(PatchProperty property, PatchConfig config) {
        config.setLogPath(Paths.get(property.getBasePath(), PatchConstant.LOG_PATH).toString());
        config.setTempPath(Paths.get(property.getBasePath(), PatchConstant.TEMP_PATH).toString());
        config.setExportPath(Paths.get(property.getBasePath(), PatchConstant.EXPORT_PATH).toString());
        return true;
    }


    public boolean convertOldProject(PatchProject project, PatchConfig config) {
        return convertProject(project, config);
    }

    public boolean convertNewProject(PatchProject project, PatchConfig config) {
        return convertProject(project, config);
    }

    public boolean compare(PatchConfig config) {

        PatchResult result = new PatchResult();
        config.setResult(result);
        Map<String, PatchFile> oMap = config.getOldProject().getFiles();
        Map<String, PatchFile> nMap = config.getNewProject().getFiles();
        System.out.println("old : " +oMap.size());
        System.out.println("new : " +nMap.size());
        Iterator<String> oldIt = oMap.keySet().iterator();
        while (oldIt.hasNext()) {
            String key = oldIt.next();
            PatchFile oFile = oMap.get(key);
            PatchFile nFile = nMap.get(key);
            if (nFile == null) {
                // 删除的
                result.getRemove().add(oFile);
            } else {
                if (!compapre(oFile, nFile)) {
                    // 修改
                    result.getUpdate().add(nFile);
                }
                nMap.remove(key);
            }
        }
        Iterator<String> newIt = nMap.keySet().iterator();
        while (newIt.hasNext()) {
            // 新增
            String key = newIt.next();
            result.getAdd().add(nMap.get(key));
        }
        return true;
    }

    @Override
    public boolean export(PatchConfig config) {
        PatchResult result = config.getResult();
        if (result != null) {
            PatchType type = config.getProperty().getType();
            File exportDir = new File(Paths.get(config.getExportPath(),config.getId()).toUri());
            if(!exportDir.exists()){
                exportDir.mkdirs();
            }
            // add
            System.out.println("add : " +result.getAdd().size());
            makePatchFiles(result.getAdd(),exportDir);
            System.out.println("update" +result.getUpdate().size());
            makePatchFiles(result.getUpdate(),exportDir);
            System.out.println("remove" +result.getRemove().size());
        }
        return true;
    }

    protected void makePatchFiles(List<PatchFile> list,File exportDir){
        for(PatchFile file : list){
            File f = new File(file.getRealPath());
            try {
                FileUtils.copyFile(f,new File(Paths.get(exportDir.getPath(),file.getPath()).toUri()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean compapre(PatchFile f1, PatchFile f2) {
        return compare(getFile(f1), getFile(f2));
    }

    protected File getFile(PatchFile f) {
        return new File(f.getRealPath());
    }


    protected void initComparePath(PatchProject project, PatchConfig config) {
        project.setComparePath(Paths.get(config.getTempPath(),config.getId(), project.getId()).toString());

    }

    protected boolean convertProject(PatchProject project, PatchConfig config) {
        initComparePath(project, config);
        boolean ret = adapter(project).handle(project);
        if (ret) {
            project.setFiles(loadProject(project));
        }
        return ret;
    }

}
