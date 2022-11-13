package org.bogus.groove.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.bogus.groove.domain.user.Password;

public class PasswordDeserializer extends JsonDeserializer<Password> {
    @Override
    public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        String text = jsonNode.asText();
        return new Password(text);
    }
}
