package application.core.utils;

public class Dir {

    public static final String ROOT        = System.getProperty("user.dir").replace("\\", "/");
    public static final String APPLICATION = ROOT + "/src/application";
    public static final String LIBS        = ROOT + "/libs";
    public static final String OUT         = ROOT + "/out";
}
