import org.junit.Before;
import org.junit.Test;

import listadt.ListADT;
import listadt.ListADTImpl;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class to test different operations in ListADT Interface.
 */
public class ListsTest {
  private ListADT<Integer> list1;
  private ListADT<Integer> list2;

  private ListADT<String> stringList1;
  private ListADT<String> stringList2;

  /**
   * Method to initialize test Objects of type ListADT that will be used for testing the
   * operations.
   */
  @Before
  public void setUp() {
    list1 = new ListADTImpl<>();
    list1.addBack(1);
    list1.addBack(2);
    list1.addBack(3);
    list1.addBack(4);
    list1.addBack(5);

    stringList1 = new ListADTImpl<>();
    stringList1.addBack("Hello");
    stringList1.addBack("World,");
    stringList1.addBack("This is");
    stringList1.addBack("a sample String List");
  }

  /**
   * Test Method to test that addBack method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void sampleTest2() {
    list2 = list1.toImmutable();
    list2.addBack(3);
  }

  /**
   * Test Method to test that addBack method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void sampleStringTest2() {
    stringList2 = stringList1.toImmutable();
    stringList2.addBack("I can add to this Immutable List");
  }

  /**
   * Test Method to test that addFront method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void sampleTest3() {
    list2 = list1.toImmutable();
    list2.addFront(7);
  }

  /**
   * Test Method to test that addFront method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void stringSampleTest3() {
    stringList2 = stringList1.toImmutable();
    stringList2.addFront("I can add to this Immutable List");
  }

  /**
   * Test Method to test that add method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void stringSampleTest4() {
    stringList2 = stringList1.toImmutable();
    stringList2.add(1, "I can add to this Immutable List");
  }

  /**
   * Test Method to test that add method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void sampleTest4() {
    list2 = list1.toImmutable();
    list2.add(1, 3);
  }

  /**
   * Test Method to test that remove method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void sampleTest5() {
    list2 = list1.toImmutable();
    list2.remove(4);
  }

  /**
   * Test Method to test that remove method to an Immutable List throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void stringSampleTest5() {
    stringList2 = stringList1.toImmutable();
    stringList2.remove("Hello");
  }

  /**
   * Test method to test constructor for ImmutableListADTImpl class. It takes an ListADT object as
   * parameter and constructs the immutable list from it.
   */
  @Test
  public void testImmutableList() {
    //Create ImmutableList from List.
    stringList2 = stringList1.toImmutable();
    list2 = list1.toImmutable();

    /**
     * Checking if the immutable List contains the same element as its mutable List counterpart.
     * This can be done by checking their toString methods return the same String.
     */
    assertEquals(stringList2.toString(), stringList1.toString());
    assertEquals(list2.toString(), list1.toString());

    /**
     * Checking it the immutable List contains the same number of elements as its mutable List
     * counterpart. This can be done by checking their getSize methods return the same count.
     */
    assertEquals(stringList2.getSize(), stringList1.getSize());
    assertEquals(list2.getSize(), list1.getSize());

    /**
     * Checking the immutable list constructed from the mutable list contains the same element as
     * the mutable element.
     */
    for (int i = 0; i < stringList1.getSize(); i++) {
      assertEquals(stringList2.get(i), stringList1.get(i));
    }

    /**
     * Checking the above methods don't return the same values for the two lists when adding
     * something into the mutable List counterpart.
     */
    stringList1.addBack("Signature");
    list1.addBack(7);
    assertNotEquals(stringList2.toString(), stringList1.toString());
    assertNotEquals(list2.toString(), list1.toString());

    assertNotEquals(stringList2.getSize(), stringList1.getSize());
    assertNotEquals(list2.getSize(), list1.getSize());

    /**
     * Getting elements at a given index for an immutable List.
     */
    String getIndex1 = stringList2.get(1);
    int getIndex = list2.get(1);
    assertEquals("World,", getIndex1);
    assertEquals(2, getIndex);

    /**
     * Getting string representation of the List.
     */
    String stringRepresentation = stringList2.toString();
    String stringRepresent = list2.toString();
    assertEquals(stringRepresentation, "(Hello World, This is a sample String List)");
    assertEquals(stringRepresent, "(1 2 3 4 5)");

    /**
     * Using map function to convert list of one type to another based on the function that it runs
     * on.
     */
    ListADT<Integer> wordLengths = stringList2.map(s -> s.length());
    assertEquals("(5 6 7 20)", wordLengths.toString());

    /**
     * Using toMutable function to convert an Immutable list to a mutable List.
     */
    ListADT<String> stringList3;
    stringList3 = stringList2.toMutable();

    /**
     * Check if the mutable List created in the last step can be mutated, that is state of the
     * can be changed.
     */
    stringList3.add(2, "Land");
    assertEquals("(Hello World, Land This is a sample String List)", stringList3.toString());
  }

  /**
   * Check if method that converts Immutable list to mutable list works as expected.
   */
  @Test
  public void checkImmutableToMutable() {
    stringList2 = stringList1.toImmutable();
    ListADT<String> stringList3;
    stringList3 = stringList2.toMutable();
    assertEquals(stringList3.getSize(), stringList2.getSize());
    for (int i = 0; i < stringList3.getSize(); i++) {
      assertEquals(stringList2.get(i), stringList3.get(i));
    }
    assertEquals(stringList3.getSize(), stringList2.getSize());
    assertEquals(stringList3.toString(), stringList2.toString());
  }

  /**
   * Check if method that converts Mutable list to Immutable list works as expected and that the new
   * list is indeed Immutable.
   */
  @Test
  public void checkMutableToImmutable() {
    stringList2 = stringList1.toImmutable();
    assertEquals(stringList2.getSize(), stringList1.getSize());
    for (int i = 0; i < stringList2.getSize(); i++) {
      assertEquals(stringList2.get(i), stringList1.get(i));
    }
    assertEquals(stringList2.getSize(), stringList1.getSize());
    assertEquals(stringList2.toString(), stringList1.toString());
    try {
      stringList2.addBack("4");
    } catch (UnsupportedOperationException e) {
      assertEquals(4, stringList2.getSize());
      assertEquals("(Hello World, This is a sample String List)", stringList2.toString());
    }

    try {
      stringList2.addFront("4");
    } catch (UnsupportedOperationException e) {
      assertEquals(4, stringList2.getSize());
      assertEquals("(Hello World, This is a sample String List)", stringList2.toString());
    }

    try {
      stringList2.add(3, "4");
    } catch (UnsupportedOperationException e) {
      assertEquals(4, stringList2.getSize());
      assertEquals("(Hello World, This is a sample String List)", stringList2.toString());
    }

    try {
      stringList2.remove("Hello");
    } catch (UnsupportedOperationException e) {
      assertEquals(4, stringList2.getSize());
      assertEquals("(Hello World, This is a sample String List)", stringList2.toString());
    }
  }

  /**
   * Check get method throws exception for invalid value of index passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidIndexForGet1() {
    stringList2 = stringList1.toImmutable();
    stringList2.get(-1);
  }

  /**
   * Check get method throws exception for invalid value of index passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkInvalidIndexForGet2() {
    stringList2 = stringList1.toImmutable();
    stringList2.get(7);
  }

  /**
   * Check new Immutable Object is created when toImmutable method is called from within an
   * immutable List.
   */
  @Test
  public void checkImmutableToImmutable() {
    stringList2 = stringList1.toImmutable();
    ListADT<String> stringList3 = stringList2.toImmutable();
    assertEquals(stringList3.toString(), stringList2.toString());
    assertNotEquals(stringList3, stringList2);
  }

  /**
   * Check immutability is maintained even when mutable list of the immutable list is changed.
   */
  @Test
  public void testImmutability() {
    stringList2 = stringList1.toImmutable();
    assertEquals(stringList2.toString(), stringList1.toString());
    stringList1.remove("Hello");
    assertNotEquals(stringList2.toString(), stringList1.toString());
  }

  /**
   * Check immutability is maintained even when mutable list of the immutable list is changed.
   */
  @Test
  public void testImmutability2() {
    stringList2 = stringList1.toImmutable();
    ListADT<String> stringList3 = stringList2.toMutable();
    assertEquals(stringList3.toString(), stringList2.toString());
    stringList3.remove("Hello");
    assertNotEquals(stringList3.toString(), stringList2.toString());
  }

  /**
   * Check immutability is maintained even when mutable list of the immutable list is changed.
   */
  @Test
  public void testImmutability3() {
    stringList2 = stringList1.toImmutable();
    ListADT<String> stringList3 = stringList2.toMutable();
    assertEquals(stringList3.toString(), stringList2.toString());
    stringList3.addBack("Hello");
    assertNotEquals(stringList3.toString(), stringList2.toString());
  }

  /**
   * Check immutability is maintained even when mutable list of the immutable list is changed.
   */
  @Test
  public void testImmutability4() {
    stringList2 = stringList1.toImmutable();
    ListADT<String> stringList3 = stringList2.toMutable();
    assertEquals(stringList3.toString(), stringList2.toString());
    stringList3.addFront("Hello");
    assertNotEquals(stringList3.toString(), stringList2.toString());
  }

  /**
   * Check immutability is maintained even when mutable list of the immutable list is changed.
   */
  @Test
  public void testImmutability5() {
    stringList2 = stringList1.toImmutable();
    ListADT<String> stringList3 = stringList2.toMutable();
    assertEquals(stringList3.toString(), stringList2.toString());
    stringList3.add(2, "Hello");
    assertNotEquals(stringList3.toString(), stringList2.toString());
  }

  /**
   * Check Map function of the Immutable List works as expected.
   */
  @Test
  public void testMap() {
    // convert the list of strings above to a list that
    // contains the length of each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String[] words = sentence.split("\\s+");
    for (String w : words) {
      stringList1.addBack(w);
    }

    stringList2 = stringList1.toImmutable();
    ListADT<Integer> wordLengths = stringList2.map(s -> s.length());
    assertEquals(stringList2.getSize(),
            wordLengths.getSize());

    for (int i = 0; i < words.length; i++) {
      assertEquals(words[0].length() + 2,
              (int) wordLengths.get(0));
    }
  }

  /**
   * Check Map function of the Immutable List works as expected and that the new List is indeed
   * Immutable.
   */
  @Test
  public void testMap2() {
    // convert the list of strings above to a list that
    // contains the length of each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String[] words = sentence.split("\\s+");
    for (String w : words) {
      stringList1.addBack(w);
    }

    stringList2 = stringList1.toImmutable();

    ListADT<Integer> wordLengths = stringList2.map(s -> s.length());

    try {
      wordLengths.addBack(6);
    } catch (UnsupportedOperationException e) {
      assertEquals(13, wordLengths.getSize());
      assertEquals("(5 6 7 20 3 5 5 3 5 4 3 4 3)", wordLengths.toString());
    }

    try {
      wordLengths.addFront(6);
    } catch (UnsupportedOperationException e) {
      assertEquals(13, wordLengths.getSize());
      assertEquals("(5 6 7 20 3 5 5 3 5 4 3 4 3)", wordLengths.toString());
    }

    try {
      wordLengths.add(1, 6);
    } catch (UnsupportedOperationException e) {
      assertEquals(13, wordLengths.getSize());
      assertEquals("(5 6 7 20 3 5 5 3 5 4 3 4 3)", wordLengths.toString());
    }

    try {
      wordLengths.remove(6);
    } catch (UnsupportedOperationException e) {
      assertEquals(13, wordLengths.getSize());
      assertEquals("(5 6 7 20 3 5 5 3 5 4 3 4 3)", wordLengths.toString());
    }

  }

  /**
   * Check Immutable List for empty List.
   */
  @Test
  public void testEmptyList() {
    ListADT<String> emptyMutableList = new ListADTImpl<>();
    ListADT<String> emptyImmutableList = emptyMutableList.toImmutable();
    assertEquals("()", emptyImmutableList.toString());
    assertEquals(0, emptyImmutableList.getSize());
  }
}
