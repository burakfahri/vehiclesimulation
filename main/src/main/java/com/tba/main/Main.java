package com.tba.main;

import com.tba.manager.InputManager;
import com.tba.manager.UIManager;
import com.tba.util.LogUtil;
import org.apache.log4j.Logger;

import java.awt.EventQueue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;

public class Main extends JFrame {
    private static Logger logger = Logger.getLogger(Main.class);

    public Main() {

        initUI();
    }

    private void initUI() {

        add(new UIManager());

        setResizable(true);
        pack();

        setTitle("Vehicle Simulation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void printWelcome()
    {
        LogUtil.setInfoLog(logger,"********************************************************************************************");
        LogUtil.setInfoLog(logger,"***  *********   *******  ***      ***  ******      ***        ***    ***    ***      ******");
        LogUtil.setInfoLog(logger,"****  *******  *  *****  ****  *******  ******  *******  ****  ***  *  *  *  ***  **********");
        LogUtil.setInfoLog(logger,"*****  *****  ***  ***  *****     ****  ******  *******  ****  ***  **   **  ***     *******");
        LogUtil.setInfoLog(logger,"******  ***  *****  *  ******  *******  ******  *******  ****  ***  *******  ***  **********");
        LogUtil.setInfoLog(logger,"*******  * ********   *******      ***      **      ***        ***  *******  ***      ******");
        LogUtil.setInfoLog(logger,"********************************************************************************************");
    }


    public static void main(String[] args) {
        printWelcome();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Main();
                ex.setVisible(true);
            }
        });
        Scanner in = new Scanner(System.in);
        System.out.println(InputManager.getHelpMessage());

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    String s = InputManager.handleInput(in.nextLine().toLowerCase().trim());
                    if(s != null)
                        LogUtil.setInfoLog(logger,s);

                }
            }
        });
    }




}