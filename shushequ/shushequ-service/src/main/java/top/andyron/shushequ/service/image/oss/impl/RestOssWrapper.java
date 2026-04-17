package top.andyron.shushequ.service.image.oss.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import top.andyron.shushequ.core.config.ImageProperties;
import top.andyron.shushequ.core.net.HttpRequestHelper;
import top.andyron.shushequ.core.util.JsonUtil;
import top.andyron.shushequ.core.util.StopWatchUtil;
import top.andyron.shushequ.service.image.oss.ImageUploader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于http的文件上传
 *
 * @author andyron
 * @date 2026/4/17
 */
@Slf4j
@Component
@ConditionalOnExpression(value = "#{'rest'.equals(environment.getProperty('image.oss.type'))}")
public class RestOssWrapper implements ImageUploader {
    @Autowired
    private ImageProperties properties;

    @Override
    public String upload(InputStream input, String fileType) {
        StopWatchUtil stopWatchUtil = StopWatchUtil.init("图片上传");
        try {
            byte[] bytes = stopWatchUtil.record("转字节", () -> StreamUtils.copyToByteArray(input));
            String res = stopWatchUtil.record("上传", () -> HttpRequestHelper.upload(properties.getOss().getEndpoint(), "image", "img." + fileType, bytes));
            HashMap<?, ?> map = JsonUtil.toObj(res, HashMap.class);
            return (String) ((Map<?, ?>) map.get("result")).get("imagePath");
        } catch (Exception e) {
            log.error("upload image error response! uri:{}", properties.getOss().getEndpoint(), e);
            return null;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("upload Image cost: {}", stopWatchUtil.prettyPrint());
            }
        }
    }

    @Override
    public boolean uploadIgnore(String fileUrl) {
        if (StringUtils.isNotBlank(properties.getOss().getHost()) && fileUrl.startsWith(properties.getOss().getHost())) {
            return true;
        }
        return !fileUrl.startsWith("http");
    }
}
