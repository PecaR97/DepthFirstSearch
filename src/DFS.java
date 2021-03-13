import java.util.ArrayList;
import java.util.HashMap;

public class DFS {
    private ArrayList<Node> openedNodes;
    private HashMap<String, String[]> table;
    private ArrayList<Node> priorityList;
    private Node source;
    private boolean isFound;

    public DFS(HashMap<String, String[]> table, String source) {
        this.openedNodes = new ArrayList<>();
        this.table = table;
        this.priorityList = new ArrayList<>();
        this.source = new Node(source, null);
        this.isFound = false;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public void reset() {
        this.openedNodes = new ArrayList<>();
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
            this.openedNodes.add(currentNode);
            if (currentNode.getValue() == destination) {
                this.isFound = true;
                break;
            } else {
                openChildren(currentNode);
                this.priorityList.remove(0);
                addChildrenToPriorityList(currentNode);
            }
        }
        System.out.println(isFound ? "Found" : "Not found");
        System.out.println();
        printOpenedNodes();

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
            if (!isOnPathToRoot(listOfChildren[i],currentNode)) {
                Node newNode = new Node(listOfChildren[i], currentNode);

            }

        }
    }

    private boolean isOnPathToRoot(String nodeValue,Node parent) {
        while (parent != null) {
            if (parent.getValue() == nodeValue) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public void printOpenedNodes() {
        for (int i = 0; i < openedNodes.size(); i++) {
            System.out.printf("%-4s%s%n",(i+1)+".",this.openedNodes.get(i).getValue());
        }
    }

}
