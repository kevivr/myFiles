import org.junit.Before;
import org.junit.Test;

import decoder.Decoder;
import decoder.DecoderImpl;

import static org.junit.Assert.assertEquals;

public class DecoderImplTest {
  Decoder sample;
  Decoder hexSample;
  Decoder sample2;
  Decoder hexSample2;
  Decoder sample3;
  Decoder sample4;
  Decoder sample5;

  /**
   * Construct sample Object to test DecodeImpl Class.
   */
  @Before
  public void setUp() {
    String hex = "0123456789abcdef";
    sample = new DecoderImpl("10");
    hexSample = new DecoderImpl(hex);

    sample.addCode('a', "100");
    sample.addCode('b', "00");
    sample.addCode('c', "01");
    sample.addCode('d', "11");
    sample.addCode('e', "101");

    for (int i = 0; i < hex.length(); i++) {
      hexSample.addCode(hex.charAt(i), hex.substring(i, i + 1));
    }
  }

  /**
   * Test method to test if add code method works as expected when passing valid arguments.
   */
  @Test
  public void testAddCode() {

    sample2 = new DecoderImpl("01");
    sample3 = new DecoderImpl("01");
    sample5 = new DecoderImpl("01");
    hexSample2 = new DecoderImpl("0123456789abcdef");
    sample2.addCode('a', "00");
    sample2.addCode('b', "0110");
    sample2.addCode('c', "100");

    sample3.addCode('a', "0");
    sample3.addCode('b', "1");

    sample5.addCode('a', "00");
    sample5.addCode('b', "01");
    sample5.addCode('c', "10");

    hexSample2.addCode('a', "0100101");
    hexSample2.addCode('b', "abcdef");

    assertEquals("a:00\n" +
            "b:0110\n" +
            "c:100\n", sample2.allCodes());

    assertEquals("a:0\n" +
            "b:1\n", sample3.allCodes());

    assertEquals("a:00\n" +
            "b:01\n" +
            "c:10\n", sample5.allCodes());

    assertEquals("a:0100101\n" +
            "b:abcdef\n", hexSample2.allCodes());
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void testIncorrectAddCode1() {
    sample.addCode('f', "0");
  }

  @Test(expected = IllegalStateException.class)
  public void testIncorrectAddCode11() {
    sample2 = new DecoderImpl("01");
    sample2.addCode(null, "0");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void testIncorrectAddCode2() {
    sample.addCode('f', "010");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void testIncorrectAddCode3() {
    sample.addCode('f', "00a");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void testIncorrectAddCode4() {
    sample.addCode('f', null);
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void testIncorrectAddCode5() {
    sample.addCode('f', "");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void hexTestIncorrectAddCode1() {
    sample.addCode('q', "0");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void hexTestIncorrectAddCode2() {
    sample.addCode('q', "01");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void hexTestIncorrectAddCode3() {
    sample.addCode('q', "g");
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void hexTestIncorrectAddCode4() {
    sample.addCode('q', null);
  }

  /**
   * Test exception thrown when attempting to do an incorrect addCode.
   */
  @Test(expected = IllegalStateException.class)
  public void hexTestIncorrectAddCode5() {
    sample.addCode('q', "");
  }

  /**
   * Test if constructor of DecoderImpl works correctly.
   */
  @Test
  public void testDecoderImplConstructor() {
    sample2 = new DecoderImpl("asdf");
    assertEquals("", sample2.allCodes());
  }

  /**
   * Test if constructor of DecoderImpl works correctly.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDecoderImplConstructorFail() {
    sample2 = new DecoderImpl(null);
    assertEquals("", sample2.allCodes());
  }

  /**
   * Test if constructor of DecoderImpl works correctly.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDecoderImplConstructorFail2() {
    sample2 = new DecoderImpl("");
    assertEquals("", sample2.allCodes());
  }

  /**
   * Test if Decode method works correctly.
   */
  @Test
  public void testDecodeMethod() {
    assertEquals("adecb", sample.decode("100111010100"));
  }

  /**
   * Test if Decode method works correctly.
   */
  @Test
  public void testDecodeMethod2() {
    assertEquals("0123456789abcdef", hexSample.decode("0123456789abcdef"));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail() {
    assertEquals("q", hexSample.decode("q"));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail2() {
    assertEquals("000", sample.decode("000"));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail4() {
    assertEquals("000", sample.decode(null));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail5() {
    assertEquals("000", sample.decode(""));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail6() {
    assertEquals("000", hexSample.decode(""));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail8() {
    assertEquals("000", sample.decode("000110010111g"));
  }

  /**
   * Test if Decode method throws exception wherever required.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeMethodFail9() {
    assertEquals("000", sample.decode("000110010"));
  }

  /**
   * Test if AllCodes method works correctly.
   */
  @Test
  public void testAllCodesMethod() {
    assertEquals("a:100\n" +
            "b:00\n" +
            "c:01\n" +
            "d:11\n" +
            "e:101\n", sample.allCodes());
  }

  /**
   * Test if AllCodes method works correctly.
   */
  @Test
  public void testAllCodesMethod2() {
    assertEquals("0:0\n" +
            "1:1\n" +
            "2:2\n" +
            "3:3\n" +
            "4:4\n" +
            "5:5\n" +
            "6:6\n" +
            "7:7\n" +
            "8:8\n" +
            "9:9\n" +
            "a:a\n" +
            "b:b\n" +
            "c:c\n" +
            "d:d\n" +
            "e:e\n" +
            "f:f\n", hexSample.allCodes());
  }

  /**
   * Test if AllCodes method works correctly.
   */
  @Test
  public void testAllCodesMethod3() {
    sample2 = new DecoderImpl("01");
    sample2.addCode('a', "0111");
    assertEquals("a:0111\n", sample2.allCodes());
    sample2.addCode('b', "0110");
    assertEquals("a:0111\nb:0110\n", sample2.allCodes());
    sample2.addCode('c', "0100");
    assertEquals("a:0111\nb:0110\nc:0100\n", sample2.allCodes());
  }

  /**
   * Test if isComplete method works correctly.
   */
  @Test
  public void testIsCompleteMethod() {
    assertEquals(true, sample.isCodeComplete());
    assertEquals(true, hexSample.isCodeComplete());
  }

  /**
   * Test if isComplete method returns false for an incomplete tree.
   */
  @Test
  public void testIsCompleteMethod2() {
    sample4 = new DecoderImpl(".-");
    sample4.addCode('4', "...-");
    sample4.addCode('6', ".-.-");
    assertEquals(false, sample4.isCodeComplete());
  }

  /**
   * Test if isComplete method returns false for an incomplete tree.
   */
  @Test
  public void testIsCompleteMethod3() {
    sample5 = new DecoderImpl(".-");
    sample5.addCode('1', ".");
    assertEquals(false, sample5.isCodeComplete());
    sample2 = new DecoderImpl(".-");
    sample2.addCode('2', ".");
    sample2.addCode('3', "-");
    assertEquals(true, sample2.isCodeComplete());
  }

  /**
   * Test miscellaneous operations on a DecoderImplObject.
   */
  @Test
  public void testMiscellaneousOperations() {
    sample5 = new DecoderImpl("1234567890");
    sample5.addCode('a', "01");
    sample5.addCode('b', "02");
    sample5.addCode('c', "03");
    sample5.addCode('d', "04");
    assertEquals("abcd", sample5.decode("01020304"));
    assertEquals(false, sample5.isCodeComplete());
    sample5.addCode('e', "05");
    sample5.addCode('f', "06");
    sample5.addCode('g', "07");
    sample5.addCode('h', "08");
    assertEquals("a:01\n" +
            "b:02\n" +
            "c:03\n" +
            "d:04\n" +
            "e:05\n" +
            "f:06\n" +
            "g:07\n" +
            "h:08\n", sample5.allCodes());
    sample5.addCode('i', "09");
    sample5.addCode('j', "00");
    assertEquals(false, sample5.isCodeComplete());
  }
}