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
    Integer n = 0;
    Integer k = 0;
    Stack<Integer> p = new Stack<>();

    for (int u = 0; u < graph.getNVertices(); u++) {
      if (tabScc[u] == 0) {
        scc(u, graph, dfsNum, tabScc, low, n, k, p);
      }
    }


    return null;
  }

  private void scc(Integer u, DirectedGraph graph, int[] dfsNum, int[] tabScc, int[] low, Integer n, Integer k, Stack<Integer> p) {


    n++; //TODO PROBLEME DE REFERENCE
    dfsNum[u] = n;
    low[u] = n;
    p.push(u);

    for (int v : graph.getSuccessorList(u)) {
        if (dfsNum[v] == 0) {
            scc(v, graph, dfsNum, tabScc, low, n, k, p);
        } else if (tabScc[v] == 0) {
            low[u] = Math.min(low[u], low[v]);
        }
    }

    if (low[u] == dfsNum[u]) {
        k++;
        int w;
        do {
            w = p.pop();
            tabScc[w] = k;
        } while (w != u);
    }

  }
}
