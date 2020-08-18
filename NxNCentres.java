import java.lang.Math;
import java.util.*;
/*
0 is U, 1 is L, 2 is F, 3 is R, 4 is B, 5 is D
*/
public class NxNCentres {
  public static int[][] orientations = new int[][]
  {
    {0, 1, 2, 3, 4, 5} , {1, 5, 2, 0, 4, 3} , {5, 3, 2, 1, 4, 0} , {3, 0, 2, 5, 4, 1} ,
    {4, 1, 0, 3, 5, 2} , {1, 2, 0, 4, 5, 3} , {2, 3, 0, 1, 5, 4} , {3, 4, 0, 2, 5, 1} ,
    {0, 4, 1, 2, 3, 5} , {4, 5, 1, 0, 3, 2} , {5, 2, 1, 4, 3, 0} , {2, 0, 1, 5, 3, 4} ,
    {0, 2, 3, 4, 1, 5} , {2, 5, 3, 0, 1, 4} , {5, 4, 3, 2, 1, 0} , {4, 0, 3, 5, 1, 2} ,
    {0, 3, 4, 1, 2, 5} , {3, 5, 4, 0, 2, 1} , {5, 1, 4, 3, 2, 0} , {1, 0, 4, 5, 2, 3} ,
    {2, 1, 5, 3, 0, 4} , {1, 4, 5, 2, 0, 3} , {4, 3, 5, 1, 0, 2} , {3, 2, 5, 4, 0, 1} // this covers all of the valid orientations
   };
  public static Random rand = new Random();
  public static void createCube(int[][] cube) {
    int index = 0;
    ArrayList<Integer> tempCol = new ArrayList<Integer>();
    for (int i = 0;i < cube.length;i++) {
      for (int j = 0; j < 24;j++) {
        tempCol.add(orientations[0][j/4]);
      }
      index = 0;
      while (tempCol.size() > 0) {
        cube[i][index++] = tempCol.remove(rand.nextInt(tempCol.size()));
      }
    }
  }
  public static void toString(int[][] cube) {
    for (int i = 0;i < cube.length;i++) {
      for (int j = 0; j < cube[i].length;j++) {
        System.out.print(cube[i][j]+ " ");
      }
      System.out.print("\n");
    }
  }
  public static int solveCentres(int[] centres, int idx) {
    int targets = 0;
    int next = 0;
    int index = 0;
    int placeholder = 0;
    boolean isSolved = false;
    int solved = 0;
    while (!isSolved) {
      solved = 0;
      next = centres[0];
      if (next == orientations[idx][0]) {
       index = 1;
       while (centres[index] == orientations[idx][index/4]) {
         index++;
         if (index == 24) break;
       }
       if (index < 4) {
         placeholder = centres[index];
         centres[index] = next;
         centres[0] = placeholder;
         targets++;
       }
       else next = orientations[idx][1];
      }
      if (next == orientations[idx][1]) {
        index = 4;
        while (centres[index] == orientations[idx][index/4] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 8) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next = orientations[idx][2];
      }
      if (next == orientations[idx][2]) {
        index = 8;
        while (centres[index] == orientations[idx][index/4] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 12) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next = orientations[idx][3];
      }
      if (next == orientations[idx][3]) {
        index = 12;
        while (centres[index] == orientations[idx][index/4] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 16) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next = orientations[idx][4];
      }
      if (next == orientations[idx][4]) {
        index = 16;
        while (centres[index] == orientations[idx][index/4] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 20) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next = orientations[idx][5];
      }
      if (next == orientations[idx][5]) {
        index = 20;
        while (centres[index] == orientations[idx][index/4] && !(centres[index] == 0 && centres[1] == 0 && centres[2] == 0 && centres[3] == 0)) {
          index++;
          if (index == 24) break;
        }
        if (index < 24) {
          placeholder = centres[index];
          centres[index] = next;
          centres[0] = placeholder;
          targets++;
        }
        else next = 6;
      }
      for (int i = 0;i < 24;i++) {
        if (orientations[idx][i/4] == centres[i]) {
          solved++;
        }
      }
      if (solved > 22) isSolved = true;
    }
    return targets;
  }

  public static int optimalOrientation(int[][] cube) {
    int[] solved = new int[24];
    for (int i = 0;i < 24;i++) {
      for (int j = 0;j < cube.length;j++) {
        for (int k = 0;k < 24;k++) {
          if (cube[j][k] == orientations[i][k/4]) {
            solved[i]+=1;
          }
        }
      }
    }
    int max = solved[0];
    int idx = 0;
    for (int i = 1; i < solved.length; i++)
    if (solved[i] > max) {
      max = solved[i];
      idx = i;
    }
    return idx;
  }

  public static void main(String[] args){
    int n;
    Scanner scanner = new Scanner(System.in);
    System.out.println("You may enter 0 to exit this program\nPlease enter in an NxN layer amount larger than 3: ");
    while((n = scanner.nextInt()) != 0) {
        double mean = 0;
        int[][] cube;
        int orbits = 0;
        if (n%2 != 0) {
          orbits = (int)Math.round(((n-3)/2)*((n-3)/2+1));
        }
        else {
          orbits = (int)Math.round(((n-2)/2)*((n-2)/2));
        }
        cube = new int[orbits][24];
        for (int i = 0;i < Math.ceil(100000/orbits);i++){
          createCube(cube);
          int idx = 0;
          if (n%2 == 0) idx = optimalOrientation(cube);
          double average = 0;
          int targets = 0;
          for (int j = 0; j < cube.length;j++) {
            targets = solveCentres(cube[j],idx);
            average += targets;
          }
          mean += (average/(double)cube.length);
        }
        System.out.println("Number of Targets: "+ mean/(double)(Math.ceil(100000/orbits)));
        System.out.println("Try another one: ");
    }
    {
        System.exit(1);
    }
  }
}
