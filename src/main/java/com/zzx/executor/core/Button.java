package com.zzx.executor.core;


import com.zzx.executor.core.interfaces.IButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author Alon
 * @Date 2019/3/24 19:50
 */
@Component
public class Button implements IButton {

    @Autowired
    RestTemplate template;

//    private String loginPage = "https://mobile.yangkeduo.com/login.html";

    //点击查询
    private String click = "";
    //获取Iframe内容
    private String getIFrameData = "";

//    @Override
//    public String getLoginPageURL() {
//        return "";
//    }

    @Override
    public String refreshButton() {
        return click;
    }

    @Override
    public String getDataButton() {
        return getIFrameData;
    }
}
