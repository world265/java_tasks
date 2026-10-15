import java.util.ArrayList;
import java.util.List;

enum GraphType {
    DIRECTED,
    UNDIRECTED
}

record Edge(int from, int to) {
}

public abstract class Graph {

    protected int vertices;
    protected List<List<Integer>> graph;

    public Graph(int vertices) {
        if (vertices <= 0) {
            throw new GraphException(
                    "Количество вершин должно быть больше 0"
            );
        }

        this.vertices = vertices;
        graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public abstract GraphType getType();

    public abstract void addEdge(int from, int to);

    public abstract void removeEdge(int from, int to);

    public void addEdge(Edge edge) {
        addEdge(edge.from(), edge.to());
    }

    public int getVerticesCount() {
        return vertices;
    }

    public List<Integer> getNeighbors(int vertex) {
        checkVertex(vertex);
        return graph.get(vertex);
    }

    public boolean hasEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        return graph.get(from).contains(to);
    }

    protected void checkVertex(int vertex) {
        if (vertex < 0 || vertex >= vertices) {
            throw new GraphException(
                    "Вершины " + vertex + " не существует"
            );
        }
    }

    protected void checkEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        if (from == to) {
            throw new GraphException(
                    "Нельзя создать петлю"
            );
        }
    }

    public void print() {
        System.out.println("Тип: " + getType());

        for (int i = 0; i < vertices; i++) {
            System.out.println(
                    i + " -> " + graph.get(i)
            );
        }
    }
}