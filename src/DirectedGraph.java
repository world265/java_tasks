public class DirectedGraph extends Graph {

    public DirectedGraph(int vertices) {
        super(vertices);
    }

    @Override
    public GraphType getType() {
        return GraphType.DIRECTED;
    }

    @Override
    public void addEdge(int from, int to) {
        checkEdge(from, to);

        if (!hasEdge(from, to)) {
            graph.get(from).add(to);
        }
    }

    @Override
    public void removeEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        graph.get(from).remove(
                Integer.valueOf(to)
        );
    }
}