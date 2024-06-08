// Mani Shankar Swaraj 

import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a question in the quiz
class Question {
    private String prompt;  // The question text
    private ArrayList<String> options;  // List of multiple-choice options
    private int correctOption;  // Index of the correct option

    // Constructor to initialize a question
    public Question(String prompt, ArrayList<String> options, int correctOption) {
        this.prompt = prompt;
        this.options = options;
        this.correctOption = correctOption;
    }

    // Getter for the question text
    public String getPrompt() {
        return prompt;
    }

    // Getter for the list of options
    public ArrayList<String> getOptions() {
        return options;
    }

    // Getter for the index of the correct option
    public int getCorrectOption() {
        return correctOption;
    }
}

// Class to represent a quiz
class Quiz {
    private String title;  // The title of the quiz
    private ArrayList<Question> questions;  // List of questions in the quiz

    // Constructor to initialize a quiz
    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    // Method to add a question to the quiz
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Getter for the list of questions
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    // Getter for the title of the quiz
    public String getTitle() {
        return title;
    }
}

public class QuizGenerator {
    private static Scanner scanner = new Scanner(System.in);  // Scanner for user input

    // Method to create a new quiz
    public static Quiz createQuiz() {
        System.out.print("Enter the title of the quiz: ");
        String title = scanner.nextLine();
        return new Quiz(title);
    }

    // Method to add a question to an existing quiz
    public static void addQuestionToQuiz(Quiz quiz) {
        System.out.print("Enter the question: ");
        String prompt = scanner.nextLine();
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options.add(scanner.nextLine());
        }
        System.out.print("Enter the number of the correct option: ");
        int correctOption = scanner.nextInt() - 1;  // Convert to zero-based index
        scanner.nextLine();  // Consume newline left-over
        Question question = new Question(prompt, options, correctOption);
        quiz.addQuestion(question);
    }

    // Method to take a quiz and display the score
    public static void takeQuiz(Quiz quiz) {
        int score = 0;
        ArrayList<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + question.getPrompt());
            ArrayList<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
            System.out.print("Your answer: ");
            int answer = scanner.nextInt() - 1;  // Convert to zero-based index
            if (answer == question.getCorrectOption()) {
                score++;
            }
        }
        int totalQuestions = questions.size();
        System.out.println("You scored " + score + "/" + totalQuestions + ".");
    }

    // Main method to provide the command-line interface
    public static void main(String[] args) {
        ArrayList<Quiz> quizzes = new ArrayList<>();  // List to store all quizzes
        while (true) {
            // Display menu options
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            switch (choice) {
                case 1:
                    // Create a new quiz
                    quizzes.add(createQuiz());
                    break;
                case 2:
                    // Add a question to an existing quiz
                    System.out.print("Enter the title of the quiz to add a question to: ");
                    String quizTitle = scanner.nextLine();
                    Quiz quizToAddQuestion = null;
                    for (Quiz quiz : quizzes) {
                        if (quiz.getTitle().equals(quizTitle)) {
                            quizToAddQuestion = quiz;
                            break;
                        }
                    }
                    if (quizToAddQuestion != null) {
                        addQuestionToQuiz(quizToAddQuestion);
                    } else {
                        System.out.println("Quiz not found.");
                    }
                    break;
                case 3:
                    // Take a quiz
                    System.out.print("Enter the title of the quiz to take: ");
                    String quizTitleToTake = scanner.nextLine();
                    Quiz quizToTake = null;
                    for (Quiz quiz : quizzes) {
                        if (quiz.getTitle().equals(quizTitleToTake)) {
                            quizToTake = quiz;
                            break;
                        }
                    }
                    if (quizToTake != null) {
                        takeQuiz(quizToTake);
                    } else {
                        System.out.println("Quiz not found.");
                    }
                    break;
                case 4:
                    // Exit the application
                    System.out.println("Exiting...");
                    return;
                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
