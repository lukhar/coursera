def count_and_sort(seq):
    if len(seq) < 2:
        return seq, 0

    middle = len(seq) / 2
    left, l_inv = count_and_sort(seq[:middle])
    right, r_inv = count_and_sort(seq[middle:])

    m_inv, i, l, r = 0, 0, 0, 0
    while l < len(left) and r < len(right):
        if left[l] < right[r]:
            seq[i] = left[l]
            l += 1
        else:
            seq[i] = right[r]
            r += 1
            m_inv += len(left) - l
        i += 1

    while l < len(left):
        seq[i] = left[l]
        l += 1
        i += 1

    while r < len(right):
        seq[i] = right[r]
        r += 1
        i += 1

    return seq, m_inv + l_inv + r_inv


def count_inversions(seq):
    return count_and_sort(seq)[1]
