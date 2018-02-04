package com.tba.manager;

import com.tba.exception.EmptyInstanceException;
import com.tba.inteface.Vehicle;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.util.LogUtil;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleManagerImpl implements VehicleManager{
    private Map<Id, Vehicle> vehicleMap = new ConcurrentHashMap<>();
    private VehicleManager vehicleManager = null;
    public static Logger logger = Logger.getLogger(VehicleManagerImpl.class);

    protected VehicleManagerImpl() {};

    @Override
    public synchronized VehicleManager getInstance() {
        if(vehicleManager == null)
            vehicleManager = new VehicleManagerImpl();
        return vehicleManager;
    }

    @Override
    public Map<Id, Vehicle> getVehicles() throws EmptyInstanceException {
       checkInstance(this.getClass().getEnclosingMethod().getName());
        return vehicleMap;
    }

    @Override
    public synchronized Boolean addVehicle(Vehicle vehicle) throws EmptyInstanceException {
        checkInstance(this.getClass().getEnclosingMethod().getName());
        vehicleMap.put(vehicle.id(),vehicle);
        return true;
    }

    @Override
    public synchronized Boolean removeVehicle(Id id) throws EmptyInstanceException {
        checkInstance(this.getClass().getEnclosingMethod().getName());
        vehicleMap.remove(id);
        return null;
    }

    @Override
    public synchronized Point getCoordinateOfVehicle(Id id) throws EmptyInstanceException {
        checkInstance(this.getClass().getEnclosingMethod().getName());
        if(!vehicleMap.isEmpty()) {
            Vehicle vehicle = vehicleMap.get(id);
            if(vehicle != null)
                return vehicle.coordinate();
        }
        return null;
    }

    @Override
    public synchronized Boolean changeDirectionOfVehicle(Id id,Vector vector) throws EmptyInstanceException {
        checkInstance(this.getClass().getEnclosingMethod().getName());
        if(!vehicleMap.isEmpty()) {
            Vehicle vehicle = vehicleMap.get(id);
            if(vehicle != null)
                return vehicle.changeDirection(vector);
            return false;
        }
        return false;
    }

    private synchronized void checkInstance(String className) throws EmptyInstanceException {
        if(vehicleManager == null)
        {
            printInstantiateErrorLog(this.getClass().getEnclosingMethod().getName());
            throw new EmptyInstanceException("Instance must be instantiate first, you can use getInstance method");
        }
    }

    private synchronized void printInstantiateErrorLog(String methodName)
    {
        LogUtil.setErrorLog(logger,"Vehicle manager must be instantiate first ,before calling the"+methodName +"  method");
    }
}
