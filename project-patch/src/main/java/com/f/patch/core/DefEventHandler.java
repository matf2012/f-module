package com.f.patch.core;

import com.f.patch.IEventHandler;
import com.f.patch.PatchConfig;
import com.f.patch.PatchProject;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
public class DefEventHandler implements IEventHandler {
    @Override
    public boolean beforeConvertProject(PatchProject project, PatchConfig config) {
        return true;
    }

    @Override
    public boolean afterConvertProject(PatchProject project, PatchConfig config) {
        return true;
    }

    @Override
    public boolean beforeCompare(PatchConfig config) {
        return true;
    }

    @Override
    public boolean afterCompare(PatchConfig config) {
        return true;
    }

    @Override
    public int order() {
        return 50;
    }
}
