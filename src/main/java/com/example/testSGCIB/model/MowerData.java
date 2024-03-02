package com.example.testSGCIB.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MowerData {

    private Point mapSize;
    private List<Mower> mowersPositionDirection;
    private List<String> mowersInstructions;

    public MowerData() {
        this.mapSize = new Point();
        this.mowersPositionDirection = new ArrayList<>();
        this.mowersInstructions = new ArrayList<>();
    }

    public Point getMapSize() {
        return mapSize;
    }

    public void setMapSize(Point mapSize) {
        this.mapSize = mapSize;
    }

    public List<Mower> getMowersPositionDirection() {
        return mowersPositionDirection;
    }

    public void setMowersPositionDirection(List<Mower> mowersPositionDirection) {
        this.mowersPositionDirection = mowersPositionDirection;
    }

    public List<String> getMowersInstructions() {
        return mowersInstructions;
    }

    public void setMowersInstructions(List<String> mowersInstructions) {
        this.mowersInstructions = mowersInstructions;
    }
}
