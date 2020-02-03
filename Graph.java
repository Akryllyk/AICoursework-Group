package solution;

import java.util.*;

class Graph {

    // Attributes
    private List<Node> nodes;

    // Constructors
    public Graph (List<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph () {
        this.nodes = new ArrayList<>();
    }

    // Accessors
    public List<Node> getNodes()
    {
        return nodes;
    }

    // Mutators
    public void setNodes(List<Node> nodes)
    {
        this.nodes = nodes;
    }
}
