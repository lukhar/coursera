from nose.tools import assert_equal
from scc import reverse_graph, compute_orderings, compute_sccs


def test_reverse_directed_graph():
    assert_equal(reverse_graph({1: {2, 3}, 2: {4}, 3: {4}}),
                 {1: set(), 2: {1}, 3: {1}, 4: {2, 3}})


def test_reverse_two_element_graph():
    assert_equal(reverse_graph({1: {2}, 2: set()}), {1: set(), 2: {1}})


def test_reverse_symetric_graph():
    assert_equal(reverse_graph({1: {2, 3}, 2: {1, 3}, 3: {1, 2}}),
                 {1: {2, 3}, 2: {1, 3}, 3: {1, 2}})


def test_reverse_reversed_graph_should_be_same():
    assert_equal(reverse_graph(reverse_graph({1: {2, 3}, 2: {4}, 3: {4}})),
                 {1: {2, 3}, 2: {4}, 3: {4}, 4: set()})


def test_given_two_element_graph_compute_orderings():
    assert_equal(compute_orderings({1: {2}, 2: set()}), [1, 2])


def test_given_bigger_graph_compute_orderings():
    assert_equal(compute_orderings({1: {2, 3}, 2: {4}, 3: {4}, 4: set()}), [1, 3, 4, 2])


#def test_given_two_element_graph_compute_sccs():
#    assert_equal(compute_sccs({1: [2], 2: []}), [1, 1])
