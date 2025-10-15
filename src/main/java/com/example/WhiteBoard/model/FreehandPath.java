package com.example.WhiteBoard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class FreehandPath extends DrawingCommand {
    private final String tool;
    private final List<Map<String, Double>> points;

    // The @JsonCreator annotation tells Jackson to use this constructor
    // The @JsonProperty annotations map the JSON fields directly to the constructor arguments
    @JsonCreator
    public FreehandPath(
            @JsonProperty("tool") String tool,
            @JsonProperty("points") List<Map<String, Double>> points,
            @JsonProperty("color") String color,
            @JsonProperty("lineWidth") double lineWidth) {
        this.tool = tool;
        this.points = points;
        setColor(color);
        setLineWidth(lineWidth);
    }

    // Getters
    @JsonIgnore
    @Override
    public String getTool() {
        return tool;
    }

    public List<Map<String, Double>> getPoints() {
        return points;
    }
}

