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
        DirectedGraph graph = DirectedGraphReader.fromFile("data/chaine1.txt");

        // Applies the tarjan algorithm
        SccAlgorithm tarjan = new TarjanAlgorithm();
        GraphScc scc = tarjan.compute(graph);

        // Displays the strongly connected components found by the tarjan algorithm
        System.out.println("Composantes fortement connexes :");
        for (int i = 0; i < scc.count(); i++) {
            System.out.print("Composante " + (i + 1) + " : ");
            for (int j = 0; j < scc.components().length; j++) {
                if (scc.components()[j] == i + 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        // Applies the contraction algorithm
        GenericAlgorithm<GraphCondensation> contraction = new ContractionAlgorithm();
        GraphCondensation contractedGraph = contraction.compute(graph);

        // Displays the condensed graph
        System.out.println("Graphe réduit :");
        for (int i = 0; i < contractedGraph.condensation().getNVertices(); i++) {
            System.out.print("Sommet " + i + " : ");
            for (int j : contractedGraph.condensation().getSuccessorList(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Displays the vertex mapping found by the contraction algorithm
        System.out.println("Mapping :");
        for (int i = 0; i < contractedGraph.mapping().size(); i++) {
            System.out.print("Composante " + i + " : ");
            for (int j : contractedGraph.mapping().get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }


    }
}
