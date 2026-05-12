package com.genericAI;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatModel chatModel;

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/api/chat")
    public ResponseEntity<String> generateContent(@RequestBody Map<String, String> request) {
        String userPrompt = request.get("prompt");
        if (userPrompt == null || userPrompt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Prompt is required");
        }
        
        ChatResponse response = chatModel.call(new Prompt(userPrompt));
        return ResponseEntity.ok(response.getResult().getOutput().getContent());
    }

    @GetMapping("/")
    public String greet(){
        return "Hello from the GenericAI";
    }
}
