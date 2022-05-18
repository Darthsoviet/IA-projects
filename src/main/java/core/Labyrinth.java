package core;

import util.MatrixPainter;
import util.MatrixPrinter;

import java.util.LinkedList;
import java.util.List;

public class Labyrinth {

  private final Integer[][] matrix;
  private final int wall;
  private final int blank;
  private final Coordinate begin;
  private final Coordinate end;

  public Labyrinth(Integer[][] matrix, int wall, int blank, Coordinate begin, Coordinate end) {
    this.matrix = matrix;
    this.wall = wall;
    this.blank = blank;
    this.begin = begin;
    this.end = end;
  }




  public void solveAndPrint() {
    AStarMatrixPathFinder pathFinder = new AStarMatrixPathFinder(wall,matrix);
    final List<Coordinate> path = pathFinder.solve(begin, end);
    final MatrixPainter<Integer> integerMatrixPainter =
        new MatrixPainter(wall, blank, begin, end, matrix, path);
    final MatrixPrinter matrixPrinter = new MatrixPrinter();
    matrixPrinter.print(integerMatrixPainter.paint());
  }
}
