package com.tba.model;

import java.util.Objects;

/**
 * encapsulate the id of vehicle
 */
public class Id {
    int uid;

    private Id() {
    }

    public Id(int uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id ıd = (Id) o;
        return uid == ıd.uid;
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "Id{" +
                "uid=" + uid +
                '}';
    }
}
