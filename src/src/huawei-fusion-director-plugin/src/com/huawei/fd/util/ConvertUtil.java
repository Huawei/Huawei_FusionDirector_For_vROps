package com.huawei.fd.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.huawei.fd.api.exception.FusionDirectorException;

public class ConvertUtil {
    
    public static Integer parseInt(String source) {
        try{
            return Integer.parseInt(source);
        }catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }
    
    public static boolean isNumeric(String text) {
        
        if (text == null || text.length() == 0) {
            return false;
        }
        
        return text.matches("^[0-9]+$");
    }
    
    public static String convertHealth(String health) {
        
        if (health == null) {
            return "Unknown";
        }
        
        if (health.matches("^(OK|Warning|Unknown|Immediate|Critical)")) {
            return health;
        } else {
            return "Unknown";
        }
    }
    
    public static <T> T deepClone(T src) throws FusionDirectorException {
        
        if (src == null) {
            return null;
        }
        
        T o = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(src);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = (T) ois.readObject();
            ois.close();
            bais.close();
            baos.close();
        } catch (IOException e) {
            throw new FusionDirectorException("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new FusionDirectorException("ClassNotFoundException: " + e.getMessage());
        }
        return o;
    }
    
}

    

