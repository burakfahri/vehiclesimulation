package com.tba.inteface;

import com.tba.model.Id;
import com.tba.model.Point;
import com.tba.model.Vector;

public interface Vehicle extends Runnable {

    public Id id();

    Point coordinate();

    Boolean setCoordinate(Point point);

    Boolean changeDirection(Vector vector);

    Vector direction();

}
