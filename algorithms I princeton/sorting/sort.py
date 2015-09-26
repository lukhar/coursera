def insertion_sort(sequence, delimeter):
    if len(sequence) < 2:
        return sequence

    counter = 0
    for i in range(1, len(sequence)):
        for j in reversed(range(i)):
            if sequence[j] <= sequence[j + 1]:
                break
            sequence[j], sequence[j + 1] = sequence[j + 1], sequence[j]

            counter += 1

            if counter == delimeter:
                return sequence

    return sequence


class MergeSort(object):

    _buffer = []

    @classmethod
    def _merge(cls, sequence, lo, mid, hi):
        for i in xrange(len(sequence)):
            cls._buffer[i] = sequence[i]

        l, r, k = lo, mid + 1, lo

        for k in xrange(lo, hi+1):
            if l > mid:
                sequence[k] = cls._buffer[r]
                r += 1
            elif r > hi:
                sequence[k] = cls._buffer[l]
                l += 1
            elif cls._buffer[l] < cls._buffer[r]:
                sequence[k] = cls._buffer[l]
                l += 1
            else:
                sequence[k] = cls._buffer[r]
                r += 1

        return sequence

    @classmethod
    def top_down_mergesort(cls, sequence, delimeter):
        cls._buffer = [0] * len(sequence)

        counter = {'merges': 0}

        def sort(sequence, lo, hi):
            if lo >= hi:
                return sequence

            mid = lo + (hi - lo) / 2
            sort(sequence, lo, mid)
            sort(sequence, mid + 1, hi)

            if counter['merges'] == delimeter:
                return sequence

            cls._merge(sequence, lo, mid, hi)
            counter['merges'] += 1

            return sequence

        return sort(sequence, 0, len(sequence) - 1)

    @classmethod
    def bottom_up_mergesort(cls, sequence, delimeter):
        cls._buffer = [0] * len(sequence)
        lo, sz, counter = 0, 1, 0

        while sz < len(sequence):
            lo = 0
            while lo < len(sequence) - sz:
                if counter == delimeter:
                    return sequence

                cls._merge(sequence, lo, lo + sz - 1, min(lo + sz + sz - 1, len(sequence) - 1))
                counter += 1
                lo += (sz + sz)
            sz += 1

        return sequence


def partition(sequence, lo, hi):
    i, j = lo, hi + 1
    pivot = sequence[lo]

    while True:
        while True:
            i += 1
            if i == hi or pivot <= sequence[i]:
                break
        while True:
            j -= 1
            if j == lo or pivot >= sequence[j]:
                break
        if i >= j:
            break

        sequence[i], sequence[j] = sequence[j], sequence[i]

    sequence[lo], sequence[j] = sequence[j], sequence[lo]

    return sequence

if __name__ == '__main__':
    import sys
    sequence = [int(val) for val in sys.argv[1:]]
    print 'insertion sort:       ', ' '.join(str(val) for val in insertion_sort(list(sequence), delimeter=4))
    print 'top-down merge sort:  ', ' '.join(str(val) for val in MergeSort.top_down_mergesort(list(sequence), delimeter=7))
    print 'bottom-up merge sort: ', ' '.join(str(val) for val in MergeSort.bottom_up_mergesort(list(sequence), delimeter=7))
    print 'partition two-way     ', ' '.join(str(val) for val in partition(list(sequence), lo=0, hi=len(sequence) - 1))
