package solution;

import java.util.*;

class Node {

    // Attributes
    private String id;
    private Object value;
    private Node parent;
    private ArrayList<Node> children;
    private LinkedHashMap<Node, Integer> connections;
    private boolean visited = false;
    private boolean visible = false;

    // Constructors
    /**
     * Constructor for Node.
     * @param id ID of the node.
     * @param data The data to be stored in the node.
     * @param connections A LinkedHashMap of the connections, Node and arc length
     */
    public Node(String id, Object data, LinkedHashMap<Node, Integer> connections) {
        this.id = id;
        this.value = data;
        this.children = new ArrayList<>();
        for (Node node: connections.keySet()) {
            children.add(node);
        }
        this.connections = connections;
    }

    /**
     * Constructor for Node. Makes an empty LinkedHashMap for the arcs.
     * @param id ID of the node.
     * @param data The data to be stored in the node.
     */
    public Node(String id, Object data) {
        this(id, data, new LinkedHashMap());
    }

    /**
     * Constructor for Node. Makes a new generic node.
     */
    public Node() {
        this("node", new Object());
    }

    // Accessors
    public String getId()
    {
        return id;
    }

    public Object getValue()
    {
        return this.value;
    }

    public Node getParent() {
        return this.parent;
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public HashMap<Node, Integer> getConnections()
    {
        return this.connections;
    }

    public boolean isVisited()
    {
        return this.visited;
    }

    public boolean isVisible()
    {
        return visible;
    }

    // Mutators
    public void setId(String id)
    {
        this.id = id;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    // Methods
    public void addChild(Node child, Integer connectionLength) {
        children.add(child);
        connections.put(child, connectionLength);
    }

    public boolean removeChild(Node node) {
        children.remove(node);
        Integer key = connections.remove(node);
        return key != null;
    }

    public boolean removeChild(Object value) {
        Integer key = null;
        for (Node node: connections.keySet()) {
            if (node.getValue().equals(value)) {
                children.remove(node);
                key = connections.remove(node);
                break;
            }
        }
        return key != null;
    }
}
