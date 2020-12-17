package com.lme.marsexplorer.core;

class GridExtent {
    private static int MIN_EXTENT = 0;
    private static int MAX_EXTENT = 50;

    //preventative measure if allowed to be non-zero in future
    private int min_x;
    private int min_y;
    private int max_x;
    private int max_y;

    //receives input string: "5 3", should set max_x = 5, max_y = 3 and throw an exception if max_x | max_y > 50
    GridExtent(String extentStr) throws InputParseException {
        if (extentStr == null || extentStr.isEmpty()) throw new InputParseException("No Grid Extent");

        String[] extentArr = extentStr.split(" ");
        if (extentArr.length != 2) throw new InputParseException("Grid Extent should contain two integers");

        //constants
        this.min_x = MIN_EXTENT;
        this.min_y = MIN_EXTENT;

        //set the max_x extent if its valid
        try {
            this.max_x = Integer.parseInt(extentArr[0]);
        }
        catch (NumberFormatException e) {
            throw new InputParseException("X grid extent coordinate is invalid");
        }
        if (this.max_x > MAX_EXTENT || this.max_x < MIN_EXTENT) throw new InputParseException("X grid extent coordinate out of range, should be in [0;50]");

        //set the max_y extent if its valid
        try {
            this.max_y = Integer.parseInt(extentArr[1]);
        }
        catch (NumberFormatException e){
            throw new InputParseException("Y grid extent coordinate is invalid");
        }
        if (this.max_y > MAX_EXTENT || this.max_y < MIN_EXTENT) throw new InputParseException("Y grid extent coordinate out of range, should be in [0;50]");
    }

    GridExtent(int maxX, int maxY) {
        this.min_x = 0;
        this.min_y = 0;
        this.max_x = maxX;
        this.max_y = maxY;
    }

    public int getMaxX() {
        return max_x;
    }

    public int getMaxY() {
        return max_y;
    }

    public int getMinX() {
        return min_x;
    }

    public int getMinY() {
        return min_y;
    }

    public boolean isOnGrid(int x, int y) {
        return (x >= min_x && x <= max_x) && (y >= min_y && y <= max_y);
    }

    public boolean isOffGrid(int x, int y) {
        return !isOnGrid(x, y);
    }

    public String toString() {
        return String.format("%d %d", max_x, max_y);
    }
}
