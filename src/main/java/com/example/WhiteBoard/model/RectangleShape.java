package com.example.WhiteBoard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RectangleShape extends DrawingCommand {
    private final String tool;
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    @JsonCreator
    public RectangleShape(
            @JsonProperty("tool") String tool,
            @JsonProperty("x") double x,
            @JsonProperty("y") double y,
            @JsonProperty("width") double width,
            @JsonProperty("height") double height,
            @JsonProperty("color") String color,
            @JsonProperty("lineWidth") double lineWidth) {
        this.tool = tool;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setColor(color);
        setLineWidth(lineWidth);
    }

    // Getters
    @JsonIgnore
    @Override
    public String getTool() { return tool; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}

