import java.util.*;

class DijkstraToll {

    static final int V = 7;

    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];
        int parent[] = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        dist[src] = 0;
        parent[src] = -1;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v] &&
                    graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {

                    parent[v] = u;
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist, parent);
    }

    void printPath(int parent[], int j, String names[]) {

        if (parent[j] == -1) {
            System.out.print(names[j]);
            return;
        }

        printPath(parent, parent[j], names);
        System.out.print(" -> " + names[j]);
    }

    void printSolution(int dist[], int parent[]) {

        String names[] = {
            "KIR", "URS", "KHA",
            "TLG", "KRD", "DEH", "PUN"
        };

        System.out.println("Shortest Path:");
        printPath(parent, 6, names);

        System.out.println("\nTotal Toll Cost: ₹" + dist[6]);
    }

    public static void main(String[] args) {

        int graph[][] = new int[][] {

            //KIR URS KHA TLG KRD DEH PUN
            {0, 12, 15, 0, 0, 0, 0}, // KIR
            {0, 0, 8, 18, 0, 0, 0},  // URS
            {0, 0, 0, 0, 14, 0, 0}, // KHA
            {0, 0, 0, 0, 0, 20, 0}, // TLG
            {0, 0, 0, 0, 0, 16, 0}, // KRD
            {0, 0, 0, 0, 0, 0, 10}, // DEH
            {0, 0, 0, 0, 0, 0, 0}   // PUN
        };

        DijkstraToll t = new DijkstraToll();
        t.dijkstra(graph, 0);
    }
}