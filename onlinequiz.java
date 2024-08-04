import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class representing a generic question
abstract class Question {
    protected String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public abstract boolean checkAnswer(String answer);

    public abstract void displayQuestion();
}

// Class representing a multiple-choice question
class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private int correctOption;

    public MultipleChoiceQuestion(String questionText, List<String> options, int correctOption) {
        super(questionText);
        this.options = options;
        this.correctOption = correctOption;
    }

    @Override
    public boolean checkAnswer(String answer) {
        int answerOption = Integer.parseInt(answer);
        return answerOption == correctOption;
    }

    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}

// Class representing a true/false question
class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        boolean userAnswer = Boolean.parseBoolean(answer);
        return userAnswer == correctAnswer;
    }

    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        System.out.println("1. True");
        System.out.println("2. False");
    }
}

// Class representing a short-answer question
class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String questionText, String correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }

    @Override
    public void displayQuestion() {
        System.out.println(questionText);
    }
}

// Class representing a quiz
class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            if (question.checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
            System.out.println();
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }
}

// Main class to run the quiz platform
public class QuizPlatform {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Sample multiple-choice question
        List<String> options = new ArrayList<>();
        options.add("Java");
        options.add("Python");
        options.add("C++");
        options.add("JavaScript");
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion("Which programming language is known as the 'Write Once, Run Anywhere' language?", options, 1);
        quiz.addQuestion(mcq);

        // Sample true/false question
        TrueFalseQuestion tfq = new TrueFalseQuestion("The earth is flat.", false);
        quiz.addQuestion(tfq);

        // Sample short-answer question
        ShortAnswerQuestion saq = new ShortAnswerQuestion("What is the capital of France?", "Paris");
        quiz.addQuestion(saq);

        // Start the quiz
        quiz.startQuiz();
    }
}
