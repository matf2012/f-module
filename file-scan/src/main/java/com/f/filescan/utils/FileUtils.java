package com.f.filescan.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class FileUtils {

    public static void write(File outFile, List<String> lines) {
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(outFile);
            bw = new BufferedWriter(fw);
            for (String line : lines) {
                bw.write(line + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
