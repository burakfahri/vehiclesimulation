package com.tba.manager;

import com.tba.exception.EmptyInstanceException;
import com.tba.inteface.Vehicle;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.util.LogUtil;
import org.apache.log4j.Logger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.*;

/**
 * panel which will show the vehicles
 */
public class UIManager extends JPanel
        implements ActionListener {

    public static Logger logger = Logger.getLogger(VehicleManagerImpl.class);

    private final int UI_WIDTH = 350;
    private final int UI_HEIGHT = 350;
    private final int DELAY = 0;

    private Image carImage;
    private Timer timer;

    public UIManager() {
        initBackground();
    }

    private void loadImage() {
        try{

            LogUtil.setInfoLog(logger,"boo ");
            LogUtil.setInfoLog(logger,"name is "+ getClass().getName());
//            LogUtil.setInfoLog(logger,getClass().);
            ImageIcon ımageIcon = new ImageIcon(this.getClass().getClassLoader()
                    .getResource("com.tba.manager/image.png"));
            carImage = ımageIcon.getImage();
        }
        catch (Exception e)
        {
            LogUtil.setErrorLog(logger,"Image icon can not found");
        }
    }

    private void initBackground() {

        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(UI_WIDTH, UI_HEIGHT));
        loadImage();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance() ;

        Map<Id,Vehicle> vehicleMap = null;
        try {
            vehicleMap = vehicleManager.getVehicles();
        } catch (EmptyInstanceException e1) {
            LogUtil.setErrorLog(logger,"vehicle manager is not initialize yet");
            LogUtil.setErrorLog(logger,e1.toString());
        }
        if( vehicleMap == null || vehicleMap.isEmpty()) {
            return;
        }
        for ( Vehicle vehicle1 : vehicleMap.values())
        {
            try {
                g.drawImage(carImage, vehicle1.coordinate().getX(), vehicle1.coordinate().getY(), this);
            }
            catch (Exception e)
            {}
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}