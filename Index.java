package mini_fx_2;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class File_Choose extends Application {
    TextField inputField;
    TextField outputField;
    Button inputButton;
    Button outputButton;
    Button createButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        inputField = new TextField();
        outputField = new TextField();
        inputButton = new Button("Select Input File");
        outputButton = new Button("Select Output File");
        createButton = new Button("Create Index");

        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File inputFile = fileChooser.showOpenDialog(primaryStage);
                inputField.setText(inputFile.getAbsolutePath());
            }
        });

        outputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File outputFile = fileChooser.showSaveDialog(primaryStage);
                outputField.setText(outputFile.getAbsolutePath());
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String inputFile = inputField.getText();
                String outputFile = outputField.getText();
                createIndex(inputFile, outputFile);
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(inputField, inputButton, outputField, outputButton, createButton);
Scene scene = new Scene(root, 500, 200);
primaryStage.setTitle("Create Index");
primaryStage.setScene(scene);
primaryStage.show();
}public void createIndex(String inputFile, String outputFile) {
    // Initialize an empty HashMap to store the index
    HashMap<String, ArrayList<Integer>> index = new HashMap<>();

    try {
        // Open the input file
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Initialize a variable to keep track of the current page number
        int pageNumber = 1;

        // Read the input file line by line
        String line = bufferedReader.readLine();
        while (line != null) {
            // Split the line into words
            String[] words = line.split(" ");

            // Iterate through each word in the line
            for (String word : words) {
                // Remove any punctuation from the word
                word = word.replaceAll("[^a-zA-Z]", "");

                // Convert the word to lowercase
                word = word.toLowerCase();

                // If the word is not already in the index, add it
                if (!index.containsKey(word)) {
                    index.put(word, new ArrayList<Integer>());
                }

                // Add the page number to the index for the word
                index.get(word).add(pageNumber);
            }

            // Check if the current line is a page break
            if (line.equals("")) {
                pageNumber++;
            }

            // Read the next line
            line = bufferedReader.readLine();
        }

        // Close the input file
        bufferedReader.close();

        // Open the output file
        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Write the index to the output file
        for (String word : index.keySet()) {
            ArrayList<Integer> pageNumbers = index.get(word);
            String pageNumberString = pageNumbers.toString().replace("[", "").replace("]", "").replace(",", "");
            bufferedWriter.write(word + ": " + pageNumberString + "\n");
        }

        // Close the output file
        bufferedWriter.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
