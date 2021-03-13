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
        DFS dfs = new DFS(table,"S");
        dfs.search("G");

    }
}
