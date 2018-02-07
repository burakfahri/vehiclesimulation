package com.tba.manager;

import com.tba.exception.EmptyInstanceException;
import com.tba.inteface.Vehicle;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.model.VehicleImpl;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class VehicleManagerTest {
    @Test
    public void testEqualVehicle()
    {
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            Vehicle vehicle = new VehicleImpl(new Id(1),new Vector(new Point(0,0),new Point(1,0)),new Point(3,3));
            vehicleManager.addVehicle(vehicle);
            Vehicle vehicle1 = vehicleManager.getVehicle(new Id(1));
            assertEquals(vehicle,vehicle1);
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRemoveVehicle()
    {
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            Vehicle vehicle = new VehicleImpl(new Id(1),new Vector(new Point(0,0),new Point(1,0)),new Point(3,3));
            vehicleManager.addVehicle(vehicle);
            vehicleManager.removeVehicle(new Id(1));
            assertEquals(vehicleManager.getVehicles().values().size(),0);
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeDirectionOfVehicle()
    {
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        Vector v = new Vector(new Point(0,0),new Point(1,0));
        Vector v1 = new Vector(new Point(0,0),new Point(2,0));
        Point p = new Point(3,3);
        try {
            Vehicle vehicle = new VehicleImpl(new Id(1),v,new Point(3,3));
            vehicleManager.addVehicle(vehicle);
            vehicleManager.changeDirectionOfVehicle(new Id(1),v1);
            assertEquals(v1,vehicleManager.getVehicle(new Id(1)).direction());
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isRunningVehicle()
    {
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        Vector v = new Vector(new Point(0,0),new Point(1,0));
        Point p = new Point(3,3);
        try {
            Vehicle vehicle = new VehicleImpl(new Id(1),v,new Point(3,3));
            vehicleManager.addVehicle(vehicle);
            Thread.sleep(1000);
            assertNotEquals(p,vehicleManager.getVehicle(new Id(1)).coordinate());
            assertNotEquals(p,vehicleManager.getCoordinateOfVehicle(new Id(1)));
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
