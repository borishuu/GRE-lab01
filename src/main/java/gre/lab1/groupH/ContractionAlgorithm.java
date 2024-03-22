package gre.lab1.groupH;

import gre.lab1.graph.DirectedGraph;
import gre.lab1.graph.GenericAlgorithm;
import gre.lab1.graph.GraphCondensation;
import gre.lab1.graph.GraphScc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContractionAlgorithm implements GenericAlgorithm<GraphCondensation> {

  private List<List<Integer>> initializeVerticiesMapping(int count) {
    List<List<Integer>> verticiesMapping = new ArrayList<>();
    for (int i = 0; i < count; ++i) {
      verticiesMapping.add(new ArrayList<>());
    }
    return verticiesMapping;
  }

  @Override
  public GraphCondensation compute(DirectedGraph graph) {
    TarjanAlgorithm tarjan = new TarjanAlgorithm();
    GraphScc tarjanResult = tarjan.compute(graph);

    List<List<Integer>> verticiesMapping = initializeVerticiesMapping(tarjanResult.count());
    DirectedGraph condesation = new DirectedGraph(graph.getNVertices());
    Set<Integer> indexesFortementConnexe = new HashSet<>();

    for (int sommet = 0; sommet < tarjanResult.components().length; ++sommet) {
      int composanteFortmentConnexe = tarjanResult.components()[sommet];
      if (indexesFortementConnexe.contains(composanteFortmentConnexe)) {
        verticiesMapping.get(composanteFortmentConnexe).add(sommet);
      }
    }

    GraphCondensation resultingGraph = new GraphCondensation(graph, condesation, verticiesMapping);
    return null;
  }
}
