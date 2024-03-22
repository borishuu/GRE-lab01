package gre.lab1.groupH;

import gre.lab1.graph.DirectedGraph;
import gre.lab1.graph.GraphScc;
import gre.lab1.graph.SccAlgorithm;

import java.util.Stack;

public final class TarjanAlgorithm implements SccAlgorithm {
  @Override
  public GraphScc compute(DirectedGraph graph) {
    int[] dfsNum = new int[graph.getNVertices()];
    int[] tabScc = new int[graph.getNVertices()];
    int[] low = new int[graph.getNVertices()];
    int[] compteurDfsNum = {0}; // Déclarer comme tableau pour passer par référence
    int[] compteurComposantes = {0};
    Stack<Integer> pileSommets = new Stack<>();

    for (int sommet = 0; sommet < graph.getNVertices(); sommet++) {
      if (tabScc[sommet] == 0) {
        scc(sommet, graph, dfsNum, tabScc, low, compteurDfsNum, compteurComposantes, pileSommets);
      }
    }

    return new GraphScc(graph, compteurComposantes[0], tabScc);
  }

  private void scc(Integer sommet, DirectedGraph graph, int[] dfsNum, int[] tabScc, int[] low, int[] compteurDfsNum, int[] compteurComposantes, Stack<Integer> pileSommets) {


    compteurDfsNum[0]++; //TODO PROBLEME DE REFERENCE
    dfsNum[sommet] = compteurDfsNum[0];
    low[sommet] = compteurDfsNum[0];
    pileSommets.push(sommet);

    for (int sommetSuccesseur : graph.getSuccessorList(sommet)) {
        if (dfsNum[sommetSuccesseur] == 0) {
            scc(sommetSuccesseur, graph, dfsNum, tabScc, low, compteurDfsNum, compteurComposantes, pileSommets);
        } else if (tabScc[sommetSuccesseur] == 0) {
            low[sommet] = Math.min(low[sommet], low[sommetSuccesseur]);
        }
    }

    if (low[sommet] == dfsNum[sommet]) {
        compteurComposantes[0]++;
        int sommetDepile;
        do {
            sommetDepile = pileSommets.pop();
            tabScc[sommetDepile] = compteurComposantes[0];
        } while (sommetDepile != sommet);
    }

  }
}
