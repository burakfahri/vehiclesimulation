package com.tba.inteface;

import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;

/**
 * interface of any vehicle
 * it extends from runnable
 * must run independent from main thread
 */
public interface Vehicle extends Runnable,Cloneable {

    /**
     * id of the vehicle
     * @return type of Id
     */
    public Id id();

    /**
     * coordinate of the vehicle
     * @return current location of the vehicle
     */
    Point coordinate();

    /**
     * set the new coordinate of the vehicle
     * @param point which the location where vehicle start to move
     * @return true if operation succeed
     */
    Boolean setCoordinate(Point point);

    /**
     * change the direction of the vehicle
     * @param vector which vehicle should move
     * @return true if operation succeed
     */
    Boolean changeDirection(Vector vector);

    /**
     * @return the direction of the vehicle
     */
    Vector direction();

    /**
     * @return the clone of the vehicle
     */
    Vehicle clone();


}
