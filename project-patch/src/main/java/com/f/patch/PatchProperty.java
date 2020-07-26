package com.f.patch;

import com.f.patch.constant.PatchType;
import lombok.Data;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
@Data
public class PatchProperty {
    private PatchType type;
    private String version;
    private String name;
    private String basePath;

    private String oldPath;
    private String newPath;
}
