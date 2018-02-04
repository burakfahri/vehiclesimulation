package com.tba.manager;

import com.tba.inteface.Vehicle;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleManagerImpl implements VehicleManager{
    Map<Integer, Vehicle> vehicleMap = new ConcurrentHashMap<>();

    @Override
    public VehicleManager getVehicleManager() {
        return null;
    }

    @Override
    public Map<Integer, Vehicle> getVehicles() {
        return null;
    }

    @Override
    public Boolean addVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Boolean removeVehicle(Id id) {
        return null;
    }

    @Override
    public Point getCoordinateOfVehicle(Id id) {
        return null;
    }

    @Override
    public Boolean changeDirectionOfVehicle(Vector vector) {
        return null;
    }
}
