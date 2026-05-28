import java.util.*;

public class TarjanSCC {
    private int V;
    private LinkedList<Integer>[] adj;
    private int time = 0;

    TarjanSCC(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void SCCUtil(int u, int disc[], int low[],
                 Stack<Integer> st, boolean stackMember[]) {

        disc[u] = low[u] = ++time;
        st.push(u);
        stackMember[u] = true;

        for (Integer n : adj[u]) {

            if (disc[n] == -1) {
                SCCUtil(n, disc, low, st, stackMember);

                low[u] = Math.min(low[u], low[n]);
            }

            else if (stackMember[n]) {
                low[u] = Math.min(low[u], disc[n]);
            }
        }

        int w = -1;

        if (low[u] == disc[u]) {
            System.out.print("SCC: ");

            while (w != u) {
                w = st.pop();
                System.out.print("m" + (w + 1) + " ");
                stackMember[w] = false;
            }

            System.out.println();
        }
    }

    void SCC() {
        int disc[] = new int[V];
        int low[] = new int[V];
        boolean stackMember[] = new boolean[V];
        Stack<Integer> st = new Stack<>();

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
                SCCUtil(i, disc, low, st, stackMember);
        }
    }

    public static void main(String args[]) {

        TarjanSCC g = new TarjanSCC(9);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);

        g.addEdge(2, 3);
        g.addEdge(3, 4);

        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);

        g.addEdge(3, 7);
        g.addEdge(7, 2);

        g.addEdge(7, 8);

        System.out.println("Strongly Connected Components:");
        g.SCC();
    }
}