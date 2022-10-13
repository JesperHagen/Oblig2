package com.example.oblig2.View;

import com.example.oblig2.Model.BST;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; // tre node radius
    private double vGap = 50; // vertikal avstand mellom noder

    BTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tomt tre");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Tømmer pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }

    private void displayTree(BST.TreeNode<Integer> root, double x, double y,
            double hGap) {
        if (root.left != null) {
            // tegn en linje til venstre barn
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // tegn venstre undertre
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            // tegn en linje til høyre barn
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // tegn høyre undertre
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        // tegn en node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.element + ""));
    }
    
}
