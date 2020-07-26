package com.f.test;

import com.f.patch.PatchProperty;
import com.f.patch.constant.PatchType;
import com.f.patch.core.PatchManager;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
public class ManagerTester {


    public static void main(String[] args) {
        PatchProperty property = new PatchProperty();
        property.setBasePath("F:\\neuWorkspaces\\ruian\\patch_dir");
        property.setName("SDIC");
        property.setType(PatchType.DIR);
        property.setNewPath("F:\\neuWorkspaces\\ruian\\release\\sdic-dri-master-master\\sdic-dri-web\\target\\sdic-dri-web-0.0.1-SNAPSHOT.jar");
        property.setOldPath("F:\\neuWorkspaces\\ruian\\patch_dir\\sdic-dri-web-0.0.1-SNAPSHOT_ori.jar");

        PatchManager manager = new PatchManager(property);
        manager.run();
    }
}
