package com.example.WhiteBoard.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.WhiteBoard.model.DrawingDocument;

public interface DrawingRepository extends MongoRepository<DrawingDocument, String> {
    List<DrawingDocument> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
