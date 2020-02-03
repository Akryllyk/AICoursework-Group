package solution;

import java.util.*;

class FormatOutput {

    public static final int[] WIDTHS = {15, 25, 20, 20, 20, 20};

    /**
     * Prints the table header so that it will always be the correct size.
     * @param formatLengths integer array with the width of each column.
     */
    static void header(int[] formatLengths) {
        String[] headers = {"Step No", "Open Nodes", "Closed nodes", "Current node", "Children", "New children"};
        String output = "";
        int totalLength = 0;
        for (int i = 0; i < 6; i++) {
            totalLength += formatLengths[i];
            output = headers[i];
            while (output.length() < formatLengths[i]) {
                output += " ";
            }
            System.out.print(output);
        }
        String divider = "\n";
        for (int i = 0; i < totalLength; i++) {
            divider += "-";
        }
        System.out.println(divider);
    }

    /**
     * Prints the table header so that it will always be the correct size.
     * Uses the width array provided by this class.
     */
    static void header() {
        header(WIDTHS);
    }


    /**
     * Prints the step number column of the table.
     * @param stepNo the current step number.
     * @param formatLength the width of the step number column.
     */
    static void stepNumber(int stepNo, int formatLength) {
        String step = "Step: ";
        if (stepNo <= 9) {
            step += String.format("0%d", stepNo);
        }
        else {
            step += String.valueOf(stepNo);
        }
        step += ":";
        while (step.length() < formatLength) {
            step += " ";
        }
        System.out.print(step);
    }

    /**
     * Prints the step number column of the table.
     * Uses the width array provided by this class.
     * @param stepNo
     */
    static void stepNumber(int stepNo) {
        stepNumber(stepNo, WIDTHS[0]);
    }


    /**
     * Prints the open nodes (Node that are visible) to the table.
     * @param openNodes List of nodes to print.
     * @param formatLength Width of the column.
     */
    static void openNodes(List<Node> openNodes, int formatLength) {
        String nodes = "";
        if (!(openNodes.size() == 0)) {
            for (Node node: openNodes) {
                nodes += node.getId() + ", ";
            }
            nodes = nodes.substring(0, nodes.length() - 2);
        }
        while (nodes.length() < formatLength) {
            nodes += " ";
        }
        System.out.print(nodes);
    }

    /**
     * Prints the open nodes (Nodes that are visible) to the table.
     * Uses the width array provided by this class.
     * @param openNodes List of nodes to print.
     */
    static void openNodes(List<Node> openNodes) {
        openNodes(openNodes, WIDTHS[1]);
    }

    /**
     * UNUSED:
     * Prints the open nodes (Nodes that are visible) to the table with their distance
     * @param openNodes List of nodes to print.
     * @param distances Map of distances to use.
     * @param formatLength Width of the column.
     */
    static void openNodesDistance(List<Node> openNodes, HashMap<Node, Integer> distances, int formatLength) {

    }

    /**
     * Prints the open nodes (NOdes that are visible) to the table.
     * Uses the width array provided by this class.
     * @param openNodes List of nodes to print.
     * @param distance Map of the distance to use.
     */
    static void openNodesDistance(List<Node> openNodes, HashMap<Node, Integer> distance) {
        openNodesDistance(openNodes, distance, WIDTHS[1]);
    }


    /**
     * Prints the closed nodes (Nodes that have been visited previously) to the table.
     * @param closedNodes List of nodes to print.
     * @param formatLength Width of the column.
     */
    static void closedNodes(List<Node> closedNodes, int formatLength) {
        String nodes = "";
        if (!(closedNodes.size() == 0)) {
            for (Node node: closedNodes) {
                nodes += node.getId() + ", ";
            }
            nodes = nodes.substring(0, nodes.length() - 2);
        }
        while (nodes.length() < formatLength) {
            nodes += " ";
        }
        System.out.print(nodes);
    }

    /**
     * Prints the closed nodes (Nodes that have been visited previously) to the table.
     * Uses the width array provided by this class.
     * @param closedNodes List of nodes to print.
     */
    static void closedNodes(List<Node> closedNodes) {
        closedNodes(closedNodes, WIDTHS[2]);
    }


    /**
     * Prints the current node to the table.
     * @param currentNode The current node to print.
     * @param formatLength Width of the column.
     */
    static void currentNode(Node currentNode, int formatLength) {
        String outputString = currentNode.getId();
        while  (outputString.length() < formatLength) {
            outputString += " ";
        }
        System.out.print(outputString);
    }

    /**
     * Prints the current node to the table.
     * Uses the width array provided by this class.
     * @param currentNode The current node to print.
     */
    static void currentNode(Node currentNode) {
        currentNode(currentNode, WIDTHS[3]);
    }

    /**
     * Prints the current node, and the node that it came from.
     * @param currentNode The current node to be printed.
     * @param formatLength Width of the column.
     */
    static void currentNodeParent(Node currentNode, int formatLength) {
        Node parent = currentNode.getParent();
        String outputString = String.format("%s->%s", parent.getId(), currentNode.getId());
        while  (outputString.length() < formatLength) {
            outputString += " ";
        }
        System.out.print(outputString);
    }

    /**
     * Prints the current node, and the node that it came from.
     * Uses the width array provided by this class.
     * @param currentNode The current node to be printed.
     */
    static void currentNodeParent(Node currentNode) {
        currentNodeParent(currentNode, WIDTHS[3]);
    }

    /**
     * Prints the current node, the node that it came from, and the distance between them.
     * @param currentNode The current node to be printed.
     * @param formatLength Width of the column.
     */
    static void currentNodeDistance(Node currentNode, int formatLength) {
        Node parent = currentNode.getParent();
        String outputString = String.format("%s->%s: %d", parent.getId(), currentNode.getId(), parent.getConnections().get(currentNode));
        while  (outputString.length() < formatLength) {
            outputString += " ";
        }
        System.out.print(outputString);
    }

    /**
     * Prints the current node, the node that it came from, and the distance between them.
     * Uses the width array provided by this class.
     * @param currentNode The current node to be printed.
     */
    static void currentNodeDistance(Node currentNode) {
        currentNodeDistance(currentNode, WIDTHS[3]);
    }

    /**
     * Prints the current node, the node that it came from, and the value of the node traveled to.
     * @param currentNode The current node to be printed.
     * @param formatLength The width of the column.
     */
    static void currentNodeValue(Node currentNode, int formatLength) {
        Node parent = currentNode.getParent();
        String outputString = String.format("%s->%s: %s", parent.getId(), currentNode.getId(), currentNode.getValue());
        while  (outputString.length() < formatLength) {
            outputString += " ";
        }
        System.out.print(outputString);
    }

    /**
     * Prints the current node, the node that it came from, and the value of the node traveled to.
     * Uses the width array provided by this class.
     * @param currentNode The current node to be printed.
     */
    static void currentNodeValue(Node currentNode) {
        currentNodeValue(currentNode, WIDTHS[3]);
    }


    /**
     * Prints the children of the current node into the table.
     * @param currentNode The current node to print the children of.
     * @param formatLength The width of the column.
     */
    static void children(Node currentNode, int formatLength)
    {
        String nodes = "";
        for (Node node : currentNode.getChildren()) {
            nodes += node.getId() + ", ";
        }
        if (nodes.length() > 0) {
            nodes = nodes.substring(0, nodes.length() - 2);
        }
        while (nodes.length() < formatLength) {
            nodes += " ";
        }
        System.out.print(nodes);
    }

    /**
     * Prints the children of the current node into the table.
     * Uses the widths array provided by this class.
     * @param currentNode The current node to print the children of.
     */
    static void children(Node currentNode) {
        children(currentNode, WIDTHS[4]);
    }


    /**
     * Prints the new children found of the current node.
     * @param currentNode The current node to find the children of.
     * @param openNodes The open nodes to remove from the children.
     * @param formatLength The width of the column.
     */
    static void newChildren(Node currentNode, List<Node> openNodes, int formatLength) {
        String nodes = "";
        for (Node node : currentNode.getChildren()) {
            if (!openNodes.contains(node)) {
                nodes += node.getId() + ", ";
            }
        }
        if (nodes.length() > 0) {
            nodes = nodes.substring(0, nodes.length() - 2);
        }
        while (nodes.length() < formatLength) {
            nodes += " ";
        }
        System.out.println(nodes);
    }

    /**
     * Prints the new children found of the current node.
     * Uses the widths array provided by this class.
     * @param currentNode The current node to find the children of.
     * @param openNodes The open nodes to remove from the children.
     */
    static void newChildren(Node currentNode, List<Node> openNodes) {
        newChildren(currentNode, openNodes, WIDTHS[5]);
    }


    /**
     * Prints which goal has been found.
     * @param goalNode The goal node reached.
     */
    static void goal(Node goalNode) {
        String output = String.format("Found Goal: %s ", goalNode.getId());
        while (output.length() < 20) {
            output += "-";
        }
        System.out.printf("\n%s\n", output);
    }


    /**
     * Prints the route taken from the start node to the goal node. Requires parents to be assigned in the method.
     * @param goalNode The goal node reached.
     * @param startNode The node started from.
     */
    static void route(Node goalNode, Node startNode) {
        ArrayList<Node> nodeOrder = new ArrayList<>();

        Node currentNode = goalNode;
        nodeOrder.add(currentNode);
        int total = 0;

        boolean foundStart = false;
        while (!currentNode.equals(startNode)) {
            Node parentNode = currentNode.getParent();
            nodeOrder.add(0, parentNode);
            total += parentNode.getConnections().get(currentNode);
            currentNode = parentNode;
        }

        String outputString = "Route: ";
        for (int i = 0; i < nodeOrder.size() - 1; i++) {
            outputString += String.format("%s, ", nodeOrder.get(i).getId());
        }
        outputString = String.format("%s%s: %d", outputString, nodeOrder.get(nodeOrder.size() - 1).getId(), total);
        System.out.println(outputString);
    }
}
