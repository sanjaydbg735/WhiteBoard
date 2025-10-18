package com.example.WhiteBoard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "whiteBardDrawing")
public class DrawingDocument {

    @Id
    private String id;
    private String sessionId;

    private DrawingCommand data; // store coordinates, path, etc.
    public Date date;
    public DrawingDocument() {}

    public DrawingDocument(String sessionId, DrawingCommand data) {
        this.date = new Date();
        this.sessionId = sessionId;
        this.data = data;
    }

    public DrawingCommand getData() {
        return data;
    }

    public void setData(DrawingCommand data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // getters and setters
}