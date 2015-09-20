from nose.tools import assert_equal
from sort import insertion_sort


def test_given_sequnce_and_delimiter_n_return_subsorted_seq_after_n_exchanges():
    assert_equal(insertion_sort([1, 2, 7, 4, 5, 6], delimeter=1), [1, 2, 4, 7, 5, 6])
