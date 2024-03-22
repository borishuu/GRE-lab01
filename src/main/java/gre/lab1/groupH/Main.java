package gre.lab1.groupH;

import gre.lab1.graph.*;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    // Lecture d'un graphe orienté depuis un fichier
     DirectedGraph graph = DirectedGraphReader.fromFile("data/chaine1.txt");

    // Calcul des composantes fortement connexes
    SccAlgorithm tarjan = new TarjanAlgorithm();
    GraphScc scc = tarjan.compute(graph);

    // Affichage des composantes fortement connexes
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

    // Calcul du graphe de condensation
    GenericAlgorithm<GraphCondensation> contraction = new ContractionAlgorithm();
    GraphCondensation condensation = contraction.compute(graph);

    // Affichage du graphe de condensation
    System.out.println("Graphe de condensation :");
    for (int i = 0; i < condensation.condensation().getNVertices(); i++) {
      System.out.print("Sommet " + i + " : ");
      for (int j : condensation.condensation().getSuccessorList(i)) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
  System.out.println();

    // Affichage du mapping
    System.out.println("Mapping :");
    for (int i = 0; i < condensation.mapping().size(); i++) {
      System.out.print("Composante " + i + " : ");
      for (int j : condensation.mapping().get(i)) {
        System.out.print(j + " ");
      }
      System.out.println();
    }




    // TODO
    //  - Renommage du package ;
    //  - Écrire le code dans les fichiers Main.java, TarjanAlgorithm.java, ContractionAlgorithm.java
    //    et UNIQUEMENT ceux-ci ;
    //  - Documentation soignée comprenant :
    //    - la javadoc, avec auteurs et description des implémentations ;
    //    - des commentaires sur les différentes parties de vos algorithmes.
  }
}
