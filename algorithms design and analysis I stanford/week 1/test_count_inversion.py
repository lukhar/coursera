from nose.tools import assert_equal
from count_inversions import count_inversions


def test_count_inversions():
    assert_equal(count_inversions([1, 3, 5, 2, 4, 6]), 3)
    assert_equal(count_inversions([6, 5, 4, 3, 2, 1]), 15)
