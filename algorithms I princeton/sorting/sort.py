def insertion_sort(sequence, delimeter):
    if len(sequence) < 2:
        return sequence

    counter = 0
    for i in range(1, len(sequence)):
        for j in reversed(range(i)):
            if sequence[j] > sequence[j + 1]:
                sequence[j], sequence[j + 1] = sequence[j + 1], sequence[j]

                counter += 1

                if counter == delimeter:
                    return sequence
            else:
                break

    return sequence


def merge(sequence, lo, mid, hi):
    aux = list(sequence)
    i, j, k = lo, mid + 1, lo

    while k <= hi:
        if i > mid:
            sequence[k] = aux[j]
            j += 1
        elif j > hi:
            sequence[k] = aux[i]
            i += 1
        elif aux[i] < aux[j]:
            sequence[k] = aux[i]
            i += 1
        else:
            sequence[k] = aux[j]
            j += 1
        k += 1

    return sequence


def top_down_mergesort(sequence, delimeter):

    counter = {'merges': 0}

    def sort(sequence, lo, hi):
        if lo >= hi:
            return sequence

        mid = lo + (hi - lo) / 2
        sort(sequence, lo, mid)
        sort(sequence, mid + 1, hi)

        if counter['merges'] == delimeter:
            return sequence
        else:
            merge(sequence, lo, mid, hi)
            counter['merges'] += 1

        return sequence

    return sort(sequence, 0, len(sequence) - 1)


def bottom_up_mergesort(sequence, delimeter):
    lo, sz = 0, 1
    counter = 0

    while sz < len(sequence):
        lo = 0
        while lo < len(sequence) - sz:
            if counter == delimeter:
                return sequence

            merge(sequence, lo, lo + sz - 1, min(lo + sz + sz - 1, len(sequence) - 1))
            counter += 1
            lo += (sz + sz)
        sz += 1

    return sequence


def partition(sequence, lo, hi):
    i, j = lo + 1, hi
    pivot = sequence[lo]

    while True:
        while pivot > sequence[i]:
            if i == hi:
                break
            i += 1
        while pivot < sequence[j]:
            if j == lo:
                break
            j -= 1
        if i >= j:
            break

        sequence[i], sequence[j] = sequence[j], sequence[i]

    sequence[lo], sequence[j] = sequence[j], sequence[lo]

    return sequence

if __name__ == '__main__':
    import sys
    sequence = [int(val) for val in sys.argv[1:]]
    print 'insertion sort:       ', ' '.join(str(val) for val in insertion_sort(list(sequence), delimeter=4))
    print 'top-down merge sort:  ', ' '.join(str(val) for val in top_down_mergesort(list(sequence), delimeter=7))
    print 'bottom-up merge sort: ', ' '.join(str(val) for val in bottom_up_mergesort(list(sequence), delimeter=7))
    print 'partition two-way     ', ' '.join(str(val) for val in partition(list(sequence), lo=0, hi=len(sequence) - 1))
