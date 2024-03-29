package com.example.oblig2.View;

import com.example.oblig2.Model.BST;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView<E> extends Pane {
    private BST<E> tree = new BST<>();

    private double radius = 15; // tre node radius
    private double vGap = 60; // vertikal avstand mellom noder

    public BTView(BST<E> tree) {
        this.tree = tree;
        setStatus("Tomt tre");
    }

    /**
     * Setter statusen til treet
     * @param msg
     */
    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    /**
     * Viser treet med en rekursiv metode
     */
    public void displayTree() {
        this.getChildren().clear(); // Tømmer pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }

    /**
     * Viser et subtre, gitt rot, x-koordinat, y-koordinat og horisontal avstand
     * Tegner en sirkle rundt hver node
     */

    private void displayTree(BST.TreeNode<E> root, double x, double y,
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
