package com.lme.marsexplorer.core;

enum Orientation {
    N,S,E,W;

    static public Orientation toOrientation(String token) throws InputParseException {
        try {
            return Orientation.valueOf(token);
        }
        catch (Exception e) {
            throw new InputParseException("Invalid orientation");
        }
    }
}
