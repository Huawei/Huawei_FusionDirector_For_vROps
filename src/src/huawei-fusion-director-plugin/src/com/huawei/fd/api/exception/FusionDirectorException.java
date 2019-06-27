package com.huawei.fd.api.exception;

public class FusionDirectorException extends Exception {

    
    public FusionDirectorException(String message) {
        super(message);
    }
    
    public FusionDirectorException(Exception e){
        super(e);
    }
    
}
