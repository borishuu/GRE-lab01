package gre.lab1.groupH;

import gre.lab1.graph.DirectedGraph;
import gre.lab1.graph.GenericAlgorithm;
import gre.lab1.graph.GraphCondensation;
import gre.lab1.graph.GraphScc;

public class ContractionAlgorithm implements GenericAlgorithm<GraphCondensation> {
  @Override
  public GraphCondensation compute(DirectedGraph graph) {

    //Transformation du graphe en graphe scc
    TarjanAlgorithm tarjan = new TarjanAlgorithm();
    GraphScc scc = tarjan.compute(graph);

    //Création du graphe de condensation
    DirectedGraph condensation = new DirectedGraph(scc.count());

    //Ajout des arcs dans le graphe de condensation
    for (int i = 0; i < graph.getNVertices(); i++) {
      for (int j : graph.getSuccessorList(i)) {
        if (scc.componentOf(i) != scc.componentOf(j)) {
          if (!condensation.getSuccessorList(scc.componentOf(i) - 1).contains(scc.componentOf(j) - 1))
            condensation.addEdge(scc.componentOf(i) - 1, scc.componentOf(j) - 1 );
        }
      }
    }

    //Création du mapping
    java.util.List<java.util.List<Integer>> mapping = new java.util.ArrayList<>();
    for (int i = 0; i < scc.count(); i++) {
      mapping.add(new java.util.ArrayList<>());
    }

    for (int i = 0; i < graph.getNVertices(); i++) {
      mapping.get(scc.componentOf(i) - 1).add(i);
    }


    return new GraphCondensation(graph, condensation, mapping);
  }
}
