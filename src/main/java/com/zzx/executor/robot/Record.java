package com.zzx.executor.robot;


import com.zzx.executor.robot.bo.PositionEntity;
import com.zzx.executor.robot.util.RobotStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Record {
    CopyOnWriteArrayList<String> name=new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<PositionEntity> boot = new CopyOnWriteArrayList<>();
    @Autowired
    RobotStudy study;

    public void record(int times) throws AWTException, InterruptedException {
        name.add("请将鼠标停留在【搜索栏】10秒，请不要移动");
//      name.add("请将鼠标停留在【扫码转账号密码框】10秒，请不要移动");
        name.add("请将鼠标停留在【输入账号框】10秒，请不要移动");
        name.add("请将鼠标停留在【输入密码框】10秒，请不要移动");
        name.add("请将鼠标停留在【滑动左端】10秒，请不要移动");
        name.add("请将鼠标停留在【点击登录按钮】10秒，请不要移动");
        name.add("请将鼠标停留在【待收货按钮】10秒，请不要移动");
        name.add("请将鼠标停留在【勾选按钮】10秒，请不要移动");
        name.add("请将鼠标停留在【点击批量货按钮】10秒，请不要移动");
        name.add("请将鼠标停留在【输入支付密码】10秒，请不要移动");
        name.add("请将鼠标停留在【确定】10秒，请不要移动");
        name.add("请将鼠标停留在【弹窗确定】10秒，请不要移动");
        name.add("请将鼠标停留在【关闭窗口】10秒，请不要移动");
//        name.add("请将鼠标停留在【完成】10秒，请不要移动");


        for(int i=0;i<times;i++){
            JOptionPane.showMessageDialog(null, name.get(i));
            study.study();
        }
        JOptionPane.showMessageDialog(null, "录入完成，点击继续！【5秒钟后程序自动启动！】");
    }
}
