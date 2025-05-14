import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private Map<Vertex<T>, Double> dist;
    private Map<Vertex<T>, Vertex<T>> previous;
    private Vertex<T> source;

    private static class VertexDistance<T> implements Comparable<VertexDistance<T>> {
        Vertex<T> vertex;
        double distance;

        VertexDistance(Vertex<T> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistance<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public DijkstraSearch(MyGraph<T> graph, Vertex<T> source) {
        this.source = source;
        this.dist = new HashMap<>();
        this.previous = new HashMap<>();
        dijkstra(graph, source);
    }

    private void dijkstra(MyGraph<T> graph, Vertex<T> source) {
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Set<Vertex<T>> visited = new HashSet<>();
        dist.put(source, 0.0);
        pq.add(new VertexDistance<>(source, 0.0));

        while (!pq.isEmpty()) {
            VertexDistance<T> vd = pq.poll();
            Vertex<T> u = vd.vertex;
            if (visited.contains(u)) continue;
            visited.add(u);
            double d = vd.distance;

            for (Edge<T> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getDestination();
                if (!visited.contains(v)) {
                    double w = edge.getWeight();
                    double newDist = d + w;
                    if (newDist < dist.getOrDefault(v, Double.POSITIVE_INFINITY)) {
                        dist.put(v, newDist);
                        previous.put(v, u);
                        pq.add(new VertexDistance<>(v, newDist));
                    }
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<T> v) {
        return dist.containsKey(v);
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
