def parse(input_str):
    return [tuple(map(int, pair.split('-'))) for pair in input_str.split()]


def quick_union(sequence, transformations):
    for p, q in transformations:
        parent, new_parent = sequence[p], sequence[q]
        for i in range(len(sequence)):
            if sequence[i] == parent:
                sequence[i] = new_parent

    return sequence


def weighted_union(sequence, transformations):
    size = [1 for _ in sequence]

    def root(ident):
        while sequence[ident] != ident:
            ident = sequence[ident]

        return ident

    for p, q in transformations:
        pp, pq = root(p), root(q)
        if pp == pq:
            continue
        if size[pp] < size[pq]:
            sequence[pp] = pq
            size[pq] += size[pp]
        else:
            sequence[pq] = pp
            size[pp] += size[pq]

    return sequence
