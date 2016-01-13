import sys
import bisect


def two_sum(array):
    WIDTH = 10000
    out = set()
    for i in array:
        lower = bisect.bisect_left(array, -WIDTH - i)
        upper = bisect.bisect_right(array, WIDTH - i)
        out |= set([i + j for j in array[lower:upper]])
    return out


if __name__ == '__main__':
    with open(sys.argv[1]) as two_sum_input:
        collection = [int(entry) for entry in two_sum_input]
        print len(two_sum(sorted(collection)))
