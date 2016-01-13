import sys
import random


def read_graph(filename):
    graph = {}
    with open(filename) as input_file:
        for line in input_file:
            vertexes = line.split()
            graph[int(vertexes[0])] = [int(v) for v in vertexes[1:]]

    return graph


def merge_vertexes(graph, a, b):
    graph[a] = [v for v in graph[a] + graph[b] if v not in [a, b]]
    for n in graph[b]:
        graph[n] = [v if v != b else a for v in graph[n]]
    del graph[b]


def mincut(graph):
    if len(graph) == 2:
        return len(graph.values()[0])

    a = random.choice(graph.keys())
    b = random.choice(graph[a])

    merge_vertexes(graph, a, b)
    return mincut(graph)


def minkarger(graph):
    return min([mincut(dict(graph)) for i in range(len(graph))])


if __name__ == '__main__':
    graph = read_graph(sys.argv[1])
    print minkarger(graph)
