import core.AStarMatrixPathFinder;
import core.Coordinate;
import core.Labyrinth;
import org.junit.Test;
import util.LabyrinthGenerator;
import util.MatrixPainter;
import util.MatrixPrinter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AstarMatrixPathFinderTest {

  @Test
  public void test() {

    Coordinate end = new Coordinate(11, 7), begin = new Coordinate(0, 3);
    Integer[][] laberinth =
        new Integer[][] {
          {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
          {0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
          {0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0},
          {0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
          {0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0},
          {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
          {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
        };
    final MatrixPrinter matrixPrinter = new MatrixPrinter();

    matrixPrinter.print(laberinth);

    final AStarMatrixPathFinder aStarMatrixPathFinder = new AStarMatrixPathFinder(1, laberinth);
    final List<Coordinate> path = aStarMatrixPathFinder.solve(begin, end);
    final MatrixPainter<Integer> integerMatrixPainter =
        new MatrixPainter<>(1, 0, begin, end, laberinth, path);
    final String[][] paintedMatrix = integerMatrixPainter.paint();
    matrixPrinter.print(paintedMatrix);

    path.forEach(System.out::println);
  }

  @Test
  public void testMany() {

    final LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator(30);

    final List<Labyrinth> laberinthList =
        IntStream.rangeClosed(0, 10)
            .mapToObj(s -> labyrinthGenerator.generate(30, 30))
            .collect(Collectors.toUnmodifiableList());
    laberinthList.forEach(Labyrinth::solveAndPrint);
  }
}
