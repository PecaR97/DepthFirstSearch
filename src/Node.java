import java.util.ArrayList;

public class Node {
    private String value;
    private Node parent;
    private ArrayList<Node> children;

    public Node(String value) {
        this(value, null, new ArrayList<Node>());
    }

    public Node(String value, Node parent) {
        this(value, parent, new ArrayList<>());
    }

    public Node(String value, Node parent, ArrayList<Node> children) {
        setValue(value);
        setParent(parent);
        setChildren(children);
        if (parent != null)
            parent.addChild(this);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public String toString() {
        return String.format("Value: %s%nParent: %s%nChildren: %s", getValue(), hasParent() ? getParent().getValue() : "", toStringChildren());
    }

    public String toStringChildren() {
        if (getChildren().isEmpty())
            return String.format("%n");

        String toReturn = "";

        for (int i = 0; i < this.children.size(); i++) {
            toReturn += getChildren().get(i).getValue();
            if (i != this.children.size() - 1)
                toReturn += ", ";
            else
                toReturn += String.format("%n");
        }

        return toReturn;
    }
}
