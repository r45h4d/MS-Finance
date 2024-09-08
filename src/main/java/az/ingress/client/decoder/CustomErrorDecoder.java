package az.ingress.client.decoder;

import az.ingress.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static az.ingress.client.decoder.JsonNodeFileName.CODE;
import static az.ingress.client.decoder.JsonNodeFileName.MESSAGE;
import static az.ingress.exception.ExceptionConstants.CLIENT_EXCEPTION_CODE;
import static az.ingress.exception.ExceptionConstants.CLIENT_EXCEPTION_MESSAGE;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        var errorMessage = CLIENT_EXCEPTION_MESSAGE;
        var errorCode = CLIENT_EXCEPTION_CODE;

        // Feign String-in (InputStream-in) içərisindən çıxarmaq istədiyimiz JsonNode
        JsonNode jsonNode;
        try(var body = response.body().asInputStream()){
            // body-ni JsonNode sinfinə çeviririk
            jsonNode=new ObjectMapper().readValue(body, JsonNode.class);
        }
        catch (Exception e){
            // Digər tərəfdə server çökməsi və s. halında
            throw new CustomFeignException(CLIENT_EXCEPTION_MESSAGE, CLIENT_EXCEPTION_CODE, response.status());
        }

        // JsonNode-un içərisində həmin teqin olub-olmamasını yoxlayırıq
        if(jsonNode.has(MESSAGE.getValue()) && jsonNode.has(CODE.getValue())){
            errorMessage=jsonNode.get(MESSAGE.getValue()).asText();
            errorCode=jsonNode.get(CODE.getValue()).asText();
        }

        log.error("ActionLog.decode.error Message: {}, Code: {}, Method: {}", errorMessage, errorCode, methodKey);
        return new CustomFeignException(errorMessage, errorCode, response.status());
    }
}
