import questionerRefined.beans.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionsDAO {
    public int getQuestionsCount() throws SQLException {

        try (Connection connection = getConnection()) {

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select count(*) from Question")
            ) {
                resultSet.next();
                return resultSet.getInt(1);

            }
        }
    }

    public Question getQuestion(int num) throws SQLException {

        try (Connection connection = getConnection()) {

            try (PreparedStatement statement =
                         connection.prepareStatement("Select ID, num, text, answers, right from Question WHERE NUM = ?");

            ) {
                statement.setInt(1, num);

                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    return new Question(
                            resultSet.getInt("num"),
                            resultSet.getString("text"),
                            Arrays.stream(((Object[]) resultSet.getObject("answers"))).map(s -> s.toString()).collect(Collectors.toList()),
                            resultSet.getInt("right")
                    );

                }

            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/sampledb", "mylogin", "mypassword");
    }
}