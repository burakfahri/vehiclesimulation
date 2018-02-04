package com.tba.inteface;

import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.model.Id;

import java.util.Map;

public interface VehicleManager {

    VehicleManager getVehicleManager();

    Map<Integer,Vehicle> getVehicles();

    Boolean addVehicle(Vehicle vehicle);

    Boolean removeVehicle(Id id);

    Point getCoordinateOfVehicle(Id id);

    Boolean changeDirectionOfVehicle(Vector vector);


}
