package com.f.patch.core;

import com.f.patch.IEventHandler;
import com.f.patch.PatchConfig;
import com.f.patch.PatchProject;
import com.f.patch.constant.PatchEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
public class PatchEventManager {

    private static List<IEventHandler> handlerList = new ArrayList<IEventHandler>();

    public static synchronized boolean register(IEventHandler handler) {
        boolean ret = handlerList.contains(handler);
        if (!ret) {
            handlerList.add(handler);
        }
        // 注册后进行排序
        handlerList.sort(Comparator.comparingInt(IEventHandler::order).reversed());
        return ret;
    }

    static void event(PatchEvent event, Object... args) {
        if (event != null && handlerList.size() > 0) {

            PatchProject project = null;
            PatchConfig config = null;
            PatchProject property = null;
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg instanceof PatchProject) {
                        project = (PatchProject) arg;
                    } else if (arg instanceof PatchConfig) {
                        config = (PatchConfig) arg;
                    } else if (arg instanceof PatchProject) {
                        property = (PatchProject) arg;
                    }
                }
            }
            boolean flag = true;
            for(IEventHandler handler : handlerList){

                switch (event){
                    case BEFORE_COMPARE:
                        flag = handler.beforeCompare(config);
                        break;
                    case ARTER_COMPARE:
                        flag = handler.afterCompare(config);
                        break;
                    case BEFORE_LOAD_FILE:
                        flag = handler.beforeConvertProject(project,config);
                        break;
                    case AFTER_LOAD_FILE:
                        flag = handler.afterConvertProject(project,config);
                        break;
                }
                if(!flag){
                    break;
                }
            }
        }
    }
}
