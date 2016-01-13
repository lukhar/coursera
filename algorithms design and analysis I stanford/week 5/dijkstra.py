import sys
import operator
from collections import defaultdict


def dijsktra(graph):
    distances = defaultdict(lambda: 10000000)

    def traverse(graph, start):
        distances[start] = 0
        queue, visited = {(start, 0)}, set()

        while queue:
            vertex = min(queue, key=operator.itemgetter(1))
            queue.remove(vertex)

            for neighbour in graph[vertex[0]]:
                alt = distances[vertex[0]] + neighbour[1]
                if alt < distances[neighbour[0]]:
                    distances[neighbour[0]] = alt

            visited.add(vertex)
            queue = queue | (graph[vertex[0]] - visited)

    traverse(graph, start=1)

    return dict(distances)


if __name__ == '__main__':
    graph = defaultdict(set)
    with open(sys.argv[1]) as graph_file:
        for line in graph_file:
            vertex = int(line.split()[0])
            edges = line.split()[1:]

            graph[vertex] = {(int(e.split(',')[0]), int(e.split(',')[1])) for e in edges}

    distances = dijsktra(graph)

    queried = [str(distances[v]) for v in [7, 37, 59, 82, 99, 115, 133, 165, 188, 197]]

    print ','.join(queried)
