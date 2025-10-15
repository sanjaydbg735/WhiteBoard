package com.example.WhiteBoard.controller;
import com.example.WhiteBoard.model.DrawingCommand;
import com.example.WhiteBoard.service.WhiteboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.Collections;
import java.util.List;


import java.util.Collections;
import java.util.List;
import java.util.Map;


@Controller
public class WhiteboardController {

    // 1. Add a Logger instance
    private static final Logger logger = LoggerFactory.getLogger(WhiteboardController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WhiteboardService whiteboardService;

    @MessageMapping("/whiteboard/{sessionId}")
    @SendTo("/topic/whiteboard/{sessionId}")
    public DrawingCommand handleDrawing(@DestinationVariable String sessionId, @Payload DrawingCommand command) {
        // 2. Add detailed logs for receiving and processing a drawing command
        logger.info("Received a drawing command for session ID: {}", sessionId);
        if (command != null) {
            logger.info("Command details: Tool='{}', Color='{}'", command.getTool(), command.getColor());
        } else {
            logger.warn("Received a null command object for session ID: {}", sessionId);
            return null; // Avoids further errors
        }

        whiteboardService.addCommand(sessionId, command);
        logger.info("Command added to history. Broadcasting to /topic/whiteboard/{}", sessionId);

        return command;
    }

    @MessageMapping("/whiteboard/{sessionId}/history")
    public void getHistory(@DestinationVariable String sessionId, SimpMessageHeaderAccessor headerAccessor) {
        // 3. Add detailed logs for the history request
        String userSessionId = headerAccessor.getSessionId();
        logger.info("Received history request for session ID: {} from user session: {}", sessionId, userSessionId);

        List<DrawingCommand> history = whiteboardService.getHistory(sessionId);
        logger.info("Found {} commands in history for session ID: {}. Sending to user.", history.size(), sessionId);

        messagingTemplate.convertAndSendToUser(
                userSessionId,
                "/topic/whiteboard/" + sessionId + "/history",
                history,
                Collections.singletonMap("content-type", "application/json")
        );
        logger.info("Successfully sent history to user session: {}", userSessionId);
    }

    @MessageMapping("/whiteboard/{sessionId}/clear")
    @SendTo("/topic/whiteboard/{sessionId}")
    public Object clearWhiteboard(@DestinationVariable String sessionId) {
        // 4. Add detailed logs for the clear request
        logger.info("Received clear request for session ID: {}", sessionId);
        whiteboardService.clearHistory(sessionId);
        logger.info("History cleared. Broadcasting CLEAR command to /topic/whiteboard/{}", sessionId);
        return Collections.singletonMap("type", "CLEAR");
    }
}

