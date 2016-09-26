import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static String word = "bicycle";
    static Scanner scanner = new Scanner(System.in);
    static char[] wordArray = {'_', '_', '_', '_', '_', '_', '_'};
    static boolean isNotFinished = true;

//    static int wordLength = word.length();
//
//    static char[] wordArray1;
//    public static void makeWordArray() {
//        for (int i = 0; i < wordLength; i++){
//            wordArray[i] = '_';
//            i++;
//        }
//    }

    public static void main(String[] args) {
//        printBoard();
//        while (isNotFinished) {
//            userGuess();
//        }
//        System.exit(0);

        Spark.init();
        Spark.get("/",
                ((request, response) -> {
                    HashMap m = new HashMap();
//                   printBoard();
                    while(true){
                            userGuess();
                        return new ModelAndView(m, "hangman.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

    }

    public static void printBoard() {
        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word. You have 10 tries.");
        System.out.println(wordArray);
    }

    public static void userGuess() {
        int triesRemaining = 10;


        while (isNotFinished) {
            System.out.println("Please enter a letter.");
            String guess = scanner.nextLine();
            if (word.contains(guess)) {
                System.out.println("Correct");
                switchOnLetters(guess);
            } else {
                System.out.println("Wrong");
                triesRemaining--;
                System.out.printf("You have %s tries left.", triesRemaining);
            }
            showGameBoard(triesRemaining);

            isNotFinished = isNotFinished(triesRemaining);

        }
    }

    public static void showGameBoard(int triesRemaining){
        String gameboard = "-----------\n" +
                " |        |\n" +
                " ()       |\n" +
                "\\||/      |\n" +
                " ||       |\n" +
                "/  \\      |\n" +
                "          |\n" +
                "------------------------";
        switch (triesRemaining){
            case 10:
                gameboard = "-----------\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 9:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 8:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 7:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\         |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 6:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\|        |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 5:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\||       |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 4:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\||/      |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 3:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\||/      |\n" +
                        " |        |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 2:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\||/      |\n" +
                        " ||       |\n" +
                        "          |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 1:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\||/      |\n" +
                        " ||       |\n" +
                        "/         |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            case 0:
                gameboard = "-----------\n" +
                        " |        |\n" +
                        " ()       |\n" +
                        "\\||/      |\n" +
                        " ||       |\n" +
                        "/  \\      |\n" +
                        "          |\n" +
                        "------------------------";
                System.out.println(gameboard);
                break;
            default:
                System.out.println("Error");
                break;
        }

    }

    public static void switchOnLetters(String guess) {
        switch (guess) {
            case "b":
                wordArray[0] = 'b';
                break;
            case "i":
                wordArray[1] = 'i';
                break;
            case "c":
                wordArray[2] = 'c';
                wordArray[4] = 'c';
                break;
            case "y":
                wordArray[3] = 'y';
                break;
            case "l":
                wordArray[5] = 'l';
                break;
            case "e":
                wordArray[6] = 'e';
                break;
            default:
                System.out.println("error");
        }
//        String updatedArray = wordArray;
        System.out.println(wordArray);
    }

    public static boolean isNotFinished(int triesRemaining) {
        if(triesRemaining > 0) {
            if (new String(wordArray).indexOf('_') >= 0) {
                return true;
            } else {
                System.out.println("Good job! You figured it out!");
                return false;
            }
        } else {
            System.out.println("You lose. The word was 'bicycle'.");
            return false;
        }
    }


}
