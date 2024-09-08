package az.ingress.exception;

import lombok.Getter;

@Getter
public class CustomFeignException extends RuntimeException{
    private final int status;
    private final String code;

    public CustomFeignException(String message, String code, int status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
