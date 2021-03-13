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

    public void setSource(Node source){
        this.source = source;
    }

    public void reset(){
        this.openedNodes = new ArrayList<>();
        this.priorityList = new ArrayList<>();
        this.isFound = false;
    }

    public void reset(String source){
        reset();
        this.source = new Node(source, null);
    }

    public void search(String destination){
        this.priorityList.add(source);

        while(!priorityList.isEmpty()){
            Node currentNode = priorityList.get(0);
            if(currentNode.getValue() == destination){
                this.isFound = true;
                break;
            } else {

            }
        }

    }
}
