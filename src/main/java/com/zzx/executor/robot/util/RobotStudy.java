package com.zzx.executor.robot.util;

import com.zzx.executor.robot.Record;
import com.zzx.executor.robot.bo.PositionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
@EnableScheduling
@Slf4j
public class RobotStudy {


    private static ArrayList<PositionEntity> list = new ArrayList<PositionEntity>();

    public void study() throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 0; i < 30; i++) {
            list.add(dorecord());
            Thread.sleep(200);
        }
        algorithm(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("结果" + list.get(i).toString());
        }
        list.clear();
    }

    /**
     * 获取鼠标移动坐标
     *
     * @return
     */
    private PositionEntity dorecord() {
        PositionEntity positionEntity = new PositionEntity();
        PointerInfo pinfo = MouseInfo.getPointerInfo();
        Point p = pinfo.getLocation();
        int mx = (int) p.getX();
        int my = (int) p.getY();
        positionEntity.setX(mx);
        positionEntity.setY(my);
        return positionEntity;
    }

    /**
     * 获取坐标存内存
     * @param list
     */
    private void algorithm(ArrayList<PositionEntity> list) {
        PositionEntity entity=new PositionEntity();
        ArrayList<Integer> inputX = new ArrayList<Integer>();
        ArrayList<Integer> inputY = new ArrayList<Integer>();
        for (int i = 0; i < 30; i++) {
            inputX.add(list.get(i).getX());
            inputY.add(list.get(i).getY());
        }
        int a = dolist(inputX);
        int b = dolist(inputY);
        entity.setX(a);
        entity.setY(b);
        Record.boot.add(entity);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx=" + a);
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyy=" + b);
    }


    /**
     * 训练网络
     *
     * @param list
     * @return
     */
    private int dolist(ArrayList<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                int j = map.get(list.get(i)) + 1;
                map.remove(list.get(i));
                map.put(list.get(i), j);
            } else {
                map.put(list.get(i), 1);
            }
        }
        int v = 0;
        int k = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) > v) {
                v = map.get(key);
                k = key;
            }
            System.out.println("key= " + key + " and value= " + map.get(key));
        }
        return k;
    }



}
