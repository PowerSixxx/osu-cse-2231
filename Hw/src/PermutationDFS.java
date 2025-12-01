import java.util.ArrayList;
import java.util.List;

public class PermutationDFS {
    static int n;
    static boolean[] used;
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        n = 3;
        used = new boolean[n + 1];
        dfs();
    }

    static void dfs() {
        if (path.size() == n) {
            System.out.println(path);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                path.add(i);
                dfs();
                path.remove(path.size() - 1); // 回溯
                used[i] = false;
            }
        }
    }
}
