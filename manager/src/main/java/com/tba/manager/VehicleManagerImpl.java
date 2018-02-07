package com.tba.manager;

import com.tba.exception.EmptyInstanceException;
import com.tba.inteface.Vehicle;
import com.tba.inteface.VehicleManager;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.util.LogUtil;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;

/**
 * implementation of vehicle manager
 */
public class VehicleManagerImpl implements VehicleManager{
    /**
     * vehicle id to vehicle map holds the vehicle to get wanted vehicle from the id of vehicle
     */
    private Map<Id, Vehicle> vehicleMap = new ConcurrentHashMap<>();
    /**
     * vehicle id to executer map holds the executer belongs to vehicle
     */
    private Map<Id, ScheduledExecutorService> vehicleIdToExecuter = new ConcurrentHashMap<>();
    public static VehicleManager vehicleManager = null;
    public static Logger logger = Logger.getLogger(VehicleManagerImpl.class);

    protected VehicleManagerImpl() {}

    /**
     * @return the cureent instance of VehicleManager, if it is not exist, it instantiate one
     */
    public static synchronized VehicleManager getInstance() {
        if(vehicleManager == null)
            vehicleManager = new VehicleManagerImpl();
        return vehicleManager;
    }

    @Override
    public Vehicle getVehicle(Id id) throws EmptyInstanceException {
        checkInstance(Thread.currentThread().getStackTrace()[1].getMethodName());
        Vehicle vehicle = vehicleMap.get(id);
        if(vehicle == null)
            return null;
        return vehicleMap.get(id).clone();
    }

    @Override
    public Map<Id, Vehicle> getVehicles() throws EmptyInstanceException {
       checkInstance(Thread.currentThread().getStackTrace()[1].getMethodName());
        return  Collections.unmodifiableMap(vehicleMap);
    }

    @Override
    public synchronized void addVehicle(Vehicle vehicle) throws EmptyInstanceException {
        checkInstance(Thread.currentThread().getStackTrace()[0].getMethodName());
        vehicleMap.put(vehicle.id(),vehicle);
        createSchedulerForVehicle(vehicle);
    }

    @Override
    public synchronized void removeVehicle(Id id) throws EmptyInstanceException {
        checkInstance(Thread.currentThread().getStackTrace()[1].getMethodName());
        vehicleMap.remove(id);
        stopSchedulerForVehicle(id);
    }

    @Override
    public synchronized Point getCoordinateOfVehicle(Id id) throws EmptyInstanceException {
        checkInstance(Thread.currentThread().getStackTrace()[1].getMethodName());
        if(!vehicleMap.isEmpty()) {
            Vehicle vehicle = vehicleMap.get(id);
            if(vehicle != null)
                return vehicle.coordinate().clone();
        }
        return null;
    }

    @Override
    public synchronized void changeDirectionOfVehicle(Id id,Vector vector) throws EmptyInstanceException {

        LogUtil.setInfoLog(logger," changing is checking  "+ id);
        checkInstance(Thread.currentThread().getStackTrace()[1].getMethodName());
        LogUtil.setInfoLog(logger," changing direction "+ id);

        if(!vehicleMap.isEmpty()) {
            Vehicle vehicle = vehicleMap.get(id);
            if(vehicle != null)
                vehicle.changeDirection(vector);
        }
    }

    /**
     * checks the manager is instantiated or not
     * @param methodName which checkInstance call from
     * @throws EmptyInstanceException  if the instance of Vehicle Manager is not instantiated
     */
    private synchronized void checkInstance(String methodName) throws EmptyInstanceException {
        if(vehicleManager == null)
        {
            printInstantiateErrorLog(methodName);
            throw new EmptyInstanceException("Instance must be instantiate first, you can use getInstance method");
        }
    }

    /**
     * create scheduler and start the thread
     * @param vehicle which thread would start on backgroud
     */
    private synchronized void createSchedulerForVehicle(Vehicle vehicle)
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(vehicle,0,1, TimeUnit.SECONDS);
        vehicleIdToExecuter.put(vehicle.id(),executor);
    }

    /**
     * stop scheduler and remove thread
     * @param id of vehicle
     */
    private synchronized void stopSchedulerForVehicle(Id id)
    {
        ExecutorService executer =  vehicleIdToExecuter.get(id);
        if(executer != null) {
            executer.shutdownNow();
            vehicleIdToExecuter.remove(id);
        }
    }

    /**
     * logs the instantion error
     * @param methodName which method try to process before instantiate manager
     */
    private synchronized void printInstantiateErrorLog(String methodName)
    {
        LogUtil.setErrorLog(logger,"Vehicle manager must be instantiate first ,before calling the"+methodName +"  method");
    }
}
