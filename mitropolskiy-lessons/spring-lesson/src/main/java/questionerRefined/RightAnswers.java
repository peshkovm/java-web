package questionerRefined;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RightAnswers {
    private final Map<String, List<Boolean>> rightAnswers = new HashMap<>();

    @Bean
    public Map<String, List<Boolean>> getRightAnswers() {
        return rightAnswers;
    }
}
