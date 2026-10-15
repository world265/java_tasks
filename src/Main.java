public class Main {

    public static void main(String[] args) {

        System.out.println(
                "Неориентированный граф"
        );

        Graph graph =
                new UndirectedGraph(5);

        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(0, 2));
        graph.addEdge(new Edge(1, 3));
        graph.addEdge(new Edge(2, 4));

        graph.print();

        System.out.println(
                "Количество вершин: "
                        + graph.getVerticesCount()
        );

        System.out.println(
                "Соседи вершины 0: "
                        + graph.getNeighbors(0)
        );

        System.out.println(
                "Есть ребро 0-1: "
                        + graph.hasEdge(0, 1)
        );


        GraphAlgorithm bfs = new BFS();
        GraphAlgorithm dfs = new DFS();

        System.out.println(
                "BFS: "
                        + bfs.execute(graph, 0)
        );

        System.out.println(
                "DFS: "
                        + dfs.execute(graph, 0)
        );


        System.out.println(
                "\nУдаляем ребро 0-1:"
        );

        graph.removeEdge(0, 1);

        graph.print();


        System.out.println(
                "\nОриентированный граф"
        );

        Graph directed =
                new DirectedGraph(4);

        directed.addEdge(0, 1);
        directed.addEdge(1, 2);
        directed.addEdge(2, 3);

        directed.print();

        System.out.println(
                "BFS: "
                        + bfs.execute(directed, 0)
        );

        System.out.println(
                "DFS: "
                        + dfs.execute(directed, 0)
        );


        System.out.println(
                "\nСлучайный граф"
        );

        RandomGraphGenerator generator =
                new RandomGraphGenerator();

        Graph randomGraph =
                generator.generate(
                        GraphType.UNDIRECTED,
                        5,
                        5
                );

        randomGraph.print();

        System.out.println(
                "BFS: "
                        + bfs.execute(randomGraph, 0)
        );

        System.out.println(
                "DFS: "
                        + dfs.execute(randomGraph, 0)
        );


        System.out.println(
                "\nСвое исключение"
        );

        try {

            graph.addEdge(0, 100);

        } catch (GraphException e) {

            System.out.println(
                    "Ошибка: "
                            + e.getMessage()
            );
        }
    }
}