package com.f.patch.adapter;

import com.f.common.utils.SystemUtils;
import com.f.patch.IFileAdapter;
import com.f.patch.PatchProject;
import com.f.patch.constant.CommandConstant;
import com.f.patch.constant.FileType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-24
 */
@Slf4j
public class JarFileAdapter implements IFileAdapter {

    public boolean support(PatchProject project) {
        FileType type = FileType.get(project.getFileType());
        return FileType.JAR.equals(type) || FileType.WAR.equals(type);
    }

    public boolean handle(PatchProject project) {
        return command(project);
    }

    protected boolean command(PatchProject project) {
        try {
            File from = new File(project.getPath());
            File to = new File(project.getComparePath(),from.getName());
            FileUtils.copyFile(from, to);
            String toDir = to.getParent();
            Process proc = null;
            if (SystemUtils.isWindows()) {
                proc = Runtime.getRuntime().exec(CommandConstant.COMMAND_WINDOW);
            } else {
                proc = Runtime.getRuntime().exec(CommandConstant.COMMAND_LINUX);
            }
            if (proc != null) {

                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);

                // window 进入盘符
                if (SystemUtils.isWindows()) {
                    // 进入盘符
                    String dist = toDir.substring(0, 2);
                    out.println(dist);
                }

                // 切换目录
                out.println("cd "+toDir);

                // 解压命令
//                out.println("jar -xvf " + to.getName());
                out.println("jar -xf " + to.getName());
                out.println("exit");
                String line;
                while ((line = in.readLine()) != null) {
//                    log.debug(line);
                }
                proc.waitFor();
                in.close();
                out.close();
                proc.destroy();
            }else{
                return false;
            }
            to.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
