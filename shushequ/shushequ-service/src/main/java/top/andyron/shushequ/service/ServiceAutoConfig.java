package top.andyron.shushequ.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Configuration
@ComponentScan("top.andyron.shushequ.service")
@MapperScan(basePackages = {
        "top.andyron.shushequ.service.article.repository.mapper",
        "top.andyron.shushequ.service.user.repository.mapper",
        "top.andyron.shushequ.service.comment.repository.mapper",
        "top.andyron.shushequ.service.config.repository.mapper",
        "top.andyron.shushequ.service.statistics.repository.mapper",
        "top.andyron.shushequ.service.notify.repository.mapper",
        "top.andyron.shushequ.service.shortlink.repository.mapper",
})
public class ServiceAutoConfig {
}
