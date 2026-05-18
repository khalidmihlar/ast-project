Gradescope autograder for ArrayCollection-only submissions.

This version accepts one submitted Java file with any filename, such as Bear.java or Otter.java. The file must still define class ArrayCollection. The autograder copies that file internally to assignment3/ArrayCollection.java, normalizes the package declaration to package assignment3;, compiles it with ArrayCollectionGrader.java, and runs the ArrayCollection-only tests.
