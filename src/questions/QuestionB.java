package questions;

import java.util.*;

public class QuestionB {

  public static int getMinimumTapeCount(final Batch batch) {

    int tapeSize = batch.getTapeSize();

    List<Integer> files =  new ArrayList<>();
    for (int i : batch.getFileSizes()) {
      files.add(i);
    }

    // order file by size
    Collections.sort(files, Collections.reverseOrder());

    // plan tapes by distributing files: big files + small file so that they do not exceed the size of the tapes but try
    // to use as much space as possible
    List<Tape> tapes = new ArrayList<>();

    while (!files.isEmpty()) {

      // index remove
      Integer firstFile = files.remove(0);

      Integer secondFile = findSecond(files, firstFile, tapeSize);

      // find second file that fits: first + second <= tapeSize
      if (secondFile != null) {
        // object remove
        files.remove(secondFile);
      }

      // create new Tape() and add to tapes
      tapes.add(new Tape(firstFile, secondFile));
    }

    // return tapes.size()

    return tapes.size();

  }

  private static Integer findSecond(List<Integer> files, Integer firstFile, int tapeSize) {

    for (Integer file : files) {
      if (file + firstFile <= tapeSize) {
        return file;
      }
    }

    return null;
  }

  private static class Tape  {

    private final Integer first;
    // might be null thus objects
    private final Integer second;

    private Tape(Integer first, Integer second) {
      this.first = first;
      this.second = second;
    }
  }

}
