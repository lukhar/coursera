from collections import defaultdict


def reverse_graph(graph):
    r_graph = {}

    for key in graph.keys():
        neighbours = graph[key]

        for neighbour in neighbours:
            if neighbour not in r_graph:
                r_graph[neighbour] = {key}

            elif key not in r_graph[neighbour]:
                r_graph[neighbour].add(key)

        if key not in r_graph:
            r_graph[key] = set()

        del graph[key]

    return r_graph


def compute_orderings(graph):
    visited, ordered = set(), []

    def recurse(graph, start):
        visited.add(start)
        for vertex in graph[start]:
            if vertex not in visited:
                recurse(graph, vertex)

        ordered.append(start)

    def traverse(graph, start):
        stack = [start]

        while stack:
            vertex = stack.pop()
            if vertex not in visited:
                visited.add(vertex)
                ordered.append(vertex)
                stack.extend(graph[vertex] - visited)

        return ordered

    for vertex in graph.keys():
        if vertex not in visited:
            traverse(graph, vertex)

    return ordered


def compute_sccs(graph):
    sccs = []
    visited = set()

    orderings = compute_orderings(graph)
    graph = reverse_graph(graph)

    def traverse(graph, start):
        number, stack = 0, [start]

        while stack:
            vertex = stack.pop()
            if vertex not in visited:
                number += 1
                visited.add(vertex)
                stack.extend(graph[vertex] - visited)

        return number

    for vertex in reversed(orderings):
        if vertex not in visited:
            sccs.append(traverse(graph, vertex))

    return sccs


if __name__ == '__main__':
    import sys

    def read_graph(filename):
        graph = defaultdict(set)
        with open(filename) as input_file:
            for line in input_file:
                edge = line.split()
                graph[int(edge[0])].add(int(edge[1]))

        return graph

    # graph = read_graph(sys.argv[1])
    # compute_orderings(graph)

    sccs = sorted(compute_sccs(read_graph(sys.argv[1])), reverse=True)
    print sccs[:5]
