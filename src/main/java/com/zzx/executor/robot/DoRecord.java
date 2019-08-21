package com.zzx.executor.robot;

import com.zzx.executor.robot.bo.PositionEntity;
import com.zzx.executor.robot.util.RobotApplication;
import com.zzx.executor.robot.util.RobotUtil;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@Service
public class DoRecord {

    PositionEntity entity=new PositionEntity();

    /**
     * 输入密码
     *
     * @return
     * @throws AWTException
     * @throws InterruptedException
     */
    public void dorobot(PositionEntity entity, String str) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Thread.sleep(300);
        if (entity==null) {
            System.out.println("没有收到xy");
            return ;
        }
//        TODO 移动鼠标
        robot.mouseMove(entity.getX(), entity.getY());
//        TODO 点击鼠标
        RobotUtil.mouseClick(robot);
//        TODO 删除记录
        RobotUtil.keyClick(robot, KeyEvent.VK_HOME);
        for (int i = 0; i < 200; i++) {
            RobotUtil.keyClick(robot, KeyEvent.VK_DELETE);
        }
//        TODO 插入记录
        for (int i=0;i<str.length();i++){
            if(!String.valueOf(str.charAt(i)).equals(".")){
                RobotApplication.input(String.valueOf(str.charAt(i)));
            }else {
                RobotUtil.keyClick(robot, KeyEvent.VK_PERIOD );
            }
        }

    }


    public void dorobot(PositionEntity entity) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Thread.sleep(300);
        if (entity == null) {
            System.out.println("没有收到xy");
            return;
        }
//        TODO 移动鼠标
        robot.mouseMove(entity.getX(), entity.getY());
//        TODO 点击鼠标
        RobotUtil.mouseClick(robot);
    }

    public void dorobot() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Thread.sleep(300);
//        TODO 点击鼠标
        RobotUtil.mouseClick(robot);
        RobotUtil.keyClick(robot, KeyEvent.VK_ENTER );
    }



    public void mousedown(PositionEntity entity) throws AWTException, InterruptedException {
        this.entity=entity;
        Robot robot = new Robot();
        Thread.sleep(300);
        if (entity == null) {
            System.out.println("没有收到xy");
            return;
        }
//        TODO 移动鼠标
        robot.mouseMove(entity.getX(), entity.getY());
//        TODO 点击鼠标
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public void mouseup() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Thread.sleep(300);
        if (entity == null) {
            System.out.println("没有收到xy");
            return;
        }
//        TODO 移动鼠标
        robot.mouseMove(entity.getX()+300, entity.getY());
//        TODO 点击鼠标
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}
