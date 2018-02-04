package com.tba.inteface;

import com.tba.model.Point;
import com.tba.model.Vector;

public interface Vehicle extends Runnable {

    Point coordinate();

    Boolean setCoordinate(Point point);

    Boolean changeDirection(Vector vector);

    Vector direction();

}
