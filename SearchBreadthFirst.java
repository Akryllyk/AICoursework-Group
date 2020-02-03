package solution;

import java.util.*;

public class SearchBreadthFirst {
    // Attributes
    private Queue<Node> nodeQueue;

    public SearchBreadthFirst() {
        this.nodeQueue = new LinkedList<>();
    }

    public void search (Graph graph, Node startNode, Node ...endNodes) {
        // Redefine end nodes for .contains() method
        ArrayList<Node> goals = new ArrayList<>(Arrays.asList(endNodes));

        // Create arrays for open nodes and closed nodes
        ArrayList<Node> openNodes;                          // Nodes that are visible but not visited
        ArrayList<Node> closedNodes = new ArrayList<>();    // Nodes that have been visited
        Node currentNode = null;

        nodeQueue.add(startNode);
        boolean foundGoal = false;
        int stepNo = 1;
        FormatOutput.header(FormatOutput.WIDTHS);
        while (!nodeQueue.isEmpty() && !foundGoal) {
            FormatOutput.stepNumber(stepNo, FormatOutput.WIDTHS[0]);

            // Open nodes
            openNodes = new ArrayList<>();
            for (Node node: nodeQueue ) {
                openNodes.add(node);
            }
            FormatOutput.openNodes(openNodes, FormatOutput.WIDTHS[1]);

            //Closed nodes
            FormatOutput.closedNodes(closedNodes, FormatOutput.WIDTHS[2]);
            currentNode = nodeQueue.remove();
            currentNode.setVisited(true);
            FormatOutput.currentNode(currentNode, FormatOutput.WIDTHS[3]);
            FormatOutput.children(currentNode, FormatOutput.WIDTHS[4]);
            FormatOutput.newChildren(currentNode, openNodes, FormatOutput.WIDTHS[5]);
            if (goals.contains(currentNode)) {
                FormatOutput.goal(currentNode);
                foundGoal = true;
                break;
            }

            // Check through children
            Set<Node> children = currentNode.getConnections().keySet();
            for (Node child: children) {
                if (!child.isVisible() && !child.isVisited()) {
                    nodeQueue.add(child);
                    child.setParent(currentNode);
                    child.setVisible(true);
                }
            }
            // Increment step
            closedNodes.add(currentNode);
            stepNo++;
        }
        FormatOutput.route(currentNode, startNode);

        if (!foundGoal) {
            System.out.println("Goal not found");
        }
    }

}
