package util;

import core.Labyrinth;

import java.util.Random;

public class LabyrinthGenerator {

  private final int density;

  public LabyrinthGenerator(int density) {
    this.density = density;
  }

  public Labyrinth generate(int width, int heigth) {
    final Random random = new Random();
    final Integer[][] matrix = new Integer[heigth][width];
    for (int y = 0; y < heigth; y++) {
      for (int x = 0; x < width; x++) {
        matrix[y][x] = random.nextInt(100) <= density ? 1 : 0;
      }
    }
    final LabyrinthRandomEntryExit entryExitGenerator = new LabyrinthRandomEntryExit(matrix, 1);
    return new Labyrinth(matrix, 1, 0, entryExitGenerator.randomPoint(), entryExitGenerator.randomPoint());
  }
}
