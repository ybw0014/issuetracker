package net.guizhanss.issuetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    public static Response success(String message, Object data) {
        return new Response(
            0,
            message,
            data
        );
    }

    public static Response success(Object data) {
        return success("Success", data);
    }

    public static Response error(int code, String message) {
        return new Response(
            code,
            message,
            null
        );
    }
}
