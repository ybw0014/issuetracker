package net.guizhanss.issuetracker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.guizhanss.issuetracker.entity.Token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper extends BaseMapper<Token> {
}
