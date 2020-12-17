package com.lme.marsexplorer.core;

import java.util.HashSet;


/*
    Instead of allocating a 2D grid array in order to be able to check for its extents (array boundaries) and
    the cells where a smell has be found, I decided to encapsulate the extent and smells into this GridState
    class. An instance of this class is created once per service request and updated as each of the robots
    navigate across the grid.

    This should be more memory efficient for large arrays with fewer smells, which is probably the norm. Also
    should we need to pass the smells between the client and server (potentially to keep the server stateless for
    scalability, this would be a more efficient data structure to pass around.

 */
public class GridState {
    private GridExtent extent;
    private HashSet<Position> smells;

    GridState(String lineStr) throws InputParseException {
        this.extent = new GridExtent(lineStr);
        this.smells = new HashSet<>();
    }

    GridState(int maxX, int maxY) {
        this.extent = new GridExtent(maxX, maxY);
        this.smells = new HashSet<>();
    }

    public GridExtent getExtent() {
        return extent;
    }

    public boolean isOnGrid(int x, int y) {
        return extent.isOnGrid(x, y);
    }

    public void addSmellAt(int x, int y) {
        if (extent.isOnGrid(x, y)) {
            smells.add(new Position(x, y));
        }
    }

    public boolean isSmellAt(int x, int y) {
        return smells.contains(new Position(x, y));
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\n");

        sb.append(String.format("  Extent: %s", extent.toString()));
        sb.append("\n");

        sb.append("  Smells: [");
        for (Position smell : smells) {
            sb.append("(");
            sb.append(smell.toString());
            sb.append(")");
            sb.append(", ");
        }
        //sb.deleteCharAt(sb.lastIndexOf(", "));
        sb.append("  ]");
        sb.append("\n");
        sb.append("}");

        return sb.toString();
    }
}
