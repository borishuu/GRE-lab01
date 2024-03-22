package gre.lab1.groupH;

import gre.lab1.graph.DirectedGraph;
import gre.lab1.graph.GraphScc;
import gre.lab1.graph.SccAlgorithm;

import java.util.Stack;

/**
 * Object allowing to apply the tarjan algorithm on a given directed graph
 *
 * @author Ançay Rémi
 * @author Hutzli Boris
 */
public final class TarjanAlgorithm implements SccAlgorithm {

    /**
     * Contains all the data used by the tarjan/scc algorithm (used to pass values by reference)
     */
    private static class DataContainer {
        /**
         * Graph on which to apply the tarjan algorithm
         */
        DirectedGraph graph;

        /**
         * Contains dfsnum value for each vertex (vertex being the index)
         */
        public int[] dfsNum;

        /**
         * Contains the strongly connected component number for each vertex (vertex being the index)
         */
        public int[] tabScc;

        /**
         * Keeps track of the smallest vertex index reachable from the currently iterated index
         */
        public int[] low;

        /**
         * Stores the iterated vertices not yet added in a strongly connected component
         */
        Stack<Integer> verteciesStack;

        /**
         * Counts the amount of iterated vertices
         */
        public int dfsNumCounter;

        /**
         * Counts the amount of strongly connected components
         */
        public int componentCounter;

        /**
         * Constructor
         *
         * @param graph Graph on which to apply the tarjan algorithm
         */
        public DataContainer(DirectedGraph graph) {
            this.graph = graph;
            dfsNum = new int[graph.getNVertices()];
            tabScc = new int[graph.getNVertices()];
            low = new int[graph.getNVertices()];
            verteciesStack = new Stack<>();
            dfsNumCounter = 0;
            componentCounter = 0;
        }
    }

    @Override
    public GraphScc compute(DirectedGraph graph) {
        DataContainer dataContainer = new DataContainer(graph); // Contains all the data used by the algorithm

        // Applies the scc algorithm for each vertex not added to a strongly connected component
        for (int vertex = 0; vertex < graph.getNVertices(); vertex++) {
            if (dataContainer.tabScc[vertex] == 0) {
                scc(dataContainer, vertex);
            }
        }

        return new GraphScc(graph, dataContainer.componentCounter, dataContainer.tabScc);
    }

    /**
     * Gets the strongly connected component of a given vertex
     *
     * @param dataContainer Container containing the necessary data
     * @param vertex        Vertex from which to find the strongly connected component
     */
    private void scc(DataContainer dataContainer, int vertex) {

        // Updates the data regarding the given vertex
        dataContainer.dfsNumCounter++;
        dataContainer.dfsNum[vertex] = dataContainer.dfsNumCounter;
        dataContainer.low[vertex] = dataContainer.dfsNumCounter;
        dataContainer.verteciesStack.push(vertex);

        // Apply the scc algorithm recursively for each successor if it hasn't been iterated through yet
        for (int vertexSuccessor : dataContainer.graph.getSuccessorList(vertex)) {
            if (dataContainer.dfsNum[vertexSuccessor] == 0) {
                scc(dataContainer, vertexSuccessor);

                // Updates the vertex' low value if its successor hasn't been added to a strongly connected component
            } else if (dataContainer.tabScc[vertexSuccessor] == 0) {
                dataContainer.low[vertex] = Math.min(dataContainer.low[vertex], dataContainer.low[vertexSuccessor]);
            }
        }

        // If a strongly connected component has been found, add all the corresponding vertices to it
        if (dataContainer.low[vertex] == dataContainer.dfsNum[vertex]) {
            dataContainer.componentCounter++;
            int poppedVertex;
            do {
                poppedVertex = dataContainer.verteciesStack.pop();
                dataContainer.tabScc[poppedVertex] = dataContainer.componentCounter;
            } while (poppedVertex != vertex);
        }

    }
}
