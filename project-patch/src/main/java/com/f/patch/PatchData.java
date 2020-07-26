package com.f.patch;

import lombok.Data;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-26
 */
@Data
public class PatchData {
    private String version;
    private String name;
    private Project oldProject;
    private Project newProject;
    private String exportPath;
    private PatchProperty property;
    private PatchResult result;


    @Data
    public static class Project{
        private String name;
        private String size;
        private String path;
    }
}
