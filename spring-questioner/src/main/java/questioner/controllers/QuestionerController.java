package questioner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import questioner.beans.Question;
import questioner.beans.QuestionBuilder;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "dispatcher",
        urlPatterns = {"/"},
        displayName = "spring-mvc-demo",
        initParams = {@WebInitParam(name = "contextConfigLocation", value = "/WEB-INF/spring-mvc-demo-servlet.xml")},
        loadOnStartup = 1)

@Controller
public class QuestionerController extends DispatcherServlet {
    private static final List<Question> QUESTIONS = QuestionBuilder.getQuestions();

    @RequestMapping("/questioner")
    public String showQuestion(HttpServletRequest request, Model model) {

        Question question;
        if (request.getParameter("numOfQ") == null)
            question = QUESTIONS.get(0);
        else if (Integer.parseInt(request.getParameter("numOfQ")) == QUESTIONS.size() - 1) {
            model.addAttribute("numCorrect", 4);
            return "results";
        } else
            question = QUESTIONS.get(Integer.parseInt(request.getParameter("numOfQ")) + 1);

/*        Map<String, Object> questionMap = new HashMap<>();
        questionMap.put("answers", question.getAnswers());
        questionMap.put("num", question.getNum() + 1);
        questionMap.put("right", question.getRight());
        questionMap.put("text", question.getText());

        model.addAllAttributes(questionMap);*/

        model.addAttribute("question", question);

        return "questionRefined";
    }

}