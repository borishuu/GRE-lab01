package gre.lab1.groupX;

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
    int[] n = {0}; // Déclarer comme tableau pour passer par référence
    int[] k = {0};
    Stack<Integer> p = new Stack<>();

    for (int u = 0; u < graph.getNVertices(); u++) {
      if (tabScc[u] == 0) {
        scc(u, graph, dfsNum, tabScc, low, n, k, p);
      }
    }


    return null;
  }

  private void scc(Integer u, DirectedGraph graph, int[] dfsNum, int[] tabScc, int[] low, int[] n, int[] k, Stack<Integer> p) {


    n[0]++; //TODO PROBLEME DE REFERENCE
    dfsNum[u] = n[0];
    low[u] = n[0];
    p.push(u);

    for (int v : graph.getSuccessorList(u)) {
        if (dfsNum[v] == 0) {
            scc(v, graph, dfsNum, tabScc, low, n, k, p);
        } else if (tabScc[v] == 0) {
            low[u] = Math.min(low[u], low[v]);
        }
    }

    if (low[u] == dfsNum[u]) {
        k[0]++;
        int w;
        do {
            w = p.pop();
            tabScc[w] = k[0];
        } while (w != u);
    }

  }
}
