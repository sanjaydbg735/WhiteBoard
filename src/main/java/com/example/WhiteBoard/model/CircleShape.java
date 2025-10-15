package com.example.WhiteBoard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CircleShape extends DrawingCommand {
    private final String tool;
    private final double x;
    private final double y;
    private final double radius;

    @JsonCreator
    public CircleShape(
            @JsonProperty("tool") String tool,
            @JsonProperty("x") double x,
            @JsonProperty("y") double y,
            @JsonProperty("radius") double radius,
            @JsonProperty("color") String color,
            @JsonProperty("lineWidth") double lineWidth) {
        this.tool = tool;
        this.x = x;
        this.y = y;
        this.radius = radius;
        setColor(color);
        setLineWidth(lineWidth);
    }

    // Getters
    @JsonIgnore
    @Override
    public String getTool() { return tool; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
}

