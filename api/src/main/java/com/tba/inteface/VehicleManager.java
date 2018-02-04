package com.tba.inteface;

import com.tba.exception.EmptyInstanceException;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.model.Id;

import java.util.Map;

public interface VehicleManager {

    VehicleManager getInstance();

    Map<Id,Vehicle> getVehicles() throws EmptyInstanceException;

    Boolean addVehicle(Vehicle vehicle) throws EmptyInstanceException;

    Boolean removeVehicle(Id id) throws EmptyInstanceException;

    Point getCoordinateOfVehicle(Id id) throws EmptyInstanceException;

    Boolean changeDirectionOfVehicle(Id id,Vector vector) throws EmptyInstanceException;


}
