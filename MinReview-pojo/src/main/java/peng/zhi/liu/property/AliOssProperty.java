package peng.zhi.liu.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sky.aliyunoss")
public class AliOssProperty {
    private String bucketName;
    private String endpoint;
    private String region;


}
