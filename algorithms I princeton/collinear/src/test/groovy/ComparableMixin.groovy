trait ComparableMixin {

    def List toComparable(LineSegment[] segments) {
        return segments*.toString()
    }
}