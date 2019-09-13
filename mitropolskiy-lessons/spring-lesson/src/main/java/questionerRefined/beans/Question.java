package questionerRefined.beans;

import java.util.List;

public class Question {
    private String text;
    private List<String> answers;
    private int right;
    private static int globalNum = 0;
    private int num;

    public Question(String text, List<String> answers, int right) {
        this.text = text;
        this.answers = answers;
        this.right = right;
        this.num = globalNum;
        globalNum++;
    }

    public Question(int num, String text, List<String> answers, int right) {
        this.num = num;
        this.text = text;
        this.answers = answers;
        this.right = right;
        this.num = globalNum;
        globalNum++;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getRight() {
        return right;
    }

    public String getText() {
        return text;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}