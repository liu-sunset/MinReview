package peng.zhi.liu.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.constant.UserConstant;
import peng.zhi.liu.dto.AiChatDTO;
import peng.zhi.liu.exception.UserException;
import peng.zhi.liu.service.AIChatService;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/user")
public class AIChatController {
    @Autowired
    private AIChatService aiChatService;

    @JwtInspect
    @PostMapping(value = "/ai",produces = "text/html;charset= utf-8")
    public Flux<String> aiChatController(@RequestBody AiChatDTO aiChatDTO){
        log.info("用户信息:{}",aiChatDTO);
        return aiChatService.chat(aiChatDTO.getMemoryId(),aiChatDTO.getMessage());
    }
}
