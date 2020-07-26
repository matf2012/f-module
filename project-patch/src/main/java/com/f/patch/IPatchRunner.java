package com.f.patch;

public interface IPatchRunner {

    boolean initPath(PatchProperty property, PatchConfig config);

    boolean convertOldProject(PatchProject project, PatchConfig config);

    boolean convertNewProject(PatchProject project, PatchConfig config);

    boolean compare(PatchConfig config);

    boolean export(PatchConfig config);

}
