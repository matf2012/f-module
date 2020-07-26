package com.f.patch;

import java.util.Map;

public interface IFileLoader {
    Map<String,PatchFile> loadProject(PatchProject project);
}
