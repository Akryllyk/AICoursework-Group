package solution;

import java.util.*;

public class SearchDepthFirst {
    // Attributes
    private Stack<Node> nodeStack;
    private ArrayList<Node> openNodes = new ArrayList<>();      // Nodes that are visible but have not been visited
    private ArrayList<Node> closedNodes = new ArrayList<>();    // Nodes that have been visited

    public SearchDepthFirst() {
        this.nodeStack = new Stack<>();
    }

    public boolean searchReursive(Graph graph, Node startNode, Node ...endNodes) {
        // Check that the graph has an end
        boolean hasEnd = false;
        for (Node node: endNodes) {
            if (graph.getNodes().contains(node)) {
                hasEnd = true;
            }
        }
        if (!hasEnd) {
            return false;
        }

        FormatOutput.header(FormatOutput.WIDTHS);

        // Redefine end nodes for .contains() method
        ArrayList<Node> goals = new ArrayList<>(Arrays.asList(endNodes));
        startNode.setVisible(true);
        return depthFirstRecursive(startNode, goals, 1);
    }

    /**
     * Recursive method to execute Depth-First Search
     * @param checkNode The current node
     * @param endNodes List of nodes to end recurrsion on
     * @return whether the end has been found or not
     */
    private boolean depthFirstRecursive (Node checkNode, List<Node> endNodes, int stepNo) {

        boolean foundEnd = false;
        boolean end = false;

        checkNode.setVisited(true);
        FormatOutput.stepNumber(stepNo, FormatOutput.WIDTHS[0]);
        FormatOutput.openNodes(openNodes, FormatOutput.WIDTHS[1]);
        for (int i = checkNode.getChildren().size() - 1; i > 0; i--) {
            Node child = checkNode.getChildren().get(i);
            if (!child.isVisible()) {
                checkNode.getChildren().get(i).setVisible(true);
                openNodes.add(0, child);
            }
        }
        FormatOutput.closedNodes(closedNodes, FormatOutput.WIDTHS[2]);
        FormatOutput.currentNode(checkNode, FormatOutput.WIDTHS[3]);
        FormatOutput.children(checkNode, FormatOutput.WIDTHS[4]);
        FormatOutput.newChildren(checkNode, openNodes, FormatOutput.WIDTHS[5]);
        openNodes.remove(checkNode);
        closedNodes.add(checkNode);
        if (endNodes.contains(checkNode)) {
            FormatOutput.goal(checkNode);
            return true;
        }
        for (Node node: checkNode.getChildren()) {
            if (!node.isVisited()) {
                end = depthFirstRecursive(node, endNodes, ++stepNo);
            }
            if (end) {
                foundEnd = true;
                break;
            }
        }
        return foundEnd;

    }


    public boolean searchNonRecursive(Graph graph, Node startNode, Node ...endNodes) {
        // Check that the graph has an end
        boolean hasEnd = false;
        for (Node node: endNodes) {
            if (graph.getNodes().contains(node)) {
                hasEnd = true;
            }
        }

        if (!hasEnd) {
            return false;
        }

        // Redefine end nodes for .contains() method
        ArrayList<Node> goals = new ArrayList<>(Arrays.asList(endNodes));
        boolean foundGoal = false;

        FormatOutput.header(FormatOutput.WIDTHS);

        nodeStack.push(startNode);
        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pop();
            System.out.printf("Current node: %s\n", currentNode.getId());
            if (goals.contains(currentNode)) {
                System.out.printf("Goal found: %s\n", currentNode.getId());
                foundGoal = true;
                break;
            }
            if (!currentNode.isVisible()) {
                currentNode.setVisible(true);
                ArrayList<Node> nodes = currentNode.getChildren();
                Collections.reverse(nodes);
                for (Node neighbor: nodes) {
                    nodeStack.push(neighbor);
                }
            }
        }

        return true;
    }

}