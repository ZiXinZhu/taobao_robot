package com.zzx.executor.core;


import com.zzx.executor.core.interfaces.IButton;
import com.zzx.executor.robot.DoRecord;
import com.zzx.executor.robot.Record;
import com.zzx.executor.robot.dao.TaobaoConcumerDao;
import com.zzx.executor.robot.entity.TaoBaoConsumerPO;
import com.zzx.executor.robot.util.RobotStudy;
import com.zzx.executor.util.EnCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.beans.Encoder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class IEOperator implements Runnable {
    private boolean notInterrupted = true;
    private WebDriver driver;
    private static String path = "C:\\Program Files";
    static int I = 1;

    @Autowired
    private IButton button;
    @Autowired
    private UI ui;
    @Autowired
    RobotStudy robotStudy;
    @Autowired
    Record record;
    @Autowired
    DoRecord doRecord;
    @Autowired
    TaobaoConcumerDao concumerDao;


    List<TaoBaoConsumerPO> consumers = new ArrayList<>();


    public void openAndLogin() {

        if (StringUtils.isEmpty(path)) {
            System.out.println("没有找到Quark");
            return;
        }
//      System.setProperty("webdriver.ie.driver", path + File.separator + "Quark.exe");
        System.setProperty("webdriver.chrome.driver", path + File.separator + "QuarkGG.exe");

//      driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//      driver.get(button.getLoginPageURL());
    }

    @Override
    public void run() {
        try {
            this.start();
        } catch (InterruptedException | AWTException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.notInterrupted = false;
    }

    public void start() throws InterruptedException, AWTException {
        notInterrupted = true;
        Thread.sleep(500);
        record.record(12);
        Thread.sleep(5000);

        consumers = concumerDao.findAll();
        while (notInterrupted) {
            if (!CollectionUtils.isEmpty(consumers)) {
                for(int i=0;i<consumers.size();i++) {
                    ui.showName(String.valueOf(I));
//                  System.out.println(EnCode.decrypt(consumers.get(i).getPassword()));
//                  doRecord.dorobot(Record.boot.get(0),"qqwwee123");
                    doRecord.dorobot(Record.boot.get(0), "login.taobao.com");
                    doRecord.dorobot();
                    Thread.sleep(5000);

                    doRecord.dorobot(Record.boot.get(1), consumers.get(i).getAccount());
                    Thread.sleep(3000);

                    doRecord.dorobot(Record.boot.get(2), EnCode.decrypt(consumers.get(i).getPassword()));
                    Thread.sleep(4000);

                    //TODO 风控
                    doRecord.mousedown(Record.boot.get(3));
                    doRecord.mouseup();
                    doRecord.dorobot(Record.boot.get(3));
                    Thread.sleep(4000);

                    //TODO 登录
                    doRecord.dorobot(Record.boot.get(4));
                    Thread.sleep(5000);

                    //TODO 待收货
                    doRecord.dorobot(Record.boot.get(5));
                    Thread.sleep(5000);

                    //TODO 勾选
                    doRecord.dorobot(Record.boot.get(6));
                    Thread.sleep(4000);

                    //TODO 批量
                    doRecord.dorobot(Record.boot.get(7));
                    Thread.sleep(7000);

                    //TODO 支付密码
                    doRecord.dorobot(Record.boot.get(8), EnCode.decrypt(consumers.get(i).getPayPassword()));
                    Thread.sleep(4000);

                    //TODO 确定
                    doRecord.dorobot(Record.boot.get(9));
                    Thread.sleep(4000);


                    //TODO 再次确定
                    doRecord.dorobot(Record.boot.get(10));
                    Thread.sleep(4000);


                    //TODO 关闭窗口
                    doRecord.dorobot(Record.boot.get(11));
                    Thread.sleep(1000);
                    doRecord.dorobot(Record.boot.get(11));

                    I++;
                    Thread.sleep(10000);
                }
                JOptionPane.showMessageDialog(null, "收货完成，程序停止！");
            }

        }
    }


}
