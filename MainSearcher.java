package solution;

import java.util.Arrays;

public class MainSearcher {
    public static void main(String[] args)
    {
        // Create nodes
        Node nodeS = new Node("S", 9);
        Node nodeA = new Node("A", 6);
        Node nodeB = new Node("B", 2);
        Node nodeC = new Node("C", 2);
        Node nodeD = new Node("D", 5);
        Node nodeE = new Node("E", 5);
        Node nodeF = new Node("F", 3);
        Node nodeJ = new Node("J", 1);
        Node nodeG1 = new Node("G1",0);
        Node nodeG2 = new Node("G2", 0);
        Node[] nodes = {nodeS, nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG1, nodeG2, nodeJ};

        // Add arcs
        nodeS.addChild(nodeA, 4);
        nodeS.addChild(nodeC, 3);
        nodeS.addChild(nodeD, 1);
        nodeA.addChild(nodeB, 3);
        nodeA.addChild(nodeG1, 8);
        nodeB.addChild(nodeG1, 4);
        nodeC.addChild(nodeB, 2);
        nodeC.addChild(nodeD, 2);
        nodeC.addChild(nodeF, 2);
        nodeC.addChild(nodeJ, 4);
        nodeD.addChild(nodeE, 3);
        nodeE.addChild(nodeF, 3);
        nodeE.addChild(nodeG2, 6);
        nodeF.addChild(nodeG2, 4);
        nodeJ.addChild(nodeF, 5);
        nodeJ.addChild(nodeG1, 3);

        Graph graph = new Graph(Arrays.asList(nodes));

        // Breadth First Search
        System.out.println("----- Breadth First Search -----");
        SearchBreadthFirst breadthFirst = new SearchBreadthFirst();
        breadthFirst.search(graph, nodeS, nodeG1, nodeG2);
        resetNodes(graph);


        // Depth First Search
        /*
        System.out.println("\n----- Depth First Search -----");
        SearchDepthFirst depthFirst = new SearchDepthFirst();
        depthFirst.searchReursive(graph, nodeS, nodeG1, nodeG2);
        resetNodes(graph);
         */

        // Iterative Deepening
        System.out.println("\n----- Iterative Deepening -----");
        SearchIterativeDeepening.searchRecursive(graph, nodeS, nodeG1, nodeG2);
        resetNodes(graph);

        // Uniform Search
        System.out.println("\n----- Uniform Search -----");
        SearchUniformSearch.search(graph, nodeS, nodeG1, nodeG2);
        resetNodes(graph);

        // Best First Search
        System.out.println("\n----- Best First Search -----");
        SearchBestFirst.search(graph, nodeS, nodeG1, nodeG2);
        resetNodes(graph);

        //System.out.printf("5\u2082");
    }

    /**
     * Resets each node in a graph.
     * @param g The graph to reset the nodes of.
     */
    private static void resetNodes(Graph g) {
        for (Node node: g.getNodes()) {
            node.setVisited(false);
            node.setVisible(false);
            node.setParent(null);
        }
    }
}
