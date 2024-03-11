package net.guizhanss.issuetracker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.guizhanss.issuetracker.entity.Issue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IssueMapper extends BaseMapper<Issue> {
}
