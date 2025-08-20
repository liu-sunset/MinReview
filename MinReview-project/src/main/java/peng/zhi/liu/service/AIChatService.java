package peng.zhi.liu.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel = "openAiStreamingChatModel",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface AIChatService {
    @SystemMessage("你是这个学校餐厅点评网站的助手，可以帮助解答一些关于这个网站的相关信息")
    public Flux<String> chat(@MemoryId Object memoryId,@UserMessage String message);
}
