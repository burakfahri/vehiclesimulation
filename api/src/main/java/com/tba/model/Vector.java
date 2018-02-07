package com.tba.model;

import java.util.Objects;

/**
 * holds the vector to define a direction or something else
 */
public class Vector {
    private Point startPoint;
    private Point endPoint;


    public Vector() {
    }

    public Vector(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Vector(Integer startX, Integer startY, Integer endX, Integer endY)
    {
        this(new Point(startX,startY),new Point(endX,endY));
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Point getMovement()
    {
        return new Point(endPoint.getX() - startPoint.getX(),endPoint.getY() - startPoint.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(startPoint, vector.startPoint) &&
                Objects.equals(endPoint, vector.endPoint);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startPoint, endPoint);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
