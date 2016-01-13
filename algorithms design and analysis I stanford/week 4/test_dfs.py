from nose.tools import assert_equal


def dfs(graph):
    visited = set()

    def traverse(v):
        if v in visited:
            return
        visited.add(v)
        for n in graph[v]:
            traverse(n)

    traverse(graph.keys()[0])

    return visited


def test_dfs():
    assert_equal(dfs({1: [2, 6],
                      2: [1, 3, 6],
                      3: [2, 4, 5],
                      4: [3, 5],
                      5: [3, 4, 6],
                      6: [1, 2, 5]}), {1, 2, 3, 4, 5, 6})
