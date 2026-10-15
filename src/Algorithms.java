import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

interface GraphAlgorithm {

    List<Integer> execute(
            Graph graph,
            int start
    );
}

class BFS implements GraphAlgorithm {

    @Override
    public List<Integer> execute(
            Graph graph,
            int start
    ) {

        if (start < 0 ||
                start >= graph.getVerticesCount()) {

            throw new GraphException(
                    "Неверная стартовая вершина"
            );
        }

        boolean[] visited =
                new boolean[graph.getVerticesCount()];

        Queue<Integer> queue =
                new LinkedList<>();

        List<Integer> result =
                new ArrayList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int vertex = queue.poll();

            result.add(vertex);

            for (int next :
                    graph.getNeighbors(vertex)) {

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return result;
    }
}

class DFS implements GraphAlgorithm {

    @Override
    public List<Integer> execute(
            Graph graph,
            int start
    ) {

        if (start < 0 ||
                start >= graph.getVerticesCount()) {

            throw new GraphException(
                    "Неверная стартовая вершина"
            );
        }

        boolean[] visited =
                new boolean[graph.getVerticesCount()];

        List<Integer> result =
                new ArrayList<>();

        dfs(graph, start, visited, result);

        return result;
    }

    private void dfs(
            Graph graph,
            int vertex,
            boolean[] visited,
            List<Integer> result
    ) {

        visited[vertex] = true;
        result.add(vertex);

        for (int next :
                graph.getNeighbors(vertex)) {

            if (!visited[next]) {
                dfs(
                        graph,
                        next,
                        visited,
                        result
                );
            }
        }
    }
}

class RandomGraphGenerator {

    private final java.util.Random random =
            new java.util.Random();

    public Graph generate(
            GraphType type,
            int vertices,
            int edges
    ) {

        int maxEdges;

        if (type == GraphType.DIRECTED) {
            maxEdges =
                    vertices * (vertices - 1);
        } else {
            maxEdges =
                    vertices * (vertices - 1) / 2;
        }

        if (edges < 0 || edges > maxEdges) {
            throw new GraphException(
                    "Неверное количество ребер"
            );
        }

        Graph result;

        if (type == GraphType.DIRECTED) {
            result = new DirectedGraph(vertices);
        } else {
            result = new UndirectedGraph(vertices);
        }

        int added = 0;

        while (added < edges) {

            int from = random.nextInt(vertices);
            int to = random.nextInt(vertices);

            if (from == to) {
                continue;
            }

            if (result.hasEdge(from, to)) {
                continue;
            }

            result.addEdge(
                    new Edge(from, to)
            );

            added++;
        }

        return result;
    }
}
