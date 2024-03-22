package gre.lab1.groupH;

import gre.lab1.graph.DirectedGraph;
import gre.lab1.graph.GenericAlgorithm;
import gre.lab1.graph.GraphCondensation;
import gre.lab1.graph.GraphScc;

import java.util.ArrayList;
import java.util.List;

/**
 * Object allowing to contract a directed graph to a graph where each vertex represents a strongly connected component
 *
 * @author Ançay Rémi
 * @author Hutzli Boris
 */
public class ContractionAlgorithm implements GenericAlgorithm<GraphCondensation> {
    @Override
    public GraphCondensation compute(DirectedGraph graph) {

        // Applies the tarjan algorithm on the graph
        TarjanAlgorithm tarjan = new TarjanAlgorithm();
        GraphScc scc = tarjan.compute(graph);

        // Instantiates the condensed graph
        DirectedGraph contractedGraph = new DirectedGraph(scc.count());

        // Adds the edges to the condensed graph
        for (int i = 0; i < graph.getNVertices(); i++) {
            for (int j : graph.getSuccessorList(i)) {
                if (scc.componentOf(i) != scc.componentOf(j)) {
                    if (!contractedGraph.getSuccessorList(scc.componentOf(i) - 1).contains(scc.componentOf(j) - 1))
                        contractedGraph.addEdge(scc.componentOf(i) - 1, scc.componentOf(j) - 1);
                }
            }
        }

        // Creates the mapping of the strongly connected components to their contained vertices
        List<List<Integer>> mapping = new ArrayList<>();
        for (int i = 0; i < scc.count(); i++) {
            mapping.add(new ArrayList<>());
        }
        for (int i = 0; i < graph.getNVertices(); i++) {
            mapping.get(scc.componentOf(i) - 1).add(i);
        }

        return new GraphCondensation(graph, contractedGraph, mapping);
    }
}
