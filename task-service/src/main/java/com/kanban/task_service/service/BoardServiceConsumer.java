package com.kanban.task_service.service;

import com.kanban.task_service.dto.BoardEventDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BoardServiceConsumer {

    @KafkaListener(topics = "board-created-topic",
            groupId = "board-consumer-group",
            containerFactory = "boardEventDtoConcurrentKafkaListenerContainerFactory")
    public void listenMessage(BoardEventDto boardEventDto) {
        System.out.println("Received task created event: ");
        System.out.println("ID: " +  boardEventDto.boardId());
        System.out.println("Title: " + boardEventDto.title());
    }
}
