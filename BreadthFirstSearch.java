import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private Map<Vertex<T>, Vertex<T>> previous;
    private Vertex<T> source;

    public BreadthFirstSearch(MyGraph<T> graph, Vertex<T> source) {
        this.source = source;
        this.previous = new HashMap<>();
        bfs(graph, source);
    }

    private void bfs(MyGraph<T> graph, Vertex<T> source) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        previous.put(source, null);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            for (Edge<T> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getDestination();
                if (!previous.containsKey(v)) {
                    previous.put(v, u);
                    queue.add(v);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<T> v) {
        return previous.containsKey(v);
    }

    @Override
    public List<Vertex<T>> pathTo(Vertex<T> v) {
        if (!hasPathTo(v)) return null;
        List<Vertex<T>> path = new ArrayList<>();
        for (Vertex<T> x = v; x != null; x = previous.get(x)) {
            path.add(x);
        }
        Collections.reverse(path);
        return path;
    }
}
