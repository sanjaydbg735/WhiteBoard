package com.example.WhiteBoard.service;

import com.example.WhiteBoard.model.DrawingCommand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class WhiteboardService {

    // Using ConcurrentHashMap for thread-safe access to sessions.
    // The value is a thread-safe list to handle concurrent drawing actions.
    private final Map<String, List<DrawingCommand>> commandHistory = new ConcurrentHashMap<>();

    /**
     * Adds a drawing command to the history of a specific session.
     * @param sessionId The ID of the whiteboard session.
     * @param command The drawing command to add.
     */
    public void addCommand(String sessionId, DrawingCommand command) {
        commandHistory.computeIfAbsent(sessionId, k -> new CopyOnWriteArrayList<>()).add(command);
    }

    /**
     * Retrieves the entire command history for a specific session.
     * @param sessionId The ID of the whiteboard session.
     * @return An unmodifiable list of commands, or an empty list if the session doesn't exist.
     */
    public List<DrawingCommand> getHistory(String sessionId) {
        return Collections.unmodifiableList(commandHistory.getOrDefault(sessionId, Collections.emptyList()));
    }

    /**
     * Clears the command history for a specific session.
     * @param sessionId The ID of the whiteboard session.
     */
    public void clearHistory(String sessionId) {
        commandHistory.remove(sessionId);
    }
}
