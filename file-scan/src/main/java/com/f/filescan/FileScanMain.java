package com.f.filescan;

import com.f.filescan.constant.FileScanConstant;
import com.f.filescan.support.*;

import java.util.Scanner;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class FileScanMain {

    private static final String ARGS_NAME_1 = "扫描路径";
    private static final String ARGS_NAME_2 = "输出类型【1控制台，2文件】";
    private static final String ARGS_NAME_3 = "输出文件路径（非必选）";
    private static final String ARGS_NAME_FORMATTER = "%s:%s";
    private static final String PUT_IN_MESSAGE = "请输入";

    public static void main(String[] args) {
        PropertyBuilder builder = new PropertyBuilder();
        String outPath = "";
        if (args != null && args.length >= 2) {
            System.out.println("您输入的参数是：");
            println(ARGS_NAME_1, args[0]);
            println(ARGS_NAME_2, args[1]);
            builder.basePath(args[0]);
            builder.outType(args[1]);
            if(FileScanConstant.SCAN_FILE_OUTER_FILE.equals(args[1])){
                if(args.length >= 3){
                    println(ARGS_NAME_3, args[2]);
                    builder.outPath(args[2]);
                }else{
                    println(ARGS_NAME_3, outPath);
                    builder.outPath(outPath);
                }
            }
        } else {
            printFormatter("没有指定参数或参数位数不正确！可以输出大于等于两个参数，1：%s，2%s，3%s",ARGS_NAME_1,ARGS_NAME_2,ARGS_NAME_3);

            Scanner sc = new Scanner(System.in);
            println(PUT_IN_MESSAGE,ARGS_NAME_1);
            builder.basePath(sc.nextLine());
            println(PUT_IN_MESSAGE,ARGS_NAME_2);
            String outType = sc.nextLine();
            builder.outType(outType);
            if(FileScanConstant.SCAN_FILE_OUTER_FILE.equals(outType)){
                println(PUT_IN_MESSAGE,ARGS_NAME_3);
                builder.outPath(sc.nextLine());
            }
        }
        builder.sort(FileScanConstant.SCAN_FILE_SORT_TRUE);
        builder.parallel(FileScanConstant.SCAN_FILE_PARALLEL_TRUE);
        FileScanCore core = new FileScanCore(builder.build());

        // 输出结果监听器
        ListenerManager.register(new OuterListener());
        ListenerManager.register(new ErrorListener());
        ListenerManager.register(new SortListener(SortListener.SORT.COUNT_ASC));
        core.scan();
    }

    public static void println(String name, String val) {
        printFormatter(ARGS_NAME_FORMATTER, name, val);
    }

    public static void printFormatter(String formatter, String... args) {
        System.out.println(String.format(formatter, args));
    }
}
