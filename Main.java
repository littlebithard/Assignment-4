import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyGraph<String> graph = new MyGraph<>();

        Vertex<String> astana = new Vertex<>("Astana");
        Vertex<String> karaganda = new Vertex<>("Karaganda");
        Vertex<String> balkash = new Vertex<>("Balkash");
        Vertex<String> almaty = new Vertex<>("Almaty");
        Vertex<String> pavlodar = new Vertex<>("Pavlodar");
        Vertex<String> semey = new Vertex<>("Semey");
        Vertex<String> taldykorgan = new Vertex<>("Taldykorgan");
        Vertex<String> kokshetau = new Vertex<>("Kokshetau");
        Vertex<String> taraz = new Vertex<>("Taraz");

        graph.addVertex(astana);
        graph.addVertex(karaganda);
        graph.addVertex(balkash);
        graph.addVertex(almaty);
        graph.addVertex(pavlodar);
        graph.addVertex(semey);
        graph.addVertex(taldykorgan);
        graph.addVertex(kokshetau);
        graph.addVertex(taraz);

        graph.addEdge(astana, karaganda, 200);
        graph.addEdge(karaganda, balkash, 400);
        graph.addEdge(balkash, almaty, 600);
        graph.addEdge(balkash, taraz, 500);
        graph.addEdge(almaty, taraz, 300);
        graph.addEdge(almaty, taldykorgan, 250);
        graph.addEdge(taldykorgan, semey, 300);
        graph.addEdge(astana, pavlodar, 450);
        graph.addEdge(pavlodar, semey, 350);
        graph.addEdge(astana, kokshetau, 300);

        System.out.println("Graph:");
        graph.printGraph();

        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, astana);
        List<Vertex<String>> bfsPath = bfs.pathTo(almaty);
        System.out.println("\nBFS path from Astana to Almaty:");
        if (bfsPath != null) {
            for (Vertex<String> v : bfsPath) {
                System.out.print(v.getData() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No path found.");
        }

        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, astana);
        List<Vertex<String>> dijkstraPath = dijkstra.pathTo(almaty);
        System.out.println("\nDijkstra path from Astana to Almaty:");
        if (dijkstraPath != null) {
            for (Vertex<String> v : dijkstraPath) {
                System.out.print(v.getData() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No path found.");
        }
    }
}
