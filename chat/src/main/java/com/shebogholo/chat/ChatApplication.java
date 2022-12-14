package com.shebogholo.chat;

import com.shebogholo.chat.utils.Message;
import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
    @RequestMapping("/")
    public Message index() {
        return Message.builder().message("Chat API is up and running on a Docker!").build();
    }
}
