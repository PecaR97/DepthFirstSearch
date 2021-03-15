import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DFS2 {
    private final int MAX_ITERATIONS = 30;
    private ArrayList<Node> visitedNodes;
    private ArrayList<String> openedNodes;
    private HashMap<String, String[]> table;
    private ArrayList<Node> priorityList;
    private Node source;
    private boolean isFound;
    private int currentIteration;
    private int depth;
    private ArrayList<Integer> depthValues;


    public DFS2(HashMap<String, String[]> table, String source) {
        this.visitedNodes = new ArrayList<>();
        this.openedNodes = new ArrayList<>();
        this.table = table;
        this.priorityList = new ArrayList<>();
        this.source = new Node(source, null);
        this.isFound = false;
        this.currentIteration = 0;
        this.depth = 0;


    }

    public void setSource(Node source) {
        this.source = source;
    }

    public void reset() {
        this.visitedNodes = new ArrayList<>();
        this.priorityList = new ArrayList<>();
        this.isFound = false;
        this.openedNodes = new ArrayList<>();
    }

    public void reset(String source) {
        reset();
        this.source = new Node(source, null);
    }

    public void search(String destination) {

        while (!isFound && currentIteration < MAX_ITERATIONS) {
            depth = 0;
            System.out.println();
            System.out.println("Iteration " + currentIteration + ": ");
            this.source = new Node(this.source.getValue(),null);
            this.priorityList.add(source);
            this.openedNodes.add(source.getValue());

            while (!priorityList.isEmpty()) {
                Node currentNode = priorityList.get(0);
                System.out.println("Priority list: " + getPriorityListValues());
                this.visitedNodes.add(currentNode);
                if (currentNode.getValue().equals(destination)) {
                    this.isFound = true;
                    break;
                } else {
                    depth = countDepth(currentNode);
                    if (depth < currentIteration) {
                        openChildren(currentNode);

                    }

                    this.priorityList.remove(0);
                    addChildrenToPriorityList(currentNode);
                }
            }



            printVisitedNodes();
            printOpenedNodes();
            if(visitedNodes.size() == table.keySet().size())
                break;
            openedNodes = new ArrayList<>();
            visitedNodes = new ArrayList<>();
            currentIteration++;
        }


        System.out.println();
        if (isFound) {
            System.out.println("Destination found after " + (currentIteration + 1) + " iterations");
            printPath();
        } else {
            System.out.println("Destination not found after " + (currentIteration + 1) + " iterations");
        }


    }

    private int countDepth(Node currentNode) {
        int currentDepth = 0;
        Node parent = currentNode.getParent();
        while (parent != null) {
            currentDepth++;
            parent = parent.getParent();
        }
        return currentDepth;
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
            if (!openedNodes.contains(listOfChildren[i])) {
                Node newNode = new Node(listOfChildren[i], currentNode);
                openedNodes.add(newNode.getValue());
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
        System.out.println("Visited nodes step by step: ");
        for (int i = 0; i < visitedNodes.size(); i++) {
            System.out.printf("%-4s%s%n", (i + 1) + ".", this.visitedNodes.get(i).getValue());
        }
    }

    public void printOpenedNodes() {
        System.out.println("Opened node nodes step by step: ");
        for (int i = 0; i < openedNodes.size(); i++) {
            System.out.printf("%-4s%s%n", (i + 1) + ".", this.openedNodes.get(i));
        }
    }

}
