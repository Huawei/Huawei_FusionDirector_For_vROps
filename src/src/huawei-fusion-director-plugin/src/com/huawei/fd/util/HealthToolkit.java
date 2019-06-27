package com.huawei.fd.util;

public class HealthToolkit {
    
    private String defaultStatus = "OK";

    public String getHealth() {
    	
    	if ("Unknown".equals(defaultStatus)) {
    		defaultStatus = "Warning";
    	}
    	
        return defaultStatus;
    }

    
    public void pushHealth(String status) {
        
        switch (defaultStatus){
        case "OK":{
            defaultStatus = status;
        }
        break;
        case "Unknown":{
            if(status.equals("Warning")||status.equals("Immediate")||status.equals("Critical")){
                defaultStatus = status;
            }
        }
        break;
        case "Warning":{
            if(status.equals("Immediate")||status.equals("Critical")){
                defaultStatus = status;
            }
        }
        break;
        case "Immediate":{
            if(status.equals("Critical")){
                defaultStatus = status;
            }
        }
        break;
        case "Critical":{
            break;
        }
        default : {
            break;
        }
        }
        
    }
}
