import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DFS {
    private ArrayList<Node> visitedNodes;
    private HashMap<String, String[]> table;
    private ArrayList<Node> priorityList;
    private Node source;
    private boolean isFound;

    public DFS(HashMap<String, String[]> table, String source) {
        this.visitedNodes = new ArrayList<>();

        this.table = table;
        this.priorityList = new ArrayList<>();
        this.source = new Node(source, null);
        this.isFound = false;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public void reset() {
        this.visitedNodes = new ArrayList<>();
        this.priorityList = new ArrayList<>();
        this.isFound = false;
    }

    public void reset(String source) {
        reset();
        this.source = new Node(source, null);
    }

    public void search(String destination) {
        this.priorityList.add(source);


        while (!priorityList.isEmpty()) {
            Node currentNode = priorityList.get(0);
            System.out.println("Priority list: " + getPriorityListValues());
            this.visitedNodes.add(currentNode);
            if (currentNode.getValue().equals(destination)) {
                this.isFound = true;
                break;
            } else {
                openChildren(currentNode);
                this.priorityList.remove(0);
                addChildrenToPriorityList(currentNode);
            }
        }
        System.out.println();
        System.out.println(isFound ? "Found" : "Not found");
        System.out.println();
        printVisitedNodes();
        System.out.println();
        printPath();
    }

    private void printPath() {
        if (!isFound)
            return;

        System.out.print("Path from source " + this.source.getValue() + " to destination " + priorityList.get(0).getValue() + ": ");

        Node node = priorityList.get(0);
        ArrayList<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.getParent();
        }
        Collections.reverse(path);

        for (Node n : path) {
            System.out.print(n.getValue());
            if (!n.getValue().equals(priorityList.get(0).getValue())) {
                System.out.print("->");
            }
        }
        System.out.println();

    }

    private String getPriorityListValues() {
        String toReturn = "";
        for (int i = 0; i < priorityList.size(); i++) {
            toReturn += priorityList.get(i).getValue() + " ";
        }
        return toReturn;
    }

    private void addChildrenToPriorityList(Node currentNode) {
        ArrayList<Node> newList = new ArrayList<>();
        if (currentNode.getChildren().isEmpty()) {
            return;
        } else {
            for (Node child : currentNode.getChildren()) {
                newList.add(child);
            }
            newList.addAll(this.priorityList);
            this.priorityList = newList;
        }
    }

    public void openChildren(Node currentNode) {
        String[] listOfChildren = table.get(currentNode.getValue());
        if (listOfChildren.length == 0) {
            return;
        }
        for (int i = 0; i < listOfChildren.length; i++) {
            if (!isOnPathToRoot(listOfChildren[i], currentNode)) {
                Node newNode = new Node(listOfChildren[i], currentNode);

            }

        }
    }

    private boolean isOnPathToRoot(String nodeValue, Node parent) {
        while (parent != null) {
            if (parent.getValue() == nodeValue) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public void printVisitedNodes() {
        for (int i = 0; i < visitedNodes.size(); i++) {
            System.out.printf("%-4s%s%n", (i + 1) + ".", this.visitedNodes.get(i).getValue());
        }
    }

}
