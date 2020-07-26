package com.f.patch;

import com.f.patch.constant.ProjectType;
import lombok.Data;

import java.util.Map;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
@Data
public class PatchProject {
    private String id;
    private String name;
    private String path;
    private ProjectType type;
    private String fileType;
    private String comparePath;

    private Map<String, PatchFile> files;
}

