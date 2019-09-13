import questionerRefined.beans.Question;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    public static void main(String[] args) throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./database-lesson/src/main/resources/database/database", "mylogin", "mypassword")) {
            List<Question> questions = Arrays.asList(
                    new Question(" 2 + 2", Arrays.asList("2", "5", "4"), 2),
                    new Question(" 3 + 3", Arrays.asList("6", "8", "9"), 0),
                    new Question(" 1 - 1", Arrays.asList("-1", "0", "1"), 1)
            );

            for (int i = 0; i < questions.size(); i++) {
                questions.get(i).setNum(i);
            }

            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Question (num, text, answers, right) VALUES (?, ?, ?, ?)"
            )) {

                for (Question question : questions) {

                    ps.setInt(1, question.getNum());
                    ps.setString(2, question.getText());
                    ps.setObject(3, question.getAnswers().toArray());
                    ps.setInt(4, question.getRight());
                    ps.addBatch();

                }

                ps.executeBatch();
            }
        }
    }
}