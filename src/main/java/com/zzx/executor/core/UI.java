package com.zzx.executor.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class UI implements Runnable {
    private UICore jDialog;
    private JTable table;
    private JTextField urlField;
    private JComboBox<String> intervalSelect;
//    private JButton login;
    private JButton start;
    private JButton stop;
    private JTextField addtionField;
    private DefaultTableModel model;
    public static int I=1;





    @Autowired
    @Qualifier(value = "loginListener")
    private ActionListener loginListener;

    @Autowired
    @Qualifier(value = "startScanListener")
    private ActionListener startScanListener;

    @Autowired
    @Qualifier(value = "stopScanListener")
    private ActionListener stopScanListener;

    @Autowired
    private IEOperator ieOperator;


    //TODO 附加参数
    public void showName(String name){
        this.addtionField.setText(" "+name);
        //this.addtionField.setEnabled(false);
    }
    @Override
    public void run(){
        try {
            jDialog = new UICore();
            jDialog.initUI();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public UICore getCore(){
        return jDialog;
    }

    public class UICore{

        public void show(String records) {
            if (records == null) {
                return;
            }
            model.getDataVector().removeAllElements();
            //升序排列后，越靠近现在的记录越晚插入监控屏，效率更高
            insertToRightPlace(records);

        }

        /**
         * 显示到正确位置
         * 依次比较得出正确位置
         * @param record
         */
        private void insertToRightPlace(String record){
            int i = 0;
            String data[] = {record};
            model.insertRow(i, data);
        }


        public void initUI() {
            JFrame jFram = new JFrame("淘宝收货辅助工具");
            jFram.setSize(900, 190);
            jFram.setLocationRelativeTo(null);
            jFram.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel upPanel = new JPanel(new BorderLayout());
            upPanel.setBorder(BorderFactory.createTitledBorder("数据监控"));
            //upPanel.setSize(900,200);
            JPanel downPanel = new JPanel();
            downPanel.setBorder(BorderFactory.createTitledBorder("参数设置"));
            downPanel.setSize(900,10);
            //表格
            {
                Object[] columnNames = {"数据监控"};
                model = new DefaultTableModel(columnNames, 0);
                table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.getViewport().setBackground(Color.WHITE);
                upPanel.add(scrollPane, BorderLayout.CENTER);
            }

            JLabel url;
            {
                url = new JLabel("服务器地址:");
                //TODO 展示上报地址
                urlField = new JTextField("",40);
                urlField.setText(""); //在地址栏显示上报地址
                downPanel.add(url);
                downPanel.add(urlField);
            }

            JLabel interval;
            {
                String[] listData = new String[]{"5", "10", "20", "30"};
                interval = new JLabel("扫描周期(秒):");
                intervalSelect = new JComboBox<>(listData);
                intervalSelect.setSelectedIndex(2);
                downPanel.add(interval);
                downPanel.add(intervalSelect);
            }


            JLabel addtion;
            {
                //TODO 展示附加参数
                addtion = new JLabel("数据更新次数:");
                addtionField = new JTextField("",10);

                downPanel.add(addtion);
                downPanel.add(addtionField);
            }

            {
//                login = new JButton("请点击→");
//                login.addActionListener(loginListener);
                start = new JButton("开始录屏");
                start.addActionListener(startScanListener);
                stop = new JButton("停止扫描");
                stop.addActionListener(stopScanListener);

//                downPanel.add(login);
                downPanel.add(start);
                downPanel.add(stop);
            }


            Box box = Box.createVerticalBox();
            Box row1 = Box.createHorizontalBox();
            row1.add(url);
            row1.add(urlField);
            row1.add(interval);
            row1.add(intervalSelect);
            //附加参数，暂取消显示
            row1.add(addtion);
            row1.add(addtionField);
            Box row2 = Box.createHorizontalBox();
//            row2.add(login);
            row2.add(start);
            row2.add(stop);

            Box downBox = Box.createVerticalBox();
            downBox.add(row1);
            downBox.add(row2);
            downPanel.add(downBox);

            box.add(upPanel);
            box.add(downPanel);
            jFram.setContentPane(box);
            jFram.setResizable(false);
            jFram.setVisible(true);
            //关闭窗口时，调stop()关闭Quark.exe，否则进程中多个Quark.exe
            jFram.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ieOperator.stop();
                }
            });
        }
    }


}
