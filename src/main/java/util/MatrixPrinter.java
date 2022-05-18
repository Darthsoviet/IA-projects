package util;

import javax.swing.*;

public class MatrixPrinter {

  public <T> void print(T[][] matrix) {
    StringBuilder stringifyMatrix = new StringBuilder();
    for (T[] ts : matrix) {
      for (int j = 0; j < matrix.length; j++) {
        stringifyMatrix.append(" ");
        stringifyMatrix.append(ts[j].toString());
      }
      stringifyMatrix.append("\n");
    }
    System.out.println(stringifyMatrix);
  }
}
