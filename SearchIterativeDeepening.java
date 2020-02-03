package solution;

import java.util.*;

public class SearchIterativeDeepening {

    private static ArrayList<Node> openNodes = new ArrayList<>(),
            closedNodes = new ArrayList<>();
    private static int stepNo = 1, k;
    private static Node nodeGoal = null;
    private static Node initialNode;

    public static boolean searchRecursive (Graph graph, Node startNode, Node ...endNodes) {
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

        // Redefine end nodes for .contains() method
        ArrayList<Node> goals = new ArrayList<>(Arrays.asList(endNodes));
        // Variables un=sed in search
        k = 0;
        boolean foundEnd = false;
        initialNode = startNode;

        FormatOutput.header(FormatOutput.WIDTHS);

        while (!foundEnd) {
            System.out.printf("----- Pass k = %d\n", k);
            foundEnd = depthFirstRecursive(startNode, goals, k);
            // Set nodes to not visited after each pass
            for (Node node: graph.getNodes()) {
                node.setVisited(false);
            }
            // Increase depth
            k++;

            // Reset variables
            stepNo = 1;
            closedNodes = new ArrayList<>();
        }
        FormatOutput.route(nodeGoal, startNode);
        return true;
    }

    /**
     * Recursive method to execute Depth-First Search.
     * @param checkNode The current node.
     * @param endNodes List of nodes to end recursion on.
     * @return whether the end has been reached.
     */
    private static boolean depthFirstRecursive (Node checkNode, List<Node> endNodes, int depth) {
        // Variables used
        boolean foundEnd = false;
        boolean end = false;
        int nextLevel = depth - 1;

        // Say we have visited the node
        checkNode.setVisited(true);

        // Output current known details
        FormatOutput.stepNumber(stepNo);
        FormatOutput.openNodes(openNodes);
        FormatOutput.openNodes(closedNodes);
        if (checkNode.equals(initialNode)) {
            FormatOutput.currentNode(checkNode);
        } else {
            FormatOutput.currentNodeParent(checkNode);
        }


        // Check if this node is a goal node
        if (endNodes.contains(checkNode)) {
            System.out.println();
            nodeGoal = checkNode;
            FormatOutput.goal(checkNode);
            return true;
        }

        // Add open nodes
        openNodes.remove(checkNode);
        closedNodes.add(checkNode);
        ArrayList<Node> newChildren = checkNode.getChildren();
        if (nextLevel >= 0) {
            for (Node child : newChildren) {
                child.setParent(checkNode);
                if (child.isVisible()) {
                    newChildren.remove(child);
                }
            }
            FormatOutput.children(checkNode);
            FormatOutput.newChildren(checkNode, openNodes);

            openNodes.addAll(0, newChildren);
        } else {
            System.out.printf("none at k=%s\n", k);
        }


        // Check if we need to go deeper
        for (Node node: checkNode.getChildren()) {
            if (!node.isVisited() && nextLevel >= 0) {
                stepNo++;
                end = depthFirstRecursive(node, endNodes, nextLevel);
            }
            else {
                return false;
            }
            // Check if we need to end
            if (end) {
                foundEnd = end;
                break;
            }
        }
        return foundEnd;
    }

}
