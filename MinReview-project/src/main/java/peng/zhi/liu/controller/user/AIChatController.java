package peng.zhi.liu.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.AIChatService;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/user")
public class AIChatController {
    @Autowired
    private AIChatService aiChatService;

    @PostMapping(value = "/ai",produces = "text/html;charset= utf-8")
    public Flux<String> aiChatController(Object memoryId, String message){
        return aiChatService.chat(memoryId, message);
    }
}
