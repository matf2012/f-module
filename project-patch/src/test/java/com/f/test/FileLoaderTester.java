package com.f.test;

import com.f.patch.IFileLoader;
import com.f.patch.PatchProject;
import com.f.patch.core.FileScanLoader;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-23
 */
public class FileLoaderTester {
    public static void main(String[] args) {
        testFileScanLoader();
    }

    public static void testFileScanLoader(){
        IFileLoader loader = new FileScanLoader();
        System.out.println(loader.loadProject(getProject()));
    }

    public static PatchProject getProject(){
        PatchProject project = new PatchProject();
        project.setPath("F:\\neuWorkspaces\\dev\\aaaaaaaaaaaaaa\\sdic-dri-web-0.0.1-SNAPSHOT");
        return project;
    }
}
