import sys
import math


def quicksort_count_first(seq):
    def sort(seq, begin, end):
        if begin >= end:
            return 0

        p, m = begin, begin
        for i in range(begin+1, end):
            if seq[i] < seq[p]:
                seq[i], seq[m+1] = seq[m+1], seq[i]
                m += 1

        seq[m], seq[p] = seq[p], seq[m]

        left = sort(seq, begin, m)
        right = sort(seq, m+1, end)

        return end - begin - 1 + left + right

    return sort(seq, 0, len(seq))


def quicksort_count_final(seq):
    def sort(seq, begin, end):
        if begin >= end:
            return 0

        seq[begin], seq[end-1] = seq[end-1], seq[begin]

        p, m = begin, begin
        for i in range(begin+1, end):
            if seq[i] < seq[p]:
                seq[i], seq[m+1] = seq[m+1], seq[i]
                m += 1

        seq[m], seq[p] = seq[p], seq[m]

        left = sort(seq, begin, m)
        right = sort(seq, m+1, end)

        return end - begin - 1 + left + right

    return sort(seq, 0, len(seq))


def quicksort_count_median(seq):
    def median(seq, begin, end):
        length = end - begin
        middle = begin + (length + 1) / 2 - 1
        values_by_index = {seq[begin]: begin, seq[middle]: middle, seq[end-1]: end-1}
        sor = sorted(values_by_index.keys())

        return values_by_index[sor[1]]

    def sort(seq, begin, end):
        if begin >= end - 1:
            return 0

        p = median(seq, begin, end)
        seq[begin], seq[p] = seq[p], seq[begin]

        p, m = begin, begin
        for i in range(begin+1, end):
            if seq[i] < seq[p]:
                seq[i], seq[m+1] = seq[m+1], seq[i]
                m += 1

        seq[m], seq[p] = seq[p], seq[m]

        left = sort(seq, begin, m)
        right = sort(seq, m+1, end)

        return end - begin - 1 + left + right

    return sort(seq, 0, len(seq))


if __name__ == '__main__':
    with open(sys.argv[1]) as input_file:
        numbers = [int(number) for number in input_file.readlines()]
        print 'comparisons when pivot is first number: {}'.format(
            quicksort_count_first(numbers[:]))
        print 'comparisons when pivot is first number: {}'.format(
            quicksort_count_final(numbers[:]))
        print 'comparisons when pivot is median number: {}'.format(
            quicksort_count_median(numbers[:]))
