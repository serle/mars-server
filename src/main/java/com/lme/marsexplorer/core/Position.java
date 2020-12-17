package com.lme.marsexplorer.core;

import java.util.Objects;

class Position {
    private int x;
    private int y;

    public Position(String positionStr, GridExtent extent) throws InputParseException {
        if (positionStr == null || positionStr.isEmpty()) throw new InputParseException("Position Empty or Null");

        String[] positionArr = positionStr.split(" ");
        if (positionArr.length != 2) throw new InputParseException("Position should contain 2 coordinates");

        //set the max_x extent if its valid
        try {
            x = Integer.parseInt(positionArr[0]);
        }
        catch (NumberFormatException e) {
            throw new InputParseException("X extent coordinate is invalid");
        }

        //set the max_y extent if its valid
        try {
            y = Integer.parseInt(positionArr[1]);
        }
        catch (NumberFormatException e){
            throw new InputParseException("Y extent coordinate is invalid");
        }

        if (extent.isOffGrid(x, y)) {
            throw new InputParseException("Coordinate out of range");
        }
    }

    public Position(int x, int y) {
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
