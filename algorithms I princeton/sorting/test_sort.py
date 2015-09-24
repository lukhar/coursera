from nose.tools import assert_equal
from sort import insertion_sort, top_down_mergesort, merge, bottom_up_mergesort


def test_given_sequnce_and_delimiter_n_return_subsorted_seq_after_n_exchanges():
    assert_equal(insertion_sort([1, 2, 7, 4, 5, 6], delimeter=1), [1, 2, 4, 7, 5, 6])


def test_partially_sort_sequence_using_top_down_merge_sort():
    assert_equal(top_down_mergesort([2, 1, 7, 4, 5, 6], delimeter=1), [1, 2, 7, 4, 5, 6])
    assert_equal(top_down_mergesort([45, 51, 92, 25, 93, 22, 35, 27, 29, 49, 80, 19], delimeter=7),
                 [22, 25, 45, 51, 92, 93, 27, 29, 35, 49, 80, 19])


def test_partially_sort_sequence_using_bottom_up_merge_sort():
    assert_equal(bottom_up_mergesort([76, 43, 51, 90, 68, 73, 57, 41, 99, 84], delimeter=7),
                 [43, 51, 76, 90, 41, 57, 68, 73, 84, 99])


def test_merge_two_lists():
    assert_equal(merge([1, 3, 7, 2, 5, 6], lo=0, mid=2, hi=5), [1, 2, 3, 5, 6, 7])
