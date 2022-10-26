package com.example.oblig2;
import com.example.oblig2.Model.*;
import com.example.oblig2.View.*;
import com.example.oblig2.Model.AVLTree;

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
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        
        
        AVLTree<Integer> avlTree = new AVLTree<>();
        BTView avlView = new BTView(avlTree);

        pane.setCenter(avlView);
        
        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Sett inn");
        Button btDelete = new Button("Slett");
        Button btSearch = new Button("Søk");
        //Legg til 10 tilfeldige verdier
        Button btRandom = new Button("Legg til 10 tilfeldige verdier");

        

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsert, btDelete, btSearch, btRandom);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (avlTree.search(key)) { // key is in the avlTree already
                avlView.displayTree();
                avlView.setStatus(key + " er allerede i treet");
            } else {
                avlTree.insert(key); // Insert a new key
                avlView.displayTree();
                avlView.setStatus(key + " er satt inn i treet");
            }
        });  
        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!avlTree.search(key)) { // key is not in the avlTree
                avlView.displayTree();
                avlView.setStatus(key + " er ikke i treet");
            } else {
                avlTree.delete(key); // Delete a key
                avlView.displayTree();
                avlView.setStatus(key + " er slettet fra treet");
            }
        });
        btSearch.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!avlTree.search(key)) { // key is not in the avlTree
                avlView.displayTree();
                avlView.setStatus(key + " er ikke i treet");
            } else {
                avlView.displayTree();
                avlView.setStatus(key + " er i treet");
            }
        });
        btRandom.setOnAction(e -> {
            for (int i = 0; i < 10; i++) {
                int key = (int)(Math.random() * 100);
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
     
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("AVL Tree"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }    
}
