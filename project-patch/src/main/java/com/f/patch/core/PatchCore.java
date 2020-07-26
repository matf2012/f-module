package com.f.patch.core;

import com.f.patch.*;
import com.f.patch.adapter.JarFileAdapter;
import com.f.patch.compare.ByteFileComparisonImpl;
import com.f.patch.exception.PatchException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
public class PatchCore {

    private static final List<IFileAdapter> adapterList = new ArrayList<>();
    private static IFileLoader loader = null;
    private static IComparison comparison;

    static {
        adapterList.add(new JarFileAdapter());
        loader = new FileScanLoader();
        comparison = new ByteFileComparisonImpl();
    }

    public IFileAdapter adapter(PatchProject project){
        for(IFileAdapter adapter : adapterList){
            if(adapter.support(project)){
                return adapter;
            }
        }
        throw new PatchException(" NO FILE ADAPTER FOR : {}"+project.getPath());
    }

    public Map<String, PatchFile> loadProject(PatchProject project){
        return loader.loadProject(project);
    }

    public boolean compare(File f1 , File f2){
        return comparison.comparison(f1,f2);
    }
}
