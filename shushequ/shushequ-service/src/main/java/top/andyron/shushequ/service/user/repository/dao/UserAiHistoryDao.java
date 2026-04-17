package top.andyron.shushequ.service.user.repository.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import top.andyron.shushequ.service.user.repository.entity.UserAiHistoryDO;
import top.andyron.shushequ.service.user.repository.mapper.UserAiHistoryMapper;

import javax.annotation.Resource;

@Repository
public class UserAiHistoryDao extends ServiceImpl<UserAiHistoryMapper, UserAiHistoryDO> {

    @Resource
    private UserAiHistoryMapper userAiHistoryMapper;
}

