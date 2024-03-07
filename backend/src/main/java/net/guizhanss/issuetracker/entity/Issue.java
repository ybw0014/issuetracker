package net.guizhanss.issuetracker.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("issues")
public class Issue {
    @TableId
    private Long id;
    private String owner;
    private String repository;
    @TableField(value = "issue_id")
    private Long issueId;
}
