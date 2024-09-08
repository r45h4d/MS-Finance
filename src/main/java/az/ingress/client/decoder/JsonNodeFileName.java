package az.ingress.client.decoder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JsonNodeFileName {
    MESSAGE("message"),
    CODE("code");

    private final String value;
}
