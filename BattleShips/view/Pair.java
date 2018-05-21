/**
 * Pair Class: An object with two integer values.
 */

package view;

public class Pair {
  private int first;
  private int second;

  /**
   * Constructor:
   * 
   * @param x first integer value
   * @param y second integer value
   */
  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }


  /**
   * updateFirst:
   * 
   * Update the first value of the pair
   * 
   * @param x integer value
   */
  public void updateFirst(int x) {
    first = x;
  }


  /**
   * updateSecond:
   * 
   * Update the second value of the pair
   * 
   * @param y integer value
   */
  public void updateSecond(int y) {
    second = y;
  }


  /**
   * getFirst:
   * 
   * Returns the first value of the pair
   * 
   * @return the first value of the pair
   */
  public int getFirst() {
    return first;
  }


  /**
   * getSecond:
   * 
   * Returns the second value of the pair
   * 
   * @return the second value of the pair
   */
  public int getSecond() {
    return second;
  }

}
