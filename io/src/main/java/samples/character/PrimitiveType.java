package samples.character;

/**
 * Java中的基础类型有：byte、short、int、long、float、double、char和boolean。
 *
 * <p>它们可被分为四种类型，整型、浮点型、char型和boolean型，即四类八种。
 *
 * <p>整型：byte、short、int、long 分别占用1、2、4、8个字节的空间。
 *
 * <p>浮点型：float,double 分别占用4、8个字节。
 *
 * <p>char型：char 占用2个字节。
 *
 * <p>boolean型：boolean 占用1位。
 *
 * @author wangyongtao
 * @date 2012-7-8 00:47:00
 */
public class PrimitiveType {

  public static void main(String[] args) {

    /** java中整型数计算采用补码，可以通过十六进制或者八进制形式对整型数直接赋值，java中不能直接使用二进制数 */

    // byte
    byte maxByte = 0x7f;
    System.out.println("最大byte：0x7f  真值：" + maxByte);
    byte minByte = (byte) 0x80;
    System.out.println("最小byte：0x80  真值：" + minByte);

    // short
    short maxShort = 0x7fff;
    System.out.println("最大short：0x7fff  真值：" + maxShort);
    short minShort = (short) 0x8000;
    System.out.println("最小short：0x8000  真值：" + minShort);

    // int
    int maxInt = 0x7fffffff;
    System.out.println("最大int：0x7fffffff  真值：" + maxInt);
    int minInt = 0x80000000;
    System.out.println("最小int：0x80000000  真值：" + minInt);

    // long
    System.out.println("最大long：0x7fffffffffffffff  真值：" + Long.decode("0x7fffffffffffffff"));
    // 算是一个bug吧，补码表示已经是带符号的数字了
    System.out.println("最小long：0x8000000000000000  真值：" + Long.decode("-0x8000000000000000"));

    // java中对小数直接量默认为double类型，定义float型需要在小数后面加上字母F，大小写不限
    @SuppressWarnings("unused")
    float f = 0.1F;
    // 定义double，在小数后面也可以加D，大小写不限
    @SuppressWarnings("unused")
    double d = 0.1D;

    // ---------------------Java中浮点数计算都遵循IEEE754规范-------------------------//
    // 与整型数不同，浮点数在java中无法直接通过十六进制或者八进制形式定义
    // 最大正数(0 11111110 11111111111111111111111)=(0x7f7fffff)
    float maxPositiveNumber = Float.intBitsToFloat(0x7f7fffff);
    System.out.println("最大正单精度浮点数：0x7f7fffff  真值：" + maxPositiveNumber);
    // 最小正数(0 00000001 00000000000000000000000)=(0x00800000)
    float minPositiveNumber = Float.intBitsToFloat(0x00800000);
    System.out.println("最小正单精度浮点数：0x00800000  真值：" + minPositiveNumber);
    // 0，IEEE754 规定 E=0 M=0,则值为0,(0 00000000 00000000000000000000000)=(0x00000000)
    float positoveZero = Float.intBitsToFloat(0x00000000);
    System.out.println("正零：" + positoveZero);
    float negativeZero = Float.intBitsToFloat(0x80000000);
    System.out.println("负零：" + negativeZero);
    // 最大负数(1 00000001 00000000000000000000000)=(0x80800000)
    float maxNegativeNumber = Float.intBitsToFloat(0x80800000);
    System.out.println("最大负单精度浮点数：0x80800000  真值：" + maxNegativeNumber);
    // 最小负数(1 11111110 11111111111111111111111)=(0xff7fffff)
    float minNegativeNumber = Float.intBitsToFloat(0xff7fffff);
    System.out.println("最小负单精度浮点数：0xff7fffff  真值：" + minNegativeNumber);
    // NaN，非数值，JDK API doc 上面也有提到
    System.out.println("NaN：E=255 M<>0  例如：0x3f800001  运行结果：" + Float.intBitsToFloat(0x7f800001));
    // POSITIVE_INFINITY，正无穷大
    System.out.println(
        "POSITIVE_INFINITY：S=0 E=255 M=0  0x7f800000  运行结果：" + Float.intBitsToFloat(0x7f800000));
    // NEGATIVE_INFINITY，负无穷大
    System.out.println(
        "NEGATIVE_INFINITY：S=0 E=255 M=0  0xff800000  运行结果：" + Float.intBitsToFloat(0xff800000));
    // 双精度浮点数同理可得

    // 下面对char型进行讨论
    char[] codeUnits;
    // 基本多语言级别 basic multilingual plane
    // ASC2 code
    for (int i = 0; i < 128; i++) {
      codeUnits = Character.toChars(i);
      if (codeUnits.length == 1) {
        System.out.println(i + " " + (char) i);
      }
    }
    // 汉字 '严'
    char yan = '\u4e25';
    codeUnits = Character.toChars(0x4e25);
    System.out.printf(
        "汉字：" + yan + "  代码点所占代码单元长度" + codeUnits.length + " (0x%x) ,它属于基本多语言级别。\n",
        (int) codeUnits[0]);
    // 辅助字符 supplementary character
    // 代码点0x1d56b
    codeUnits = Character.toChars(0x1d56b);
    // 判断代码单元的高低位
    System.out.printf(
        "代码点0x%x 在UTF-16表示中被分解为两个代码单元 0x%x 0x%x \n",
        0x105600, (int) codeUnits[0], (int) codeUnits[1]);
    System.out.printf(
        "0x%x is HighSurrogate:" + Character.isHighSurrogate(codeUnits[0]) + "\n",
        (int) codeUnits[0]);
    System.out.printf(
        "0x%x is HighSurrogate:" + Character.isHighSurrogate(codeUnits[1]) + "\n",
        (int) codeUnits[1]);
    System.out.printf(
        "0x%x is LowSurrogate:" + Character.isLowSurrogate(codeUnits[0]) + "\n",
        (int) codeUnits[0]);
    System.out.printf(
        "0x%x is LowSurrogate:" + Character.isLowSurrogate(codeUnits[1]) + "\n",
        (int) codeUnits[1]);
  }
}
