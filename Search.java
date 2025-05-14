mport java.util.List;

public interface Search<T> {
    boolean hasPathTo(Vertex<T> v);

    List<Vertex<T>> pathTo(Vertex<T> v);
}
