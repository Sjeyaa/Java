import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class File_Choose_Con extends Application {
    TextArea textArea;
    Button openButton, createButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        openButton = new Button("Open File");
        createButton = new Button("Create Concordance");

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(primaryStage);
                displayFile(file);
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showSaveDialog(primaryStage);
                createConcordance(file);
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(textArea, openButton, createButton);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Create Concordance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void displayFile(File file) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            textArea.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
                                }
        }

    public void createConcordance(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            HashMap<String, ArrayList<Integer>> concordance = new HashMap<>();

            String[] lines = textArea.getText().split("\n");
            int lineNumber = 1;

            for (String line : lines) {
                String[] words = line.split(" ");
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                    if (word.length() < 3 || word.equals("the")) {
                        continue;
                    }
                    if (!concordance.containsKey(word)) {
                        concordance.put(word, new ArrayList<>());
                    }
                    concordance.get(word).add(lineNumber);
                }
                lineNumber++;
            }


            for (String word : concordance.keySet()) {
                fileWriter.write(word + ": ");
                for (int line : concordance.get(word)) {
                    fileWriter.write(line + " ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
