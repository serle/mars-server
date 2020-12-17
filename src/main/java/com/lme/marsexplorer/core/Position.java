package com.lme.marsexplorer.core;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(String positionStr, GridExtent extent) throws InputParseException {
        if (positionStr == null || positionStr.isEmpty()) throw new InputParseException("Position Empty or Null");

        String[] positionArr = positionStr.split(" ");
        if (positionArr.length != 2) throw new InputParseException("Position should contain 2 coordinates");

        //set the max_x extent if its valid
        try {
            this.x = Integer.parseInt(positionArr[0]);
        }
        catch (NumberFormatException e) {
            throw new InputParseException("X coordinate is invalid");
        }
        if (this.x < extent.getMinX() || this.x > extent.getMaxX()) throw new InputParseException("X coordinate out of range");

        //set the max_y extent if its valid
        try {
            this.y = Integer.parseInt(positionArr[1]);
        }
        catch (NumberFormatException e){
            throw new InputParseException("Y grid extent coordinate is invalid");
        }
        if (this.x < extent.getMinX() || this.x > extent.getMaxX()) throw new InputParseException("Y coordinate out of range");
    }



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
