from nose.tools import assert_equal
from merge_sort import merge_sort


def test_merge_sort():
    assert_equal(merge_sort([1, 4, 2, 8, 7]), [1, 2, 4, 7, 8])
