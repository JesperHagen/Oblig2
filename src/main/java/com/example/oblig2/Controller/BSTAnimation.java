package com.example.oblig2.Controller;

import java.util.Random;

import com.example.oblig2.Model.*;
import com.example.oblig2.View.*;
import com.example.oblig2.Model.AVLTree;
import com.example.oblig2.Model.BST;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BSTAnimation extends Application {

    int inputType = 2;

    /**
     * Tegner GUI, med knapper, tekstfelt og view
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        AVLTree<Object> avlTree = new AVLTree<Object>();
        BTView avlView = new BTView(avlTree);

        pane.setCenter(avlView);

        TextField tfKey = new TextField();

        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Sett inn");
        Button btDelete = new Button("Slett");
        Button btSearch = new Button("Søk");
        // Legg til 10 tilfeldige verdier
        Button btRandom = new Button("Legg til 10 tilfeldige verdier");
        // Knapp for å finne laveste verdiene i treet
        Button btLowest = new Button("Finn laveste verdi");
        // Slett treet
        Button btClear = new Button("Slett treet");

        // change between integer and string
        Button btChangeInputType = new Button("Velg input type");

        HBox hBox = new HBox(5);
        HBox topHbox = new HBox(5);

        topHbox.getChildren().addAll(btChangeInputType);
        hBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsert, btDelete, btSearch, btRandom, btLowest,
                btClear);

        hBox.setAlignment(Pos.CENTER);
        topHbox.setAlignment(Pos.CENTER);

        pane.setBottom(hBox);
        pane.setTop(topHbox);

        btChangeInputType.setOnAction(e -> {
            if (inputType == 0) {
                inputType = 1;
                btChangeInputType.setText("Bytt input type (String)");
                btClear.fire();
                System.out.print("\nInput type changed to String \n" + inputType);
            } else {
                inputType = 0;
                btChangeInputType.setText("Bytt input type (Integer)");
                btClear.fire();
                System.out.println("\nInput type changed to Integer \n" + inputType);
            }
        });

        btInsert.setOnAction(e -> {
            if (inputType == 0) {
                int key = Integer.parseInt(tfKey.getText());
                if (avlTree.search(key)) { // key is in the avlTree already
                    avlView.displayTree();
                    avlView.setStatus(key + " er allerede i treet");
                } else {
                    avlTree.insert(key); // Insert a new key
                    avlView.displayTree();
                    avlView.setStatus(key + " er satt inn i treet");
                }
            }
            if (inputType == 1) {
                String key = tfKey.getText();
                if (avlTree.search(key)) { // key is in the avlTree already
                    avlView.displayTree();
                    avlView.setStatus(key + " er allerede i treet");
                } else {
                    avlTree.insert(key); // Insert a new key
                    avlView.displayTree();
                    avlView.setStatus(key + " er satt inn i treet");
                }
            }
        });
        btDelete.setOnAction(e -> {
            if (tfKey.getText().matches("[0-9]+") && inputType == 0) {
                int key = Integer.parseInt(tfKey.getText());
                if (!avlTree.search(key)) { // key is not in the avlTree
                    avlView.displayTree();
                    avlView.setStatus(key + " er ikke i treet");
                } else {
                    avlTree.delete(key); // Delete a key
                    avlView.displayTree();
                    avlView.setStatus(key + " er slettet fra treet");
                }
            } else {
                String key = tfKey.getText();
                if (!avlTree.search(key)) { // key is not in the avlTree
                    avlView.displayTree();
                    avlView.setStatus(key + " er ikke i treet");
                } else {
                    avlTree.delete(key); // Delete a key
                    avlView.displayTree();
                    avlView.setStatus(key + " er slettet fra treet");
                }
            }
        });
        btSearch.setOnAction(e -> {
            if (tfKey.getText().matches("[0-9]+") && inputType == 0) {
                int key = Integer.parseInt(tfKey.getText());
                if (!avlTree.search(key)) { // key is not in the avlTree
                    avlView.displayTree();
                    avlView.setStatus(key + " er ikke i treet");
                } else {
                    avlView.displayTree();
                    avlView.setStatus(key + " er i treet");
                }
            } else {
                String key = tfKey.getText();
                if (!avlTree.search(key)) { // key is not in the avlTree
                    avlView.displayTree();
                    avlView.setStatus(key + " er ikke i treet");
                } else {
                    avlView.displayTree();
                    avlView.setStatus(key + " er i treet");
                }
            }
        });
        btRandom.setOnAction(e -> {
            // Legg til 10 tilfeldige verdier (Integer)
            if (inputType == 0) {
                for (int i = 0; i < 10; i++) {
                    int key = (int) (Math.random() * 100);
                    if (avlTree.search(key)) { // key is in the avlTree already
                        avlView.displayTree();
                        avlView.setStatus(key + " er allerede i treet");
                    } else {
                        avlTree.insert(key); // Insert a new key
                        avlView.displayTree();
                        avlView.setStatus(key + " er satt inn i treet");
                    }
                }
            } else {
                // Legg til 10 tilfeldige verdier (String)
                for (int i = 0; i < 10; i++) {
                    // generate random char string
                    char[] chars = "abcdefghijklmnopqrstuvwxyzæøå".toCharArray();
                    StringBuilder sb = new StringBuilder();
                    Random random = new Random();
                    for (int j = 0; j < 1; j++) {
                        char c = chars[random.nextInt(chars.length)];
                        sb.append(c);
                    }
                    String output = sb.toString();
                    if (avlTree.search(output)) { // key is in the avlTree already
                        avlView.displayTree();
                        avlView.setStatus(output + " er allerede i treet");
                    } else {
                        avlTree.insert(output); // Insert a new key
                        avlView.displayTree();
                        avlView.setStatus(output + " er satt inn i treet");
                    }
                }
            }
        });

        btClear.setOnAction(e -> {
            avlTree.clear();
            avlView.displayTree();
            avlView.setStatus("Treet er slettet");
        });

        btLowest.setOnAction(e -> {

        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("AVL Tree"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }
}
