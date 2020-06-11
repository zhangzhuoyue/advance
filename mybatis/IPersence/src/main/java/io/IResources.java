package io;

import java.io.InputStream;

public class IResources {


    public static InputStream getResourceAsStream(String path){
        if (path == null || path.trim().equals("")) return  null;
        InputStream inputStream = IResources.class.getResourceAsStream(path);
        return inputStream;
    }
}
