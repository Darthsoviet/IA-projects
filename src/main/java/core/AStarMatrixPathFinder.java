package core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class AStarMatrixPathFinder {
  private final int wall;
  private final Integer[][] matrix;

  public AStarMatrixPathFinder(int wall, Integer[][] matrix) {
    this.wall = wall;
    this.matrix = matrix;
  }

  public List<Coordinate> solve(Coordinate begin, Coordinate end) {

    boolean finded = begin.equals(end);
    Queue<CoordinateEdge> queue =
        new PriorityQueue<>(Comparator.comparing(CoordinateEdge::getDistance));
    Set<Coordinate> visitedCoordinates = new HashSet<>();
    final Map<Coordinate, CoordinateEdge> table = initializeMap(begin);

    queue.add(new CoordinateEdge(begin, 0));
    while (!finded && !queue.isEmpty()) {

      final CoordinateEdge currentCoordinateAndDistance = queue.poll();
      final Coordinate currentCoordinate = currentCoordinateAndDistance.getCoordinate();
      if (currentCoordinate.equals(end)) {
        finded = true;
      }
      if (!visitedCoordinates.contains(currentCoordinate)) {
        visitedCoordinates.add(currentCoordinate);

        final List<Coordinate> neighbors = getCoordinateValidNeighbors(currentCoordinate);
        final float currentDistance = currentCoordinateAndDistance.getDistance();
        for (Coordinate neightbor : neighbors) {
          final float newDistance = currentDistance + 1 + calculateDistance(neightbor, end);
          if (table.get(neightbor).distance > newDistance) {
            queue.add(new CoordinateEdge(neightbor, newDistance));
            table.put(
                neightbor, new CoordinateEdge(currentCoordinate, newDistance));
          }
        }
      }
    }
    return createPathFromTable(begin,end,table);

  }
  private List<Coordinate> createPathFromTable(Coordinate begin,Coordinate end,Map<Coordinate,CoordinateEdge> table){
    final LinkedList<Coordinate> path = new LinkedList<>();
    CoordinateEdge coordinateEdge = table.get(end);
    path.add(end);
    while (coordinateEdge.getCoordinate()!=null&&!coordinateEdge.getCoordinate().equals(begin)) {
      path.add(coordinateEdge.coordinate);
      coordinateEdge = table.get(coordinateEdge.coordinate);
    }
    path.add(begin);
    return path;
  }

  private List<Coordinate> getCoordinateValidNeighbors(Coordinate current) {
    final List<Coordinate> validContiguosCoordinates = new LinkedList<>();
    if (isValidCoordinate(current.getX() + 1, current.getY())) {
      validContiguosCoordinates.add(new Coordinate(current.getX() + 1, current.getY()));
    }
    if (isValidCoordinate(current.getX() - 1, current.getY())) {
      validContiguosCoordinates.add(new Coordinate(current.getX() - 1, current.getY()));
    }
    if (isValidCoordinate(current.getX(), current.getY() + 1)) {
      validContiguosCoordinates.add(new Coordinate(current.getX(), current.getY() + 1));
    }
    if (isValidCoordinate(current.getX(), current.getY() - 1)) {
      validContiguosCoordinates.add(new Coordinate(current.getX(), current.getY() - 1));
    }
    return validContiguosCoordinates;
  }
  private boolean isValidCoordinate(int x, int y) {
    return y < matrix.length && y >= 0 && x < matrix[0].length && x >= 0 && matrix[y][x] != wall;
  }

  private Map<Coordinate, CoordinateEdge> initializeMap(Coordinate begin) {
    final HashMap<Coordinate, CoordinateEdge> table = new HashMap<>();
    for (int y = 0; y < matrix.length; y++) {
      for (int x = 0; x < matrix[y].length; x++) {
        final Coordinate coordinate = new Coordinate(x, y);
        if (coordinate.equals(begin)) {
          table.put(coordinate, new CoordinateEdge(begin, 0));
        } else {
          table.put(coordinate, new CoordinateEdge(null, Integer.MAX_VALUE));
        }
      }
    }

    return table;
  }

  private float calculateDistance(Coordinate source, Coordinate destination) {
    return
            (float) sqrt(
                pow(destination.getX() - source.getX(), 2)
                    + pow(destination.getY() - source.getY(), 2));
  }

  private static class CoordinateEdge {
    private Coordinate coordinate;
    private float distance;

    public CoordinateEdge(Coordinate coordinate, float distance) {
      this.coordinate = coordinate;
      this.distance = distance;
    }

    public Coordinate getCoordinate() {
      return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
      this.coordinate = coordinate;
    }

    public float getDistance() {
      return distance;
    }

    public void setDistance(int distance) {
      this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof CoordinateEdge)) return false;
      CoordinateEdge that = (CoordinateEdge) o;
      return Objects.equals(coordinate, that.coordinate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(coordinate, distance);
    }
  }
}
