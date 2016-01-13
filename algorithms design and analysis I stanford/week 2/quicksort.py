def quicksort(seq):
    def sort(seq, begin, end):
        if begin >= end:
            return seq

        p, m = begin, begin
        for i in range(begin+1, end):
            if seq[i] < seq[p]:
                seq[i], seq[m+1] = seq[m+1], seq[i]
                m += 1

        seq[m], seq[p] = seq[p], seq[m]

        sort(seq, begin, m)
        sort(seq, m+1, end)

        return seq

    return sort(seq, 0, len(seq))
