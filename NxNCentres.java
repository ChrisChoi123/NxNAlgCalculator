import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
/*
0 is yellow, 1 is blue, 2 is red, 3 is green, 4 is orange, 5 is white
*/
public class NxNCentres {
  public static Random rand = new Random();
  public static int[][] createCube(float n) {
    int[] colours = new int[24];
    int index = 0;
    for (int i = 0;i < 6;i++) {
      for (int j = 0;j < 4;j++) {
        colours[index++] = i;
      }
    }
    int[][] cube;
    if (n%2 != 0) {
      cube = new int[(int)Math.round(((n-3)/2)*((n-3)/2+1))][24]; //The expression in the 2nd bracket is the amount of centre orbits
    }
    else {
      cube = new int[(int)Math.round(((n-2)/2)*((n-2)/2))][24];
    }
    ArrayList<Integer> tempCol = new ArrayList<Integer>();
    for (int i = 0;i < cube.length;i++) {
      for (int j = 0; j < 24;j++) {
        tempCol.add(colours[j]);
      }
      index = 0;
      while (tempCol.size() > 0) {
        cube[i][index++] = tempCol.remove(rand.nextInt(tempCol.size()));
      }
    }
    return cube;
  }
  public static void toString(int[][] cube) {
    for (int i = 0;i < cube.length;i++) {
      for (int j = 0; j < cube[i].length;j++) {
        System.out.print(cube[i][j]+ " ");
      }
      System.out.print("\n");
    }
  }
  public static void main(String[] args){
    int[][] cube = createCube(5);
    toString(cube);
  }
}
