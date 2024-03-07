package net.guizhanss.issuetracker.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.guizhanss.issuetracker.entity.enums.TokenType;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tokens")
public class Token {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String token;
    @EnumValue
    @TableField(value = "type")
    @Builder.Default
    private TokenType tokenType = TokenType.BEARER;
    @TableField(value = "user_id")
    private Long userId;
    @TableField(value = "created_at")
    private Timestamp createdAt;
    @TableField(value = "expires_at")
    private Timestamp expiresAt;
    private boolean revoked;
}
