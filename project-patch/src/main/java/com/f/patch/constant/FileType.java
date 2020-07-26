package com.f.patch.constant;

public enum FileType {
    CLASS("class"), JAVA("java"), JAR("jar"), WAR("war"),
    PROPERPTY("property"), YAML("yml"),
    HTML("html"), JS("js"), CSS("css"),
    OTHER("");
    private String name;

    private FileType(String name) {
        this.name = name;
    }

    public static FileType get(String name){
        for(FileType type : values()){
            if(name.equals(type.name)){
                return type;
            }
        }
        return OTHER;
    }
}
