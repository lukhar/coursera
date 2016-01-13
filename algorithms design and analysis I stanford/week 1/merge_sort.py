def merge_sort(seq):
    if len(seq) < 2:
        return seq

    middle = len(seq) / 2
    left = merge_sort(seq[:middle])
    right = merge_sort(seq[middle:])

    i, l, r = 0, 0, 0
    while l < len(left) and r < len(right):
        if left[l] < right[r]:
            seq[i] = left[l]
            l += 1
        else:
            seq[i] = right[r]
            r += 1
        i += 1

    while l < len(left):
        seq[i] = left[l]
        l += 1
        i += 1

    while r < len(right):
        seq[i] = right[r]
        r += 1
        i += 1

    return seq
