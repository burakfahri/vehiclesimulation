package com.tba.manager;

import com.tba.exception.EmptyInstanceException;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class InputManagerTest {
    @Test
    public void testHelpMessage()
    {
        String s = InputManager.handleInput("help");
        assertEquals(s,InputManager.getHelpMessage());
    }

    @Test
    public void testExampleMessage()
    {
        String s = InputManager.handleInput("example");
        StringBuilder stringBuilder = new StringBuilder().append("Add a vehicle : \"add coordinate(point) direction(vector)\" - \"add (0,0) ((0,0),(1,1))\"  command \n")
                .append("List vehicles : \"list\" \n")
                .append("Remove a vehicle : \"remove id(int) \" - \"remove 1\" \n")
                .append("Change a vehicle direction \"change_direction id(int) vector\" - \"change_direction 1 ((0,0),(1,0)) \"");
        assertEquals(s,stringBuilder.toString());
    }

    @Test
    public void testAddMessage()
    {
        InputManager.handleInput("add (0,0) ((0,0),(1,1))");
        InputManager.handleInput("add (0,0) ((0,0),(1,1))");

        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            assertEquals(2,vehicleManager.getVehicles().size());
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }
    private void removeAll(VehicleManager vehicleManager)
    {
        try {
            vehicleManager.getVehicles().forEach((id, vehicle) -> {
                try {
                    vehicleManager.removeVehicle(id);
                } catch (EmptyInstanceException e) {
                    e.printStackTrace();
                }
            });
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveMessage()
    {

        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        removeAll(vehicleManager);
        InputManager.handleInput("add (0,0) ((0,0),(1,1))");
        try {
            assertEquals(1,vehicleManager.getVehicles().size());
            InputManager.handleInput("remove 1");
            assertEquals(0,vehicleManager.getVehicles().size());
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChangeDirection()
    {

        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        removeAll(vehicleManager);
        InputManager.handleInput("add (0,0) ((0,0),(1,1))");
        try {
            assertEquals(1,vehicleManager.getVehicles().size());
            InputManager.handleInput("change_direction 1 ((0,0),(1,0))");
            assertEquals(new Vector(new Point(0,0),new Point(1,0)),vehicleManager.getVehicle(new Id(1)).direction());
            assertNotEquals(new Vector(new Point(0,0),new Point(1,1)),vehicleManager.getVehicle(new Id(1)).direction());
        } catch (EmptyInstanceException e) {
            e.printStackTrace();
        }
    }

}
