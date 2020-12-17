package com.lme.marsexplorer.core;

import java.util.Objects;

public class Position {
    private int x;
    private int y;


    public Position(int x, int y, GridExtent extent) throws InvalidPositionException {
        if (x < extent.getMinX() || x > extent.getMaxX()) throw new InvalidPositionException("X coordinate out of bounds");
        if (y < extent.getMinY() || y > extent.getMaxY()) throw new InvalidPositionException("Y coordinate out of bounds");

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("%d %d", this.x, this.y);
    }
}
