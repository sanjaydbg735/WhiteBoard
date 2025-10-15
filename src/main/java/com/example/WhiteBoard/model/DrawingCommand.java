package com.example.WhiteBoard.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// This configuration is correct and tells Jackson how to determine the object type
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tool")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FreehandPath.class, name = "pen"),
        @JsonSubTypes.Type(value = FreehandPath.class, name = "eraser"),
        @JsonSubTypes.Type(value = RectangleShape.class, name = "rectangle"),
        @JsonSubTypes.Type(value = CircleShape.class, name = "circle")
})
public abstract class DrawingCommand {

    private String color;
    private double lineWidth;

    // Getters and setters for common properties
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    // Abstract method to ensure subclasses have a way to identify their tool type
    public abstract String getTool();
}

