package util;

import core.Coordinate;
import util.OutColor;
import util.OutColors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixPainter<T> {

  private final T wall;
  private final T blank;
  private Coordinate begin;
  private Coordinate end;
  private T[][] matrix;
  private List<Coordinate> path;

  public MatrixPainter(
          T wall, T blank, Coordinate begin, Coordinate end, T[][] matrix, List<Coordinate> path) {
    this.wall = wall;
    this.blank = blank;
    this.begin = begin;
    this.end = end;
    this.matrix = matrix;
    this.path = path;
  }

  public String[][] paint() {
    final Set<Coordinate> pathSet = new HashSet<>(path);
    String[][] newMatrix = new String[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        final Coordinate coordinate = new Coordinate(j, i);
        T currentStep = matrix[i][j];

        if (coordinate.equals(end)) {
          newMatrix[i][j] = OutColor.paint(currentStep.toString(), OutColors.ANSI_YELLOW);

        } else if (coordinate.equals(begin)) {
          newMatrix[i][j] = OutColor.paint(currentStep.toString(), OutColors.ANSI_CYAN);

        } else if (pathSet.contains(coordinate)) {
          newMatrix[i][j] = OutColor.paint(currentStep.toString(), OutColors.ANSI_GREEN);

        } else if (currentStep.equals(wall)) {
          newMatrix[i][j] = OutColor.paint(currentStep.toString(), OutColors.ANSI_RED);
        } else if (currentStep.equals(blank)) {
          newMatrix[i][j] = OutColor.paint(currentStep.toString(), OutColors.ANSI_BLACK);
        }
      }
    }
    return newMatrix;
  }
}
