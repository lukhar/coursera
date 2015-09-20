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


if __name__ == '__main__':
    import sys
    sequence = [int(val) for val in sys.argv[1:]]
    print 'insertion sort:',  ' '.join(str(val) for val in insertion_sort(sequence, delimeter=6))
