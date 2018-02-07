package com.tba.inteface;

import com.tba.exception.EmptyInstanceException;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.model.Id;

import java.util.Map;

/**
 * Manages the vehicles and their threads
 */
public interface VehicleManager {

    /**
     * @param id of the vehicle
     * @return vehicle holds the id
     * @throws EmptyInstanceException if the instance of Vehicle Manager is not instantiated
     */
    Vehicle getVehicle(Id id) throws EmptyInstanceException;

    /**
     * @return the all vehicles
     * @throws EmptyInstanceException if the instance of Vehicle Manager is not instantiated
     */
    Map<Id,Vehicle> getVehicles() throws EmptyInstanceException;

    /**
     *
     * @param vehicle which will add the manager and run in background
     * @throws EmptyInstanceException if the instance of Vehicle Manager is not instantiated
     */

    void addVehicle(Vehicle vehicle) throws EmptyInstanceException;

    /**
     * @param id of the vehicle would remove
     * @throws EmptyInstanceException if the instance of Vehicle Manager is not instantiated
     */

    void removeVehicle(Id id) throws EmptyInstanceException;

    /**
     * @param id of the vehicle coordinate
     * @return the current coordination of vehicle
     * @throws EmptyInstanceException if the instance of Vehicle Manager is not instantiated
     */

    Point getCoordinateOfVehicle(Id id) throws EmptyInstanceException;

    /**
     * @param id of the vehicle
     * @param vector which is direction
     * @throws EmptyInstanceException  if the instance of Vehicle Manager is not instantiated
     */
    void changeDirectionOfVehicle(Id id,Vector vector) throws EmptyInstanceException;


}
