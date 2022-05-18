package util;

import core.Coordinate;

import java.util.Random;

public class LabyrinthRandomEntryExit {

  private Integer[][] matrix;
  private Integer wall;

  public LabyrinthRandomEntryExit(Integer[][] matrix, Integer wall) {

    this.matrix = matrix;
    this.wall = wall;
  }

  public Coordinate randomPoint() {
    Coordinate coordinate;
    final Random random = new Random();
    int y = random.nextInt(matrix.length);
    int x = random.nextInt(matrix[0].length);
    do {
      coordinate = new Coordinate(x, y);
      y = random.nextInt(matrix.length);
      x = random.nextInt(matrix[0].length);
    } while (isValidCoordinate(x, y));
    return coordinate;
  }



  private boolean isValidCoordinate(int x, int y) {
    return y < matrix.length && y >= 0
            && x < matrix[0].length && x >= 0
            && !matrix[y][x].equals( wall);
  }
}
