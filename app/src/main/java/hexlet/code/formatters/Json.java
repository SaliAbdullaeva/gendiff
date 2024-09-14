package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import java.util.List;
import java.util.Map;

public final class Json implements StringFormatter {

    @Override
    public String format(List<Map<String, Object>> differNodeList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(differNodeList);
    }
}
