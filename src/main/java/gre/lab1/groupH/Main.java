package gre.lab1.groupH;

import gre.lab1.graph.*;

import java.io.IOException;

/**
 * Displays the results of the tarjan algorithm and the contraction algorithm
 *
 * @author Ançay Rémi
 * @author Hutzli Boris
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Reads a directed graph from a file
        DirectedGraph graph = DirectedGraphReader.fromFile("data/chaine2.txt");

        // Applies the contraction algorithm
        GenericAlgorithm<GraphCondensation> contraction = new ContractionAlgorithm();
        GraphCondensation contractedGraph = contraction.compute(graph);

        // Displays the condensed graph
        System.out.println("Connexions du graphe réduit :");
        for (int i = 0; i < contractedGraph.condensation().getNVertices(); i++) {
            System.out.print("Sommet " + i + " : ");
            for (int j : contractedGraph.condensation().getSuccessorList(i)) {
                System.out.print(j + " ");
            }

            // If the component doesn't have any successors, then it's necessarily persistant
            if (contractedGraph.condensation().getSuccessorList(i).isEmpty()) {
                System.out.print("(Persistant)");
            } else {
                System.out.print("(Transitoire)");
            }
            System.out.println();
        }
        System.out.println();

        // Displays the vertex mapping found by the contraction algorithm
        System.out.println("Mapping des composantes fortement connexes :");
        for (int i = 0; i < contractedGraph.mapping().size(); i++) {
            System.out.print("Composante " + i + " : ");
            for (int j : contractedGraph.mapping().get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }


    }
}
