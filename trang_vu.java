// Given a list of intervals [low, high) where low < high and len(intervals) >= 1, uniformly randomly generate a point from the union of these intervals.
// For example, given intervals [2,5], [1,3], method generateRandomValue produces a random value in [1, 2) with 20%, [2,3) with 40%, and [3,5) with 40%.

import java.util.*;

class trang_vu{
  public static void main(String[] args) {
    Interval[] intervals = {
      new Interval(1,3),
      new Interval(2,5),
      new Interval(1,4),
      new Interval(0,2)
    };

    int[] count = new int[5];
    int iterations = 100000;
    for (int i = 0; i < iterations; i++) {
      double f = generateRandomValue(intervals);
      for (int j = 1; j <= 5; j++)
      if (f >= j-1 && f < j) {
        count[j-1] ++;
      }
    }

    for (int j = 1; j <= 5; j++) {
      System.out.println("Interval [" + (j-1) + "," + j + ") with probability " + 100*(double)count[j-1]/iterations + " %");
    }

    // Interval [0,1) with probability ~10%
    // Interval [1,2) with probability ~30%
    // Interval [2,3) with probability ~30%
    // Interval [3,4) with probability ~20%
    // Interval [4,5) with probability ~10%
  }


  //utilize function
  static int arrange(double value, double arr[]) {
    int left = 0;
    int right = arr.length -1;
    while (left < right) {
      int mid = left + (right - left)/2;
      if (arr[mid] < value) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }

  // TO DO:
  // Time complexity 0(logn) : time for sorting/arranging
  // Space complexity 0(n) : in place
  public static double generateRandomValue(Interval[] intervals) {
    double[] weight = getWeight(intervals);
    Random rand = new Random();
    double num = rand.nextDouble() * weight[weight.length - 1];
    int index = arrange(num,weight);
    return intervals[index].low + (rand.nextDouble() * intervals[index].length());
  }

  static double[] getWeight(Interval[] intervals) {
    double[] weight = new double[intervals.length];
    weight[0] = intervals[0].length();
    for (int i = 1; i < intervals.length; i++) {
      weight[i] = weight[i-1] + intervals[i].length();
    }
    return weight;
  }

 
  static class Interval {
    double low;
    double high;

    public Interval(double low, double high) {
      this.low = low;
      this.high = high;
    }

    double length() {
      return high - low;
    }
  }

}