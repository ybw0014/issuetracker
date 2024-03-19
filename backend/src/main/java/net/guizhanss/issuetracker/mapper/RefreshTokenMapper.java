package net.guizhanss.issuetracker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.guizhanss.issuetracker.entity.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper extends BaseMapper<RefreshToken> {
}
