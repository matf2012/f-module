package com.f.patch;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
@Data
public class PatchResult {

    List<PatchFile> add = new ArrayList<>();
    List<PatchFile> update = new ArrayList<>();
    List<PatchFile> remove = new ArrayList<>();
}
