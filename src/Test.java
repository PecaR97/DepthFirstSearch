import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String[]> table = new HashMap<>();
        table.put("A", new String[]{"B", "D", "S"});
        table.put("B", new String[]{"A", "C", "D"});
        table.put("C", new String[]{"B"});
        table.put("D", new String[]{"A", "E", "S"});
        table.put("E", new String[]{"D", "F"});
        table.put("F", new String[]{"E", "G"});
        table.put("G", new String[]{"F"});
        table.put("S", new String[]{"A", "D"});

        /*//Depth First 1.0
        System.out.println("DEPTH FIRST SEARCH WITH REPEATING NODES");
        System.out.println("------------------------------------------");
        DFS dfs = new DFS(table, "S");
        dfs.search("G");
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println();*/
        //Depth First 1.0
        System.out.println("DEPTH FIRST SEARCH WITHOUT REPEATING NODES");
        System.out.println("------------------------------------------");
        DFS2 dfs2 = new DFS2(table, "S");
        dfs2.search("G");
        System.out.println("------------------------------------------");
    }
}
