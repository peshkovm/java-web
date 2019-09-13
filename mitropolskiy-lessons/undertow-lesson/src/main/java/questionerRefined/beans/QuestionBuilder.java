package questionerRefined.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionBuilder {
    private static final List<Question> questions = new ArrayList<>();

    static {
        questions.add(new Question("2+2"
                , Arrays.asList("1", "2", "4"), 4));
        questions.add(new Question("1+3"
                , Arrays.asList("5", "4", "7"), 4));
        questions.add(new Question("5+5"
                , Arrays.asList("10", "5", "8"), 10));
        questions.add(new Question("4*5"
                , Arrays.asList("5", "15", "20"), 20));
    }

    private QuestionBuilder() {
    }

    public static List<Question> getQuestions() {
        return questions;
    }
}