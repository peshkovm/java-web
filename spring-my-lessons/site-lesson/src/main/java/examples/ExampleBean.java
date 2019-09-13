package examples;

public class ExampleBean {
    // Number of years to calculate the Ultimate Answer
    public int years;

    // The Answer to Life, the Universe, and Everything
    public String ultimateAnswer;

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }

    public ExampleBean() {

    }

    public static ExampleBean createExampleBean(int years, String ultimateAnswer) {
        return new ExampleBean(years, ultimateAnswer);
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setUltimateAnswer(String ultimateAnswer) {
        this.ultimateAnswer = ultimateAnswer;
    }
}