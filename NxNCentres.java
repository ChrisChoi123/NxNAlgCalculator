import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
/*
0 is yellow, 1 is blue, 2 is red, 3 is green, 4 is orange, 5 is white
*/
public class NxNCentres {
  public static int[] colours = new int[] {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
  public static Random rand = new Random();
  public static int[][] createCube(float n) {
    int index = 0;
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
  public static int solveCentres(int[] centres) {
    int targets = 0;
    int next = 0;
    int index = 0;
    int placeholder = 0;
    boolean isSolved = false;
    int solved = 0;
    while (!isSolved) {
      solved = 0;
      next = centres[0];

      if (next == 0) {
       index = 1;
       while (centres[index] == colours[index]) {
         index++;
         if (index == 24) break;
       }
       if (index < 4) {
         placeholder = centres[index];
         centres[index] = next;
         centres[0] = placeholder;
         targets++;
       }
       else next++;
      }
      if (next == 1) {
        index = 4;
        while (centres[index] == colours[index] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 8) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next++;
      }
      if (next == 2) {
        index = 8;
        while (centres[index] == colours[index] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 12) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next++;
      }
      if (next == 3) {
        index = 12;
        while (centres[index] == colours[index] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 16) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next++;
      }
      if (next == 4) {
        index = 16;
        while (centres[index] == colours[index] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 20) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next++;
      }
      if (next == 5) {
        index = 20;
        while (centres[index] == colours[index] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 24) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next++;
      }
      for (int i = 0;i < 24;i++) {
        if (colours[i] == centres[i]) {
          solved++;
        }
      }
      if (solved == 24 || solved == 23) isSolved = true;
    }
    return targets;
  }

  public static void optimalOrientation(int[][] cube) {
    
  }

  public static void main(String[] args){
    int[][] cube = createCube(5);
    int targets = 0;
    toString(cube);
    for (int i = 0; i < cube.length;i++) {
      targets = solveCentres(cube[i]);
      System.out.println(targets);
    }
  }
}
