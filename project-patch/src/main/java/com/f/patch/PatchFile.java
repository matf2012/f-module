package com.f.patch;

import com.f.patch.constant.FileType;
import lombok.Data;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
@Data
public class PatchFile {
    private String path;

    private String realPath;

    private FileType type;

    private String oriName;

    private String name;

    private long time;

    private String value;

    private String status;

}
