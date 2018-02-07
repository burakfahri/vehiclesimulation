package com.tba.model;

import com.tba.inteface.Vehicle;

import com.tba.util.LogUtil;
import org.apache.log4j.Logger;


public class VehicleImpl implements Vehicle{
    Logger logger = Logger.getLogger(Vehicle.class);

    Id id;
    Vector direction;
    Vector movement;

    private VehicleImpl() {
    }

    public VehicleImpl(Id id, Vector direction,Point startPoint) {
        setId(id);
        if(startPoint == null)
        {
            LogUtil.setErrorLog(logger,"StartPoint parameter can not be null ");
            return ;
        }
        changeDirection(direction);
        movement = new Vector();
        this.movement.setStartPoint(startPoint);
    }

    public Id id() {
        return id;
    }

    public void setId(Id id) {
        if(id == null)
        {
            LogUtil.setErrorLog(logger,"Id parameter can not be null ");
            return;
        }
        this.id = id;
    }

    @Override
    public Point coordinate() {
        return movement.getEndPoint();
    }

    @Override
    public Boolean setCoordinate(Point point) {
        if(point == null)
        {
            LogUtil.setErrorLog(logger,"Point parameter can not be null ");
            return false;
        }
        this.movement.setEndPoint(point);
        return true;
    }

    @Override
    public Boolean changeDirection(Vector vector) {
        if(vector == null)
        {
            LogUtil.setErrorLog(logger,"Vector parameter can not be null ");
            return false;
        }
        this.direction = vector;
        return true;
    }

    @Override
    public Vector direction() {
        return direction;
    }

    @Override
    public void run() {
        try {
            if (movement.getEndPoint() == null) {
                LogUtil.setInfoLog(logger, "Initializing the vehicle");
                Point startPoint = movement.getStartPoint();
                if (startPoint == null) {
                    LogUtil.setErrorLog(logger, "Object can not be initialized because start point is null");
                    return;
                }
                movement.setEndPoint(startPoint.clone());
            }
            movement.getEndPoint().add(direction.getMovement());
        }
        catch (Exception e)
        {
            LogUtil.setErrorLog(logger,e.getMessage());
        }
    }

    public Vehicle clone() {
        try {
            return (Vehicle) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "VehicleImpl{" +
                "id=" + id +
                ", direction=" + direction +
                ", movement=" + movement +
                '}';
    }
}
