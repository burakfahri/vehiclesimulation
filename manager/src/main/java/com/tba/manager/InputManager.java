package com.tba.manager;

import com.tba.exception.EmptyInstanceException;
import com.tba.inteface.Vehicle;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.model.VehicleImpl;
import com.tba.util.LogUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * take the input and action for the type of the input
 * Add a vehicle : "add coordinate(point) direction(vector)" - "add (0,0) ((0,0),(1,1))"  command,
 .List vehicles : "list" ,
 .Remove a vehicle : "remove id(int) " - "remove 1" ,
 .Change a vehicle direction "change_direction id(int) vector" - "change_direction 1 ((0,0),(1,0))
 */
public class InputManager {
    private static Logger logger = Logger.getLogger(InputManager.class);

    public static String handleInput(String input) {
        if(input.toLowerCase().equals("help"))
            return  getHelpMessage();
        else if(input.toLowerCase().equals("example"))
            return getExampleUsage();
        else if(input.toLowerCase().equals("list"))
            return getList();
        else if(input.toLowerCase().contains("add"))
            return add(input);
        else if(input.toLowerCase().contains("remove"))
            return  remove(input);
        else if(input.toLowerCase().contains("change_direction"))
            return change_direction(input);
        return null;
    }

    private static  String change_direction(String input) {
        if (input == null)
            return "Change a vehicle direction \\\"change_direction id(int) vector\\\" - \\\"change_direction 1 ((0,0),(1,0)) \\\"\"";
        String [] inputArr = input.split(" ");
        Vector v = null;
        Id id = null;
        for (String s : inputArr) {
            if (s.toLowerCase().equals("change_direction"))
                continue;
            System.out.println(s);
            String[] coordinate = s.split(",");

            if(coordinate.length == 4)
            {
                try {
                    Point startPoint = new Point();
                    String[] xSArr = coordinate[0].split("\\(");
                    Integer x = Integer.parseInt(xSArr[xSArr.length-1]);

                    String[] ySArr = coordinate[1].split("\\)");
                    Integer y = Integer.parseInt(ySArr[0]);
                    startPoint.setX(x);
                    startPoint.setY(y);

                    Point endPoint = new Point();
                    xSArr = coordinate[2].split("\\(");
                    x = Integer.parseInt(xSArr[xSArr.length-1]);

                    ySArr = coordinate[3].split("\\)");
                    y = Integer.parseInt(ySArr[0]);
                    endPoint.setX(x);
                    endPoint.setY(y);
                    v = new Vector();
                    v.setStartPoint(startPoint);
                    v.setEndPoint(endPoint);

                }
                catch (Exception e)
                {
                    return "There is an error while parsing vector data \nVector must be in ((X,Y),(X,Y)) format\n ";
                }
            }
            else
            {
                try{
                    id = new Id(Integer.parseInt(s));
                }
                catch (NumberFormatException e)
                {
                   return "There is an illegal character in the command";
                }
            }
        }
        if(v == null || id == null)
        {
            LogUtil.setErrorLog(logger,"VehicleManager must be instantiate first");
            return null;
        }
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            vehicleManager.changeDirectionOfVehicle(id,v);
        } catch (EmptyInstanceException e) {
            LogUtil.setErrorLog(logger,"VehicleManager must be instantiate first");
        }

        return "Direction of vehicle has id "+ id + " changed correctly";
    }

    private static  String remove(String input) {
        if (input == null)
            return "Remove a vehicle : \"remove id(int) \" - \"remove 1\" \n";
        String [] inputArr = input.split(" ");
        Id id = null;
        for (String s : inputArr) {
            if (s.toLowerCase().equals("remove"))
                continue;
            try {
                id = new Id(Integer.parseInt(s));
            }
            catch (NumberFormatException e)
            {
                return "Illegal character "+s;
            }
        }
        if(id == null) {
            LogUtil.setErrorLog(logger, "VehicleManager must be instantiate first");
            return null;
        }
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            vehicleManager.removeVehicle(id);
        } catch (EmptyInstanceException e) {
            LogUtil.setErrorLog(logger,"VehicleManager must be instantiate first");
        }
        return "Vehicle removed correctly";
    }

    private static  String add(String input) {
        if (input == null)
            return "Add a vehicle : \"add coordinate(point) direction(vector)\" - \"add (0,0) ((0,0),(1,1))\"  command \n";
        String [] inputArr = input.split(" ");
        System.out.println(input);
        Point p = null;
        Vector v = null;
        for (String s : inputArr)
        {
            if (s.toLowerCase().equals("add"))
                continue;
            String[] coordinate = s.split(",");
            if(coordinate.length == 2)
            {
                try {
                    String[] xSArr = coordinate[0].split("\\(");
                    Integer x = Integer.parseInt(xSArr[xSArr.length-1]);

                    String[] ySArr = coordinate[1].split("\\)");
                    Integer y = Integer.parseInt(ySArr[0]);
                    p = new Point();
                    p.setX(x);
                    p.setY(y);
                }
                catch (Exception e)
                {
                    return "There is an error while parsing coordinate data \nPoint must be in (X,Y) format\n ";
                }
            }
            else if(coordinate.length == 4)
            {
                try {
                    Point startPoint = new Point();
                    String[] xSArr = coordinate[0].split("\\(");
                    Integer x = Integer.parseInt(xSArr[xSArr.length-1]);

                    String[] ySArr = coordinate[1].split("\\)");
                    Integer y = Integer.parseInt(ySArr[0]);
                    startPoint.setX(x);
                    startPoint.setY(y);

                    Point endPoint = new Point();
                    xSArr = coordinate[2].split("\\(");
                    x = Integer.parseInt(xSArr[xSArr.length-1]);

                    ySArr = coordinate[3].split("\\)");
                    y = Integer.parseInt(ySArr[0]);
                    endPoint.setX(x);
                    endPoint.setY(y);
                    v = new Vector();
                    v.setStartPoint(startPoint);
                    v.setEndPoint(endPoint);
                    System.out.println("vector added "+v);

                }
                catch (Exception e)
                {
                    return "There is an error while parsing vector data \nVector must be in ((X,Y),(X,Y)) format\n ";
                }
            }
            else
                return "Illegal character "+s;
        }
        if(v == null || p == null)
        {
            LogUtil.setErrorLog(logger,"VehicleManager must be instantiate first");
            return null;
        }
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            Id id = null;
            Map<Id,Vehicle> vehicleMap = vehicleManager.getVehicles();
            if(vehicleMap.isEmpty() ) {
                id =new Id(1);
            }
            else {
                for (int i =1 ; i< vehicleManager.getVehicles().size()+2; i++)
                    if(vehicleManager.getVehicle(new Id(i)) == null)
                    {
                        id = new Id(i);
                    }
            }
            Vehicle vehicle = new VehicleImpl(id,v,p);
            vehicleManager.addVehicle(vehicle);
        } catch (EmptyInstanceException e) {
            LogUtil.setErrorLog(logger,"VehicleManager must be instantiate first");
        }
        return "Vehicle added correctly";
    }

    private static String getList() {
        StringBuilder stringBuilder = new StringBuilder();
        VehicleManager vehicleManager = VehicleManagerImpl.getInstance();
        try {
            Map<Id,Vehicle> vehicleMap = vehicleManager.getVehicles();
            if(vehicleMap == null || vehicleMap.isEmpty())
                return stringBuilder.toString();
            vehicleMap.forEach((id, vehicle) -> stringBuilder.append(id).append(" ").append(vehicle).append("\n"));
        } catch (EmptyInstanceException e) {
            LogUtil.setErrorLog(logger,"VehicleManager must be instantiate first");
        }
        return stringBuilder.toString();
    }

    public static String getHelpMessage() {
        StringBuilder stringBuilder = new StringBuilder().append("Add a vehicle use \"add\" command \n")
                .append("For listing the vehicles, use \"list\" command \n")
                .append("For removing a vehicle, use \"remove\" command \n")
                .append("For changing the coordinate of a vehicle, use \"change_direction\" command")
                .append("For example usage, use \"example\" command");
        return stringBuilder.toString();
    }

    private static String getExampleUsage() {
        StringBuilder stringBuilder = new StringBuilder().append("Add a vehicle : \"add coordinate(point) direction(vector)\" - \"add (0,0) ((0,0),(1,1))\"  command \n")
                .append("List vehicles : \"list\" \n")
                .append("Remove a vehicle : \"remove id(int) \" - \"remove 1\" \n")
                .append("Change a vehicle direction \"change_direction id(int) vector\" - \"change_direction 1 ((0,0),(1,0)) \"");
        return stringBuilder.toString();
    }
}
