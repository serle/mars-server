package com.lme.marsexplorer.core;

class GridExtent {
    static int MIN_EXTENT = 0;
    static int MAX_EXTENT = 50;

    int max_x;
    int max_y;

    //receives input string: "5 3", should set max_x = 5, max_y = 3 and throw an exception if max_x | max_y > 50
    GridExtent(String extentStr) throws InputParseException {
        if (extentStr == null || extentStr.isEmpty()) throw new InputParseException("No Grid Extent");

        String[] extentArr = extentStr.split(" ");
        if (extentArr.length != 2) throw new InputParseException("Grid Extent should contain two integers");

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

    public String toString() {
        return String.format("%d %d", max_x, max_y);
    }
}
