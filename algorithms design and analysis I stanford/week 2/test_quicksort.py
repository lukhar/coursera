from nose.tools import assert_equal
from quicksort import quicksort


def test_sort_given_sequence():
    assert_equal(quicksort([1, 8, 7, 2, 3, 4]), [1, 2, 3, 4, 7, 8])
    assert_equal(quicksort([8, 7, 4, 3, 2, 1]), [1, 2, 3, 4, 7, 8])
    assert_equal(quicksort([1, 2, 3, 4, 7, 8]), [1, 2, 3, 4, 7, 8])
    assert_equal(quicksort([]), [])
