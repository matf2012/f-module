package com.f.patch;

public interface IEventHandler {


    boolean beforeConvertProject(PatchProject project, PatchConfig config);

    boolean afterConvertProject(PatchProject project, PatchConfig config);

    boolean beforeCompare(PatchConfig config);

    boolean afterCompare(PatchConfig config);



    int order();
}
