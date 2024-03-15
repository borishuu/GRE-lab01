package gre.lab1.groupX;

import gre.lab1.graph.*;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    // Lecture d'un graphe orienté depuis un fichier
     DirectedGraph graph = DirectedGraphReader.fromFile("data/chaine1.txt");

    TarjanAlgorithm tarjan = new TarjanAlgorithm();
    GraphScc scc = tarjan.compute(graph);

    // TODO
    //  - Renommage du package ;
    //  - Écrire le code dans les fichiers Main.java, TarjanAlgorithm.java, ContractionAlgorithm.java
    //    et UNIQUEMENT ceux-ci ;
    //  - Documentation soignée comprenant :
    //    - la javadoc, avec auteurs et description des implémentations ;
    //    - des commentaires sur les différentes parties de vos algorithmes.
  }
}
