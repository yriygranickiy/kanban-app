package com.kanban.task_service.service;

import com.kanban.task_service.dto.BoardEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardEventProducer {

    private final KafkaTemplate<String, BoardEventDto> kafkaTemplate;

    public void sendBoardcreatedEvent(BoardEventDto boardEventDto) {
        kafkaTemplate.send("board-created-topic", boardEventDto.boardId(), boardEventDto);
    }
}
