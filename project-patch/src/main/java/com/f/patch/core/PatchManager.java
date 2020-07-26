package com.f.patch.core;

import com.f.common.utils.RandomUtils;
import com.f.patch.IPatchRunner;
import com.f.patch.PatchConfig;
import com.f.patch.PatchProject;
import com.f.patch.PatchProperty;
import com.f.patch.constant.PatchEvent;
import com.f.patch.constant.ProjectType;
import com.f.patch.exception.PatchException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
@Slf4j
public class PatchManager {

    private PatchConfig config;


    private IPatchRunner runner;

    public PatchManager(PatchProperty property){
        init(property);
    }


    public void init(PatchProperty property){
        runner = getRunner();
        initConfig(property);
    }

    public void initConfig(PatchProperty property){
        config = new PatchConfig();
        config.setId(RandomUtils.UUID());
        config.setProperty(property);
        config.setNewProject(genProject(property.getNewPath(),ProjectType.NEW));
        config.setOldProject(genProject(property.getOldPath(),ProjectType.OLD));
    }

    protected PatchProject genProject(String path, ProjectType type){
        PatchProject project = new PatchProject();
        project.setId(RandomUtils.UUID());
        File file  = new File(path);
        if(!file.exists()){
            throw new PatchException("PROJECT NOT FOUNT ! PATH : "+path);
        }
        String name = file.getName();
        project.setPath(path);
        project.setName(name);
        String fileType = "";
        if(name.indexOf(".")>=0){
            fileType = name.substring(name.lastIndexOf(".")+1);
        }
        project.setFileType(fileType);
        project.setType(type);
        return project;
    }

    protected IPatchRunner getRunner(){
        return new DefPatchRunner();
    }

    public void run(){
        log.debug("run patch start !");

        runner.initPath(config.getProperty(),config);


        PatchEventManager.event(PatchEvent.BEFORE_LOAD_FILE,config.getOldProject(),config);
        runner.convertOldProject(config.getOldProject(),config);
        PatchEventManager.event(PatchEvent.AFTER_LOAD_FILE,config.getOldProject(),config);

        PatchEventManager.event(PatchEvent.BEFORE_LOAD_FILE,config.getNewProject(),config);
        runner.convertNewProject(config.getNewProject(),config);
        PatchEventManager.event(PatchEvent.AFTER_LOAD_FILE,config.getNewProject(),config);


        PatchEventManager.event(PatchEvent.BEFORE_COMPARE,config);
        runner.compare(config);
        PatchEventManager.event(PatchEvent.ARTER_COMPARE,config);


        PatchEventManager.event(PatchEvent.BEFORE_EXPORT,config);
        runner.export(config);
        PatchEventManager.event(PatchEvent.AFTER_EXPORT,config);

        log.debug("run patch end !");
    }

}
