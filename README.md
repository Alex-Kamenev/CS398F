# CS398F: advanced data structures and computational geometry

Pace University coursework from fall 2020. Assignment 1 contains a binary heap priority queue and a van Emde Boas queue exercise. Assignment 2 contains geometric segment intersection, heap/radix sorting, and a sweep-line experiment.

Compile and run the regression checks from the repository root with a JDK on `PATH` (tested with Microsoft OpenJDK 21):

```powershell
$sources = @(Get-ChildItem assignment1,assignment2,tests -Filter *.java -Recurse | ForEach-Object FullName)
javac -d out @sources
java -cp out com.akamenev.assignment2.AlgorithmRegression
java -cp out com.akamenev.assignment1.VebRegression
java -cp out com.akamenev.assignment2.VebRegressionAssignment2
```

The checks cover heap capacity growth, extraction order, empty-queue handling, comparator identity, and a single-segment sweep. Both copies of the van Emde Boas queue are compared against Java's `TreeMap` for 500 seeded operations, checking membership, payloads, minimum/maximum, successor/predecessor and extraction. Universes must be powers of two of at least 2; keys are unique integers in `[0, universe)`.

The sweep-line code is a historical assignment prototype; its full geometric correctness and ordering for degeneracies have not been established. `Intersect.segmentIntersect` provides the pairwise intersection predicate. Java compiler warnings about unchecked generic operations remain in this coursework.
