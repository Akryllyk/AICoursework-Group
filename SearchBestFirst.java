package solution;

import java.util.*;

public class SearchBestFirst {

    private static LinkedHashMap<Node, Integer> distances = new LinkedHashMap<>();
    private static LinkedHashMap<Node, Integer> shortestDistance = new LinkedHashMap<>();
    private static ArrayList<Node> openNodes = new ArrayList<>();
    private static int stepNo = 1;

    public static boolean search(Graph graph, Node startNode, Node ...endNodes) {

        // Attributes
        boolean foundEnd = false;

        // Check that the graph has an end
        boolean hasEnd = false;
        for (Node node: endNodes) {
            if (graph.getNodes().contains(node)) {
                hasEnd = true;
            }
        }

        // Fallback in case the graph does not have an end node.
        if (!hasEnd) {
            return false;
        }

        // Add all nodes into distances HashMap
        for (Node node: graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.replace(startNode, 0);

        // Redefine end nodes for .contains() method
        ArrayList<Node> goals = new ArrayList<>(Arrays.asList(endNodes));

        // Formatting
        FormatOutput.header(FormatOutput.WIDTHS);

        Node minNode = null;

        // Main loop
        while (!foundEnd) {
            minNode = findMin(distances);

            FormatOutput.stepNumber(stepNo++, FormatOutput.WIDTHS[0]);

            // Output open nodes
            openNodes = new ArrayList<>();
            for (Node node: distances.keySet()) {
                if (!distances.get(node).equals(Integer.MAX_VALUE) && !shortestDistance.containsKey(node)) {
                    openNodes.add(node);
                }
            }
            FormatOutput.openNodes(openNodes);

            // Output closed nodes
            ArrayList<Node> closedNodes = new ArrayList<>(shortestDistance.keySet());
            FormatOutput.openNodes(closedNodes);

            // Current node
            if (minNode.equals(startNode)) {
                FormatOutput.currentNode(minNode);
            } else {
                FormatOutput.currentNodeDistance(minNode);
            }

            // Check if goal
            if (goals.contains(minNode)) {
                FormatOutput.goal(minNode);
                foundEnd = true;
            }

            shortestDistance.put(minNode, distances.get(minNode));

            // Set new shortest distances
            for (Node child: minNode.getChildren()) {
                int newDistance = distances.get(minNode) + minNode.getConnections().get(child);
                if (shortestDistance.containsKey(child)) {
                    continue;
                }
                int currentDistance = distances.get(child);
                if (newDistance < currentDistance) {
                    child.setParent(minNode);
                    distances.replace(child, newDistance);
                }
            }

            // Output children
            FormatOutput.children(minNode);
            FormatOutput.newChildren(minNode, openNodes);

            distances.remove(minNode);
        }

        FormatOutput.route(minNode, startNode);

        return true;
    }

    private static Node findMin(HashMap<Node, Integer> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        Node min = new Node("x", Integer.MAX_VALUE);
        int minValue = Integer.MAX_VALUE;
        for (Node node: nodes.keySet()) {
            if ((Integer)node.getValue() < minValue && nodes.get(node) != Integer.MAX_VALUE) {
                min = node;
                minValue = (Integer)node.getValue();
            }
        }
        return min;
    }

}
