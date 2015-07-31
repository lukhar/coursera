from nose.tools import assert_equal
from unionfind import parse, quick_union, weighted_union


def test_given_raw_input_parse_into_pairs():
    assert_equal(parse('2-3 4-5 6-8 9-1'), [(2, 3), (4, 5), (6, 8), (9, 1)])


def test_given_input_perform_quick_union():
    assert_equal(quick_union([0, 1, 2, 3], [(0, 1)]), [1, 1, 2, 3])
    assert_equal(quick_union([0, 1, 2, 3], [(0, 1), (0, 3)]), [3, 3, 2, 3])


def test_given_input_perform_weighted_union():
    assert_equal(weighted_union([0, 1, 2, 3], [(0, 1), (0, 2)]), [0, 0, 0, 3])
    assert_equal(weighted_union(range(10),
                                parse('7-9 1-5 8-2 2-7 1-3 0-3 4-3 8-1 5-6')),
                 [1, 1, 8, 1, 1, 1, 1, 8, 1, 7])
