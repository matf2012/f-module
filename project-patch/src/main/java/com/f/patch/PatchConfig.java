package com.f.patch;

import lombok.Data;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
@Data
public class PatchConfig {

    private String id;
    private PatchProperty property;
    private PatchProject oldProject;
    private PatchProject newProject;

    private String logPath;
    private String exportPath;
    private String tempPath;

    private PatchResult result;
}
