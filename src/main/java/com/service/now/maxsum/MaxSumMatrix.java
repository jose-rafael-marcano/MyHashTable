package com.service.now.maxsum;

import sun.applet.resources.MsgAppletViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Using recursion we know that value of cell [n-1][m-1] can be reached by left position [n-1][m-2],
 * diagonal [n-2][m-2] and up [n-2][m-1] so we need to reach all 3 cells, and we also know that the
 * first cel can move right, down and diagonal so we start the recursion using the three positions and take
 * the max of the 3 results.
 * 
 */
public class MaxSumMatrix {
  public static void main(String[] args) {///

    MaxSumMatrix maxSumMatrix = new MaxSumMatrix();
    int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    int[][] example = {
      {4, 8, 100, -1000}, {70, -10, 2000, 70}, {-5, -21, -6, 8}, {10000, -20, 15, 21}
    };

    int[][] empty = {{}};

    int[][] twoXtwo = {{1, 2}, {5, 6}};


    int result = maxSumMatrix.findMaxSum(example);
    System.out.println(" result m1 length " + example.length + " result =" + result);
      int [][] cost={{10,10,2,0,20,4},{1,0,0,30,2,5},{0,10,4 ,0,2,0},{1,0,2,20,0,4}};

      result=maxSumMatrix.findMaxSum(cost);
      System.out.println(" result cost length " + cost.length + " result =" + result);

  }

  private int findMaxSum(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    if (matrix[0].length == 0) return 0;
    if (matrix[0].length == 1 && matrix.length == 1) return matrix[0][0];
    if (matrix.length == 1) {
      return doLinearSum(matrix);
    }
    if (matrix[0].length == 1) {
      return doLinearSum(matrix);
    }

    // first case 0,0;
    Map<String, Boolean> visitedRight = new HashMap<>();
    visitedRight.put("00", true);

    List<String> pathRight = new ArrayList<>();
    pathRight.add("[0][0]-");

    Map<String, Boolean> visitedDown = new HashMap<>();
    visitedDown.put("00", true);

    List<String> pathDown = new ArrayList<>();
    pathDown.add("[0][0]-");

    Map<String, Boolean> visitedDiagonal = new HashMap<>();
    visitedDiagonal.put("00", true); // 1

    List<String> pathDiagonal = new ArrayList<>();
    pathDiagonal.add("[0][0]-");

    int right = findMaxSum(matrix, 0, 1, visitedRight, pathRight);
    int down = findMaxSum(matrix, 1, 0, visitedDown, pathDown);
    int diagonal = findMaxSum(matrix, 1, 1, visitedDiagonal, pathDiagonal);

    System.out.println("right =" + right + " path right=" + pathRight);
    System.out.println("down =" + down + " path down=" + pathDown);
    System.out.println("diagonal =" + diagonal + " path pathDiagonal=" + pathDiagonal);

    int result = matrix[0][0] + Math.max(right, Math.max(down, diagonal));

    return result;
  }

  /**
   * @param matrix
   * @return
   */
  private int doLinearSum(int[][] matrix) {
    int result = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        result += matrix[i][j];
      }
    }
    return result;
  }

  /**
   * @param matrix
   * @param i
   * @param j
   * @param visited
   * @return
   */
  private int findMaxSum(
      int[][] matrix, int i, int j, Map<String, Boolean> visited, List<String> path) {
    int result = 0;
    if (i == matrix.length - 1 && j == matrix[0].length - 1) {
      path.add("-done [i]" + i + "[j]= " + j+ " value="+matrix[i][j]);
      return matrix[i][j];
    }

    if ((visited.get(getKey(i, j)) == null) || (!visited.get(getKey(i, j)))) {
      if (i == 0 && (j > 0 && j <= matrix[0].length - 2)) {
        visited.put(getKey(i, j), true);
        path.add("- [i]" + i + "[j]= " + j+" value="+matrix[i][j]);

        int right = findMaxSum(matrix, i, getRightColumn(j), visited, path); // 1
        int down = findMaxSum(matrix, getDown(i), j, visited, path); // 2

        int diagonalRight = findMaxSum(matrix, getDown(i), getRightColumn(j), visited, path); // 3

        int tmpMax = Math.max(right, Math.max(down, diagonalRight));
        int diagonalLeft = findMaxSum(matrix, getDown(i), getLeftColumn(j), visited, path); // 4

        tmpMax = Math.max(tmpMax, diagonalLeft); //
        int left = findMaxSum(matrix, i, getLeftColumn(j), visited, path); // left 5
        tmpMax = Math.max(tmpMax, left); // 8
        result = matrix[i][j] + tmpMax;
      } else if (j == 0 && (i > 0 && i <= matrix.length - 2)) {
        visited.put(getKey(i, j), true);
        path.add("- [i]" + i + "[j]= " + j+ " value="+matrix[i][j]);

        int down = findMaxSum(matrix, getDown(i), j, visited, path); // 4

        int right = findMaxSum(matrix, i, getRightColumn(j), visited, path); // 3

        int up = findMaxSum(matrix, getUp(i), j, visited, path); // 1

        int diagonalUpRight = findMaxSum(matrix, getUp(i), getRightColumn(j), visited, path); // 2

        int tmpMax = Math.max(up, Math.max(diagonalUpRight, right));

        tmpMax = Math.max(tmpMax, down);
        int diagonalDownRight =
            findMaxSum(matrix, getDown(i), getRightColumn(j), visited, path); // 5
        tmpMax = Math.max(tmpMax, diagonalDownRight);
        result = matrix[i][j] + tmpMax;

      } else if (j == 0 && i == matrix.length - 1) {
        visited.put(getKey(i, j), true);

        path.add("- [i]" + i + "[j]= " + j+ " value="+matrix[i][j]);
        int right = findMaxSum(matrix, i, getRightColumn(j), visited, path);

        int up = findMaxSum(matrix, getUp(i), j, visited, path);

        int diagonalUpRight = findMaxSum(matrix, getUp(i), getRightColumn(j), visited, path);

        result = matrix[i][j] + Math.max(up, Math.max(diagonalUpRight, right));
      } else if (i == 0 && j == matrix[0].length - 1) {
        visited.put(getKey(i, j), true);

        path.add("- [i]" + i + "[j]= " + j+ " value="+matrix[i][j]);

        int down = findMaxSum(matrix, getDown(i), j, visited, path);

        int left = findMaxSum(matrix, i, getLeftColumn(j), visited, path);

        int diagonalLeft = findMaxSum(matrix, getDown(i), getLeftColumn(j), visited, path);

        result = matrix[i][j] + Math.max(left, Math.max(diagonalLeft, down));

      } else if ((i > 0 && i <= matrix.length - 2) && (j > 0 && j <= matrix[0].length - 2)) {
        visited.put(getKey(i, j), true);
        path.add("- [i]" + i + "[j]= " + j+ " value="+matrix[i][j]);

        int diagonalUpLeft = findMaxSum(matrix, getUp(i), getLeftColumn(j), visited, path); // 1
        int left = findMaxSum(matrix, i, getLeftColumn(j), visited, path); // 2

        int up = findMaxSum(matrix, getUp(i), j, visited, path); // 3
        int tmpMax = Math.max(diagonalUpLeft, Math.max(left, up));

        int diagonalUpRight = findMaxSum(matrix, getUp(i), getRightColumn(j), visited, path); // 4

        tmpMax = Math.max(tmpMax, diagonalUpRight);
        int right = findMaxSum(matrix, i, getRightColumn(j), visited, path); // 5

        tmpMax = Math.max(tmpMax, right);
        int down = findMaxSum(matrix, getDown(i), j, visited, path); // 6

        tmpMax = Math.max(tmpMax, down);
        int diagonalDownLeft = findMaxSum(matrix, getDown(i), getLeftColumn(j), visited, path);

        tmpMax = Math.max(tmpMax, diagonalDownLeft);
        int diagonalDownRight = findMaxSum(matrix, getDown(i), getRightColumn(j), visited, path);


        tmpMax = Math.max(tmpMax, diagonalDownRight);

        result = matrix[i][j] + tmpMax;

      } else if ((i == matrix.length - 1) && (j > 0 && j <= matrix[0].length - 2)) {

        visited.put(getKey(i, j), true);
        path.add("- [i]" + i + "[j]= " + j+ " value="+matrix[i][j]);

        int diagonalUpLeft = findMaxSum(matrix, getUp(i), getLeftColumn(j), visited, path); // 1
        int left = findMaxSum(matrix, i, getLeftColumn(j), visited, path); // 2

        int up = findMaxSum(matrix, getUp(i), j, visited, path); // 3

        int tmpMax = Math.max(diagonalUpLeft, Math.max(left, up));

        int diagonalUpRight = findMaxSum(matrix, getUp(i), getRightColumn(j), visited, path); // 4

        tmpMax = Math.max(tmpMax, diagonalUpRight);
        int right = findMaxSum(matrix, i, getRightColumn(j), visited, path); // 5
        tmpMax = Math.max(tmpMax, right);
        result = matrix[i][j] + tmpMax;

      } else if ((j == matrix[0].length - 1) && (i > 0 && i < matrix.length - 2)) {

        visited.put(getKey(i, j), true);

        path.add("- [i]" + i + "[j]= " + j + " value="+matrix[i][j]);
        int left = findMaxSum(matrix, i, getLeftColumn(j), visited, path); // 2

        int diagonalUpLeft = findMaxSum(matrix, getUp(i), getLeftColumn(j), visited, path); // 1

        int up = findMaxSum(matrix, getUp(i), j, visited, path); // 3

        int tmpMax = Math.max(diagonalUpLeft, Math.max(left, up));

        int diagonalDownLeft = findMaxSum(matrix, getDown(i), getLeftColumn(j), visited, path); // 4

        tmpMax = Math.max(tmpMax, diagonalDownLeft);

        int down = findMaxSum(matrix, getDown(i), j, visited, path); // 5

        tmpMax = Math.max(tmpMax, down);

        result = matrix[i][j] + tmpMax;


      }
    }
    return result;
  }

  private String getKey(int i, int j) {
    return new StringBuilder().append(i).append(j).toString();
  }

  private int getRightColumn(int column) {
    return column + 1;
  }

  private int getLeftColumn(int column) {
    return column - 1;
  }

  private int getUp(int row) {
    return row - 1;
  }

  private int getDown(int row) {
    return row + 1;
  }
}
