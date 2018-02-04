import com.tba.inteface.Vehicle;
import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;
import com.tba.model.VehicleImpl;
import com.tba.util.LogUtil;
import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
    public static Logger logger = Logger.getLogger(Test.class);
    public static void main(String[] args) {
        LogUtil.setDebugLog(logger,"hoo");
        Vehicle vehicle = new VehicleImpl(new Id(1),new Vector(new Point(0,0),new Point(1,0)),new Point(0,0));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(vehicle,0,1,TimeUnit.SECONDS);
    }
}
