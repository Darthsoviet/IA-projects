import core.Labyrinth;
import util.LabyrinthGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {

    final LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator(30);

    final List<Labyrinth> laberinthList =
            IntStream.rangeClosed(0, 10)
                    .mapToObj(s -> labyrinthGenerator.generate(30, 30))
                    .collect(Collectors.toUnmodifiableList());

    laberinthList.forEach(Labyrinth::solveAndPrint);


  }
}
