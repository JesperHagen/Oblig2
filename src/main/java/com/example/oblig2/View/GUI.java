package com.example.oblig2.View;

import java.util.Arrays;

import com.example.oblig2.Model.BST;
import com.example.oblig2.View.BTView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        BST<Integer> tree = new BST<>();
        //tree.addAll(Arrays.asList(60, 55, 100, 45, 57, 67, 107, 59, 101));
        
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(5);
        tree.add(452);
        tree.add(6);
        tree.add(7);

        tree.postorder();

        tree.delete(1);

        BTView btViewTree = new BTView(tree);
        btViewTree.setMinHeight(500);
        btViewTree.setMinWidth(700);

        btViewTree.displayTree();

        borderPane.setCenter(btViewTree);        
        
        Scene scene = new Scene(borderPane, 800, 600);

        primaryStage.setTitle("Oblig 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
