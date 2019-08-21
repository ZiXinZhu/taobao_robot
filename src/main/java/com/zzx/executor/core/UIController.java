package com.zzx.executor.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



@Component
public class UIController {
    @Autowired
    private UI ui;

    public void show(String records){
        if (ui.getCore() == null){
            return;
        }
        ui.getCore().show(records);
    }


}
