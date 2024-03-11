package net.guizhanss.issuetracker.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseError {
    UNAUTHENTICATED(401, "Unauthenticated"),
    USER_NOT_FOUND(1000, "User not found"),
    USER_EXIST(1001, "User already exists"),
    INVALID_CREDENTIALS(1002, "Invalid credentials"),
    INVALID_TOKEN(2000, "Invalid token");

    private final int code;
    private final String message;
}
