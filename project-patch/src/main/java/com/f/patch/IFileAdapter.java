package com.f.patch;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-24
 */
public interface IFileAdapter {
    boolean support(PatchProject project);
    boolean handle(PatchProject project);
}
