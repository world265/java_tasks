public class UndirectedGraph extends Graph {

    public UndirectedGraph(int vertices) {
        super(vertices);
    }

    @Override
    public GraphType getType() {
        return GraphType.UNDIRECTED;
    }

    @Override
    public void addEdge(int from, int to) {
        checkEdge(from, to);

        if (!hasEdge(from, to)) {
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    @Override
    public void removeEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        graph.get(from).remove(
                Integer.valueOf(to)
        );

        graph.get(to).remove(
                Integer.valueOf(from)
        );
    }
}
