/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8340454
 * @summary Check that Reduce Allocation Merges doesn't crash when
 *          a reducible Phi becomes irreducible after the last of
 *          its SR inputs is flagged as NSR.
 * @run main/othervm -XX:CompileCommand=compileonly,*TestReduceAllocationAndSetTypeTwice*::*
 *                   -Xcomp
 *                   compiler.escapeAnalysis.TestReduceAllocationAndSetTypeTwice
 *
 * @run main compiler.escapeAnalysis.TestReduceAllocationAndSetTypeTwice
 */

package compiler.escapeAnalysis;

import java.util.Random;
import java.util.Arrays;
import java.nio.charset.Charset;

class FuzzUtils {
 public static Random random = new Random(1);
 public static long seed = 4L;
 public static int UnknownZero = 0;
 public static void init(int[] a, int seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(short[] a, short seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = (short)(((j % 2 == 0) ? seed + j : seed + (-1*j)));
   }
 }
 public static void init(byte[] a, byte seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = (byte)(((j % 2 == 0) ? seed + j : seed + (-1*j)));
   }
 }
 public static void init(long[] a, long seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(float[] a, float seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(double[] a, double seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(Integer[] a, Integer seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(Short[] a, Short seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = Short.valueOf((short)((j % 2 == 0) ? seed + j : seed + (-1*j)));
   }
 }
 public static void init(Byte[] a, Byte seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = Byte.valueOf((byte)((j % 2 == 0) ? seed + j : seed + (-1*j)));
   }
 }
 public static void init(Long[] a, Long seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(Float[] a, Float seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(Double[] a, Double seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
 public static void init(String[] a, String seed) {
   for (int j = 0; j < a.length; j++) {
        a[j] = ((j % 2 == 0) ? seed + j : seed + (-1*j));
   }
 }
public static long checksumCollectionVarsMethodint(  int[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  valuesList[i][j];
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodshort(  short[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  valuesList[i][j];
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodbyte(  byte[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  valuesList[i][j];
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodlong(  long[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  valuesList[i][j];
    }
 }
 return sum;

 }
public static double checksumCollectionVarsMethodfloat(  float[][] valuesList) {
double sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  ( valuesList[i][j]);
    }
 }
 return sum;

 }
public static double checksumCollectionVarsMethoddouble(  double[][] valuesList) {
double sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  ( valuesList[i][j]);
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodInteger(  Integer[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum =  (sum + valuesList[i][j]);
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodShort(  Short[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum =  (sum + valuesList[i][j]);
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodByte(  Byte[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum =  (sum + valuesList[i][j]);
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodLong(  Long[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum =  (sum + valuesList[i][j]);
    }
 }
 return sum;

 }
public static double checksumCollectionVarsMethodFloat(  Float[][] valuesList) {
double sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum =  (sum + valuesList[i][j].doubleValue());
    }
 }
 return sum;

 }
public static double checksumCollectionVarsMethodDouble(  Double[][] valuesList) {
double sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum =  (sum + valuesList[i][j].doubleValue());
    }
 }
 return sum;

 }
public static long checksumCollectionVarsMethodString(  String[][] valuesList) {
long sum = 0;
 for (int i = 0; i < valuesList.length; i++) {
 for (int j = 0; j < valuesList[i].length; j++)  {
 sum +=  valuesList[i][j].length();
    }
 }
 return sum;

 }
public static void printVarsMethod ( String idStr, int[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, short[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, byte[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, long[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, float[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, double[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, Integer[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, Short[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, Byte[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, Long[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, Float[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, Double[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printVarsMethod ( String idStr, String[] valuesList ) {
String[] idsArray = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsArray[currIndex]).append(" ");
sbValue.append(valuesList[currIndex]).append(" ");
}
sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_int ( String idStr, int[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodint(new int[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_short ( String idStr, short[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodshort(new short[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_byte ( String idStr, byte[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodbyte(new byte[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_long ( String idStr, long[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodlong(new long[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_float ( String idStr, float[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodfloat(new float[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_double ( String idStr, double[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethoddouble(new double[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_Integer ( String idStr, Integer[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodInteger(new Integer[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_Short ( String idStr, Short[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodShort(new Short[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_Byte ( String idStr, Byte[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodByte(new Byte[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_Long ( String idStr, Long[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodLong(new Long[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_Float ( String idStr, Float[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodFloat(new Float[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_Double ( String idStr, Double[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodDouble(new Double[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
public static void printCollectionVarsMethod_String ( String idStr, String[][] valuesList ) {
String[] idsCollection = idStr.split(",");
StringBuilder sbId = new StringBuilder();
StringBuilder sbValue = new StringBuilder();
for (int currIndex = 0; currIndex < valuesList.length; currIndex++) {
  if (currIndex % 3 == 0 && currIndex != 0) {
    sbId.append("= ");
System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();
}
sbId.append(idsCollection[currIndex]).append(" ");
          sbValue.append(checksumCollectionVarsMethodString(new String[][]{valuesList[currIndex]})).append(" ");
 }
sbId.append("= ");
 System.out.println(sbId.toString() + sbValue.toString());
sbId = new StringBuilder();
sbValue = new StringBuilder();

 }
 }
 class AnotherClass {
     public int field;
     public void meth() {field = 1;}
 }
 interface LambdaInterfaceTwoArgsLong_0 {
   Long lambdaFunctionTwoArgsLong(Long lambdaValLong_1, Long lambdaValLong_2);
}
 interface LambdaInterfaceTwoArgsDouble_3 {
   Double lambdaFunctionTwoArgsDouble(Double lambdaValDouble_4, Double lambdaValDouble_5);
}
 interface LambdaInterfaceOneArgdouble_6 {
   double lambdaFunctionOneArgdouble(double lambdaValdouble_7);
}
 interface LambdaInterfaceOneArgString_8 {
   String lambdaFunctionOneArgString(String lambdaValString_9);
}
 interface LambdaInterfaceOneArgLong_10 {
   Long lambdaFunctionOneArgLong(Long lambdaValLong_11);
}
 interface LambdaInterfaceOneArgFloat_12 {
   Float lambdaFunctionOneArgFloat(Float lambdaValFloat_13);
}
 interface LambdaInterfaceTwoArgsShort_14 {
   Short lambdaFunctionTwoArgsShort(Short lambdaValShort_15, Short lambdaValShort_16);
}
 interface LambdaInterfaceTwoArgslong_17 {
   long lambdaFunctionTwoArgslong(long lambdaVallong_18, long lambdaVallong_19);
}
// Test class
//Test_20

public class TestReduceAllocationAndSetTypeTwice {
  static int intArrayFieldVar_21[] = new int[500];
  static {
    FuzzUtils.init(intArrayFieldVar_21, 19854);
  }

  // Test method
  public static double method_double_double_22(double doubleVal_23) {
    long longVar_24 = 27453L;

    byte byteArrayVar_25[] = new byte[500];
    FuzzUtils.init(byteArrayVar_25, (byte) (-46));

    long longArrayVar_26[] = new long[500];
    FuzzUtils.init(longArrayVar_26, longVar_24);

    Float FloatVar_27 = -0.44536133454937565F;

    short shortVar_28 = 15;

    String StringlongNumericVar_30 = "9223372036854775805L";

    int intVar_31 = -68;

    int intVar_32 = 2147483642;

    String StringlongNumericVar_33 = "21086L";

    String StringlongNumericVar_34 = "-47L";

    double doubleArrayVar_35[] = new double[500];
    FuzzUtils.init(doubleArrayVar_35, doubleVal_23);

    String StringintNumericVar_36 = "18540";

    Short ShortArrayVar_38[] = new Short[500];
    FuzzUtils.init(ShortArrayVar_38, Short.valueOf((short) -127));

    double doubleArrayVar_39[] = new double[500];
    FuzzUtils.init(doubleArrayVar_39, doubleVal_23);

    String StringVar_41 = new String(byteArrayVar_25, 41, 64, Charset.forName("KOI8-U"));

    double doubleArrayVar_42[] = new double[500];
    FuzzUtils.init(doubleArrayVar_42, doubleVal_23);

    float floatVar_43 = 748.4190455726423F;

    String StringVar_44 = new String(byteArrayVar_25, 86, 116, Charset.forName("IBM862"));

    boolean booleanVar_45 = true;

    String StringVar_46 = new String(byteArrayVar_25, 97, 9, Charset.forName("KOI8-R"));

    String StringVar_47 = new String(byteArrayVar_25, 47, 82, Charset.forName("windows-1252"));

    LambdaInterfaceOneArgFloat_12 lambdaFunctionOneArgFloat_48 = (
        Float lambdaValFloat_49) -> ((Float) (float) ((lambdaValFloat_49
            + (-39.63081340365506F - 34.55991323173387F))));

    String StringVar_50 = new String(byteArrayVar_25, 112, 118, Charset.forName("windows-1251"));

    double doubleArrayVar_51[] = new double[500];
    FuzzUtils.init(doubleArrayVar_51, doubleVal_23);

    LambdaInterfaceTwoArgslong_17 lambdaFunctionTwoArgslong_52 = (long lambdaVallong_53,
        long lambdaVallong_54) -> ((long) ((lambdaVallong_53 - (lambdaVallong_53 + lambdaVallong_54))));

    String StringVar_55 = new String(byteArrayVar_25, 65, 47, Charset.forName("KOI8-R"));

    long longArrayVar_56[] = new long[500];
    FuzzUtils.init(longArrayVar_56, longVar_24);

    double doubleVar_58 = 68.26996311281277D;

    int intArrayVar_59[] = new int[500];
    FuzzUtils.init(intArrayVar_59, intVar_31);

    final String StringshortNumericVal_60 = "125";

    byte byteArrayVar_61[] = new byte[500];
    FuzzUtils.init(byteArrayVar_61, (byte) (-128));

    int intVar_63 = -18;

    String StringFloatNumericVar_65 = "-522.2895491283664F";

    String StringVar_66 = "9223372036854775804L";

    Byte ByteVar_68 = -30;

    String StringVar_69 = "q1w3ertyQWERTY *,.4%abc/w";

    float floatArrayVar_70[] = new float[500];
    FuzzUtils.init(floatArrayVar_70, floatVar_43);

    LambdaInterfaceTwoArgslong_17 lambdaFunctionTwoArgslong_71 = (long lambdaVallong_72,
        long lambdaVallong_73) -> ((long) (lambdaFunctionTwoArgslong_52.lambdaFunctionTwoArgslong(
            (long) (lambdaFunctionTwoArgslong_52.lambdaFunctionTwoArgslong((long) (lambdaVallong_73),
                (long) (lambdaVallong_73))),
            (long) (lambdaVallong_73))));

    LambdaInterfaceOneArgString_8 lambdaFunctionOneArgString_74 = (
        String lambdaValString_75) -> ((String) (String) (((booleanVar_45) ? (lambdaValString_75 + "-17")
            : String.valueOf(Math.pow(-126, (32764 + -241))))));

    String StringVar_76 = new String(byteArrayVar_61, 86, 61, Charset.forName("KOI8-R"));

    Float FloatArrayVar_77[] = new Float[500];
    FuzzUtils.init(FloatArrayVar_77, FloatVar_27);

    double doubleVar_78 = -85.64043837365118D;

    AnotherClass AnotherClassObjVar_79 = new AnotherClass();

    float floatVar_80 = 0.8408317862001622F;

    int intVar_81 = 8382;

    LambdaInterfaceOneArgFloat_12 lambdaFunctionOneArgFloat_82 = (
        Float lambdaValFloat_83) -> ((Float) (float) (lambdaFunctionOneArgFloat_48
            .lambdaFunctionOneArgFloat((Float) (float) (lambdaValFloat_83))));

    long longVar_84 = 37L;

    String StringVar_85 = new String(byteArrayVar_25, 105, 73, Charset.forName("windows-1251"));

    LambdaInterfaceOneArgString_8 lambdaFunctionOneArgString_86 = (
        String lambdaValString_87) -> ((String) (String) ((lambdaValString_87 + lambdaValString_87)));

    byte byteVar_88 = -128;

    Integer IntegerVar_89 = -16583;

    String StringVar_90 = new String(byteArrayVar_25, 36, 43, Charset.forName("windows-1252"));

    final short shortVal_91 = -32768;

    short shortVar_92 = 34;

    Integer IntegerVar_93 = 2147483645;

    String StringLongNumericVar_94 = "-2147483699L";

    String StringVar_98 = new String(byteArrayVar_61, 21, 109, Charset.forName("UTF-8"));

    double doubleVar_99 = -855.3858624281894D;

    String StringVar_101 = new String(byteArrayVar_61, 30, 114, Charset.forName("windows-1251"));

    String StringfloatNumericVar_103 = "476381.3690802497F";

    String StringArrayVar_107[] = new String[500];
    FuzzUtils.init(StringArrayVar_107, StringVar_85);

    long longVar_109 = 4L;

    short shortVar_110 = 123;

    if (((IntegerVar_89 + IntegerVar_93) != IntegerVar_93)) {
      StringVar_55 = StringVar_47.substring(StringVar_47.length() / 5);
    } else {
      for (long longIndArraySafeVar_111 = 1; longIndArraySafeVar_111 <= 56; longIndArraySafeVar_111++) {
        if (((StringVar_41.equals(StringVar_44.substring(StringVar_44.length() / 7))))
            ? (StringVar_98.equals(
                lambdaFunctionOneArgString_74.lambdaFunctionOneArgString((String) (String) (StringArrayVar_107[0]))))
            : (StringVar_98.equals(StringVar_47.substring(StringVar_47.length() / 1)))) {
          StringVar_41 = StringArrayVar_107[(int) (longIndArraySafeVar_111)];
          long longIndArraySafeVar_112 = 53;
          do {
            System.arraycopy(ShortArrayVar_38, 216, ShortArrayVar_38, 220, 162);
            if ((!((StringVar_50.equals(lambdaFunctionOneArgString_74
                .lambdaFunctionOneArgString((String) (String) (lambdaFunctionOneArgString_86
                    .lambdaFunctionOneArgString((String) (String) (StringArrayVar_107[0])))))))
                        ? (IntegerVar_89 > IntegerVar_89)
                        : (byteArrayVar_25[(int) (longIndArraySafeVar_112)] > ((byteArrayVar_61[(int) (longIndArraySafeVar_112
                            + 3)] * (byte) (longArrayVar_56[(int) (longIndArraySafeVar_111)]))
                            - byteArrayVar_25[(int) (longIndArraySafeVar_112)])))) {
              byte byteUnknownZeroVar_113 = (byte) (FuzzUtils.UnknownZero);
              byte byteIndArraySafeVar_114 = 1;
              do {
                if ((StringVar_44.equals(lambdaFunctionOneArgString_74.lambdaFunctionOneArgString(
                    (String) (String) (lambdaFunctionOneArgString_86.lambdaFunctionOneArgString(
                        (String) (String) (StringVar_44.substring(StringVar_44.length() / 8)))))))) {
                  StringlongNumericVar_30 = (lambdaFunctionOneArgString_74.lambdaFunctionOneArgString(
                      (String) (String) (StringVar_90.substring(StringVar_90.length() / 9).toUpperCase()))
                      + lambdaFunctionOneArgString_74
                          .lambdaFunctionOneArgString((String) (String) (StringArrayVar_107[0])));
                  StringVar_50 = StringVar_101.substring(StringVar_101.length() / 4);
                  synchronized (AnotherClassObjVar_79) {
                  }

                } else if ((!((((byte) (intVar_31) - byteArrayVar_25[(int) (longIndArraySafeVar_111)])
                    - ((byteArrayVar_25[(int) (longIndArraySafeVar_111 + 2)]
                        + byteArrayVar_25[(int) (longIndArraySafeVar_111 + 6)])
                        + (byteArrayVar_25[byteIndArraySafeVar_114 + 1]
                            * byteArrayVar_61[byteIndArraySafeVar_114]))) < byteArrayVar_61[(int) (longIndArraySafeVar_111)]))) {

                }

                byteIndArraySafeVar_114 = (byte) (byteIndArraySafeVar_114 + 3);

              } while (byteIndArraySafeVar_114 < 0
                  + byteUnknownZeroVar_113 * byteArrayVar_61[(int) (longIndArraySafeVar_111)] + 27);

            } else if ((lambdaFunctionTwoArgslong_52.lambdaFunctionTwoArgslong(
                (long) (longArrayVar_56[(int) (longIndArraySafeVar_111)]),
                (long) (((intArrayVar_59[(int) (longIndArraySafeVar_112)]
                    + intArrayVar_59[(int) (longIndArraySafeVar_111)])
                    * longArrayVar_26[(int) (longIndArraySafeVar_112 + 5)]))) != lambdaFunctionTwoArgslong_71
                        .lambdaFunctionTwoArgslong(
                            (long) (lambdaFunctionTwoArgslong_71.lambdaFunctionTwoArgslong(
                                (long) (longArrayVar_26[(int) (longIndArraySafeVar_112 + 7)]),
                                (long) (lambdaFunctionTwoArgslong_71.lambdaFunctionTwoArgslong(
                                    (long) (longArrayVar_26[(int) (longIndArraySafeVar_111 + 7)]),
                                    (long) (longArrayVar_56[(int) (longIndArraySafeVar_112 + 3)]))))),
                            (long) (((longArrayVar_26[(int) (longIndArraySafeVar_111)]
                                / longArrayVar_26[(int) (longIndArraySafeVar_111 + 6)]) + longVar_109))))) {

            }

            --longIndArraySafeVar_112;

          } while (longIndArraySafeVar_112 >= 0 + 4);

        } else {

        }
        StringlongNumericVar_30 = lambdaFunctionOneArgString_74
            .lambdaFunctionOneArgString((String) (String) (StringArrayVar_107[0]));

      }
    }
    if ((longVar_24 > longVar_84)) {
      if ((StringVar_41
          .equals(lambdaFunctionOneArgString_86.lambdaFunctionOneArgString((String) (String) (StringVar_69))))) {

      }

    } else {
      byte byteUnknownZeroVar_115 = (byte) (FuzzUtils.UnknownZero);
      byte byteIndArraySafeVar_116 = 35;
      do {
        if (((((intVar_32 < intArrayVar_59[byteIndArraySafeVar_116 + 7]))
            ? (StringVar_41.equals(lambdaFunctionOneArgString_86
                .lambdaFunctionOneArgString((String) (String) (lambdaFunctionOneArgString_74
                    .lambdaFunctionOneArgString((String) (String) (StringArrayVar_107[0]))))))
            : (byteArrayVar_25[byteIndArraySafeVar_116] > byteArrayVar_25[byteIndArraySafeVar_116]))
                ? (!java.util.Arrays.equals(intArrayVar_59, intArrayVar_59))
                : (StringVar_90.equals(lambdaFunctionOneArgString_74
                    .lambdaFunctionOneArgString((String) (String) (StringArrayVar_107[0])))))
                        ? ((StringVar_101.equals(StringVar_46)))
                            ? ((StringVar_90.equals(StringArrayVar_107[0]))) ? (StringVar_44.equals(StringVar_44))
                                : (longArrayVar_26[byteIndArraySafeVar_116] < longArrayVar_26[byteIndArraySafeVar_116])
                            : ((StringVar_69.equals(StringVar_76.substring(StringVar_76.length() / 9)))
                                || (StringVar_85.equals(lambdaFunctionOneArgString_86
                                    .lambdaFunctionOneArgString((String) (String) (StringVar_55)))))
                        : (booleanVar_45) ? (StringVar_50.equals(StringVar_101))
                            : (StringVar_44.equals(StringArrayVar_107[0]))) {
          for (long longIndVar_117 = 87; longIndVar_117 < 1; --longIndVar_117) {

          }
        } else {
          StringVar_98 = StringArrayVar_107[0];
        }

        byteIndArraySafeVar_116 = (byte) (byteIndArraySafeVar_116 - 5);

      } while (byteIndArraySafeVar_116 >= 0
          + byteUnknownZeroVar_115 * ++byteArrayVar_61[(intArrayVar_59[byteIndArraySafeVar_116] >>> 1) % 340] + 1);
      int intIndArraySafeVar_118 = 1;
      while (intIndArraySafeVar_118 <= 172/* LOOP_CNT_UPPER_LIMIT_ARRAY_SAFE_VAR_LONG_LOOP */ ) {

        ++intIndArraySafeVar_118;

      }

    }
    return (double) ((0 +
        (intVar_31 + intVar_32 + intVar_63 + intVar_81)
        + FuzzUtils.checksumCollectionVarsMethodint(new int[][] { intArrayVar_59 }) + 0
        +
        (shortVar_28 + shortVar_92 + shortVar_110) + (shortVal_91) + 0
        +
        (byteVar_88) + FuzzUtils.checksumCollectionVarsMethodbyte(new byte[][] { byteArrayVar_25, byteArrayVar_61 }) + 0
        +
        (longVar_24 + longVar_84 + longVar_109)
        + FuzzUtils.checksumCollectionVarsMethodlong(new long[][] { longArrayVar_26, longArrayVar_56 }) + 0
        +
        (floatVar_43 + floatVar_80) + FuzzUtils.checksumCollectionVarsMethodfloat(new float[][] { floatArrayVar_70 })
        + 0
        +
        (doubleVar_58 + doubleVar_78 + doubleVar_99) + (doubleVal_23)
        + FuzzUtils.checksumCollectionVarsMethoddouble(
            new double[][] { doubleArrayVar_35, doubleArrayVar_39, doubleArrayVar_42, doubleArrayVar_51 })
        + 0
        +
        (IntegerVar_89 + IntegerVar_93) + 0
        +
        FuzzUtils.checksumCollectionVarsMethodShort(new Short[][] { ShortArrayVar_38 }) + 0
        +
        (ByteVar_68) + 0
        +
        0
        +
        (FloatVar_27) + FuzzUtils.checksumCollectionVarsMethodFloat(new Float[][] { FloatArrayVar_77 }) + 0
        +
        0
        +
        (StringVar_41 + StringVar_44 + StringVar_46 + StringVar_47 + StringVar_50 + StringVar_55 + StringVar_66
            + StringVar_69 + StringVar_76 + StringVar_85 + StringVar_90 + StringVar_98 + StringVar_101).length()
        + (StringFloatNumericVar_65 + StringLongNumericVar_94 + StringfloatNumericVar_103 + StringintNumericVar_36
            + StringlongNumericVar_30 + StringlongNumericVar_33 + StringlongNumericVar_34).length()
        + (StringshortNumericVal_60).length() + 0
        +
        0));

  }

  // Test method
  public static float method_float_float_119(float floatVal_120) {
    int intArrayVar_121[] = new int[500];
    FuzzUtils.init(intArrayVar_121, -2147483645);

    LambdaInterfaceTwoArgslong_17 lambdaFunctionTwoArgslong_122 = (long lambdaVallong_123,
        long lambdaVallong_124) -> ((long) ((lambdaVallong_124
            + (((java.util.Arrays.equals((new int[500]), (new int[500])) || (!(!(floatVal_120 > floatVal_120)))))
                ? lambdaVallong_123
                : lambdaVallong_124))));

    String StringFloatNumericVar_125 = "-70.217217900249F";

    String StringbyteNumericVar_126 = "-45";

    byte byteVar_127 = 54;

    short shortIndVar_128 = 1;
    while (shortIndVar_128 <= 134/* LOOP_CNT_UPPER_LIMIT_ARRAY_SAFE_VAR_LONG_LOOP */ ) {
      byteVar_127 /= (byte) (byteVar_127);
      if ((!(-2147483683L != -50L))) {
        if ((byteVar_127 > (byte) (shortIndVar_128))) {
          if (((!(-73.06056756425723D != -77.89614116054634D)) || false)) {
            byteVar_127 |= (byte) ((byte) (-2147483645));
            byteVar_127 += (byte) (byteVar_127);
            byteVar_127 += (byte) (Math.min((byteVar_127 + byteVar_127), (byte) (-9223372036854775807L)));
            if ((byteVar_127 != byteVar_127)) {
              byteVar_127 = (byte) (byteVar_127);
            } else {
              intArrayVar_121 = intArrayFieldVar_21;
            }

          } else {
            intArrayVar_121[((-113 - -104) >>> 1) % 264] *= 2315;
            int intUnknownZeroVar_129 = FuzzUtils.UnknownZero;
            for (int intIndVar_130 = -2; intIndVar_130 <= 91 + intUnknownZeroVar_129 * intIndVar_130; ++intIndVar_130) {

            }

          }
          short shortIndArraySafeVar_131 = 1;
          while (shortIndArraySafeVar_131 < 55) {

            ++shortIndArraySafeVar_131;

          }

        } else {
          for (int intIndVar_132 = -2; intIndVar_132 != 90; intIndVar_132 = intIndVar_132 + 1) {

          }

        }
      } else {
        byte byteIndArraySafeVar_133 = 91;
        while (byteIndArraySafeVar_133 <= 1) {

          --byteIndArraySafeVar_133;

        }
      }

      shortIndVar_128 = (short) (shortIndVar_128 + 5);

    }
    if (java.util.Arrays.equals(intArrayVar_121, intArrayVar_121)) {
      System.arraycopy(intArrayVar_121, 67, intArrayFieldVar_21, 50, 145);
    } else {
      byteVar_127 += (byte) (((byte) (68L) + byteVar_127));
    }
    return (float) ((0 +
        FuzzUtils.checksumCollectionVarsMethodint(new int[][] { intArrayVar_121 }) + 0
        +
        0
        +
        (byteVar_127) + 0
        +
        0
        +
        (floatVal_120) + 0
        +
        0
        +
        0
        +
        0
        +
        0
        +
        0
        +
        0
        +
        0
        +
        (StringFloatNumericVar_125 + StringbyteNumericVar_126).length() + 0
        +
        0));

  }

  // Test method
  public static int method_int_int_134(int intVal_135) {
    String StringfloatNumericVar_136 = "0.05698862488676859F";

    Integer IntegerVar_137 = 103;

    LambdaInterfaceTwoArgsDouble_3 lambdaFunctionTwoArgsDouble_138 = (Double lambdaValDouble_139,
        Double lambdaValDouble_140) -> ((Double) (double) (((lambdaValDouble_139 - lambdaValDouble_140)
            - lambdaValDouble_139)));

    boolean booleanVar_141 = true;

    boolean booleanVar_142 = false;

    final boolean booleanVal_143 = false;

    boolean booleanVar_144 = true;

    LambdaInterfaceTwoArgsShort_14 lambdaFunctionTwoArgsShort_145 = (Short lambdaValShort_146,
        Short lambdaValShort_147) -> ((Short) (short) ((94 + (-126 + -32768))));

    final boolean booleanVal_148 = true;

    String StringArrayVar_149[] = new String[500];
    FuzzUtils.init(StringArrayVar_149, "-125");

    float floatArrayVar_150[] = new float[500];
    FuzzUtils.init(floatArrayVar_150, -46.69382848261012F);

    String StringArrayVar_151[] = new String[500];
    FuzzUtils.init(StringArrayVar_151, "30661");

    boolean booleanVar_152 = true;

    final String StringFloatNumericVal_153 = "97.6763879133373F";

    int intArrayVar_154[] = new int[500];
    FuzzUtils.init(intArrayVar_154, intVal_135);

    int intArrayVar_155[] = new int[500];
    FuzzUtils.init(intArrayVar_155, intVal_135);

    long longArrayVar_156[] = new long[500];
    FuzzUtils.init(longArrayVar_156, -7966L);

    int intArrayVar_157[] = new int[500];
    FuzzUtils.init(intArrayVar_157, intVal_135);

    short shortVar_158 = 82;

    String StringlongNumericVar_159 = "2147483657L";

    int intVar_160 = -5831;

    int intVar_161 = -105;

    Long LongVar_162 = -2147483648L;

    byte byteArrayVar_163[] = new byte[500];
    FuzzUtils.init(byteArrayVar_163, (byte) (126));

    byte byteArrayVar_164[] = new byte[500];
    FuzzUtils.init(byteArrayVar_164, (byte) (99));

    LambdaInterfaceOneArgLong_10 lambdaFunctionOneArgLong_165 = (
        Long lambdaValLong_166) -> ((Long) (long) ((lambdaValLong_166 | (lambdaValLong_166 - lambdaValLong_166))));

    Integer IntegerArrayVar_167[] = new Integer[500];
    FuzzUtils.init(IntegerArrayVar_167, IntegerVar_137);

    short shortVar_168 = 11306;

    int intArrayVar_169[] = new int[500];
    FuzzUtils.init(intArrayVar_169, intVal_135);

    final String StringdoubleNumericVal_170 = "85.80961817531502D";

    byte byteArrayVar_171[] = new byte[500];
    FuzzUtils.init(byteArrayVar_171, (byte) (123));

    String StringVar_172 = new String(byteArrayVar_163, 73, 80, Charset.forName("IBM855"));

    final Boolean BooleanVal_173 = true;

    String StringVar_174 = new String(byteArrayVar_171, 87, 1, Charset.forName("windows-1254"));

    LambdaInterfaceTwoArgsDouble_3 lambdaFunctionTwoArgsDouble_175 = (Double lambdaValDouble_176,
        Double lambdaValDouble_177) -> ((Double) (double) ((lambdaValDouble_176
            - lambdaFunctionTwoArgsDouble_138.lambdaFunctionTwoArgsDouble((Double) (double) (lambdaValDouble_176),
                (Double) (double) (lambdaValDouble_177)))));

    String StringArrayVar_178[] = new String[500];
    FuzzUtils.init(StringArrayVar_178, StringVar_174);

    double doubleVar_179 = 365.84732842105586D;

    float floatArrayVar_180[] = new float[500];
    FuzzUtils.init(floatArrayVar_180, -290.9470712370472F);

    Short ShortArrayVar_181[] = new Short[500];
    FuzzUtils.init(ShortArrayVar_181, Short.valueOf((short) -113));

    byte byteVar_182 = 123;

    final double doubleVal_183 = -63.0364331507826D;

    final short shortVal_184 = 32765;

    int intVar_185 = 2046;

    double doubleArrayVar_186[] = new double[500];
    FuzzUtils.init(doubleArrayVar_186, -81.73772486651734D);

    Short ShortVar_187 = -126;

    String StringVar_188 = new String(byteArrayVar_163, 68, 61, Charset.forName("IBM862"));

    String StringDoubleNumericVar_189 = "-219459.76951771986D";

    String StringVar_190 = new String(byteArrayVar_163, 99, 19, Charset.forName("KOI8-U"));

    double doubleVar_191 = -576193.1042055377D;

    double doubleArrayVar_192[] = new double[500];
    FuzzUtils.init(doubleArrayVar_192, doubleVar_191);

    String StringByteNumericVar_193 = "120";

    Long LongVar_194 = 9223372036854775800L;

    int intArrayVar_195[] = new int[500];
    FuzzUtils.init(intArrayVar_195, intVar_185);

    Double DoubleVar_196 = -4.788843897638898D;

    Double DoubleVar_197 = -82.86283107203701D;

    Integer IntegerVar_198 = -555798442;

    boolean booleanVar_199 = false;

    String StringVar_200 = new String(byteArrayVar_171, 57, 27, Charset.forName("windows-1254"));

    String StringVar_201 = new String(byteArrayVar_163, 93, 69, Charset.forName("IBM862"));

    String StringlongNumericVar_202 = "82L";

    double doubleArrayVar_203[] = new double[500];
    FuzzUtils.init(doubleArrayVar_203, doubleVar_191);

    final String StringbyteNumericVal_204 = "-127";

    byte byteArrayVar_205[] = new byte[500];
    FuzzUtils.init(byteArrayVar_205, (byte) (byteVar_182));

    int intVar_206 = -83;

    intVar_185 -= intArrayVar_195[Math.min(Math.abs((intVar_206) / 2), (316))];
    for (short shortIndArraySafeVar_207 = 114/* LOOP_CNT_UPPER_LIMIT_ARRAY_SAFE_VAR_LONG_LOOP */; shortIndArraySafeVar_207 < 1; --shortIndArraySafeVar_207) {
      intVar_160 = intArrayVar_195[shortIndArraySafeVar_207];
      try {
        short shortIndArraySafeVar_208 = 24;
        do {
          intVar_185 += intArrayVar_157[shortIndArraySafeVar_207 + 2];
          StringArrayVar_149 = Arrays.copyOf(StringArrayVar_149, 438);
          if (booleanVar_199) {
            intVar_206 -= intArrayVar_195[shortIndArraySafeVar_208];
            int intIndVar_209 = 71;
            do {
              StringVar_201 = StringArrayVar_151[intArrayVar_155[intIndVar_209 + 5]];
              StringVar_190 = StringArrayVar_149[intIndVar_209 + 5]
                  .substring(intArrayVar_169[intArrayFieldVar_21[intIndVar_209]]);
              if ((((!(LongVar_194 == LongVar_194)) || (StringByteNumericVar_193
                  .equals((StringArrayVar_151[intIndVar_209] + StringArrayVar_178[intIndVar_209].toUpperCase())))))
                      ? ((!(intArrayVar_157[intIndVar_209] == intArrayVar_169[intIndVar_209]))) ? booleanVar_141
                          : java.util.Arrays.equals(byteArrayVar_164, byteArrayVar_205)
                      : (doubleArrayVar_186[intIndVar_209] > doubleArrayVar_203[intIndVar_209 + 5])) {
                StringfloatNumericVar_136 = StringArrayVar_178[intArrayVar_157[StringVar_172.indexOf(
                    intArrayVar_169[intIndVar_209],
                    StringVar_174.indexOf(intArrayVar_157[intArrayVar_157[intIndVar_209]]))]];

              }

              intIndVar_209 = intIndVar_209 - 2;

            } while (intIndVar_209 >= 0 + 4);
            StringVar_190 = StringArrayVar_151[0];
            intVar_206 -= intArrayVar_157[shortIndArraySafeVar_208 + 2];

          } else {

          }

          shortIndArraySafeVar_208--;

        } while (shortIndArraySafeVar_208 > 0 + 1);

      } catch (Exception exVar_210) {
        // System.out.println(exVar_210.getClass().getCanonicalName());
        intVar_206 -= intArrayVar_155[shortIndArraySafeVar_207];
        try {
          int intUnknownZeroVar_211 = FuzzUtils.UnknownZero;
          for (int intIndVar_212 = 77; intIndVar_212 < 3
              + intUnknownZeroVar_211 * intArrayVar_157[intIndVar_212]; intIndVar_212--) {

          }

        } catch (Exception exVar_213) {
          // System.out.println(exVar_213.getClass().getCanonicalName());
        }
        StringlongNumericVar_202 = StringArrayVar_178[0];
        switch (++intArrayVar_157[(StringDoubleNumericVar_189.indexOf(((intArrayVar_154[shortIndArraySafeVar_207]
            + StringVar_201.indexOf(StringVar_190.lastIndexOf(StringArrayVar_178[0])))
            + intArrayVar_155[shortIndArraySafeVar_207]), intArrayFieldVar_21[shortIndArraySafeVar_207]) >>> 1)
            % 272]) {
          case -3:
            doubleArrayVar_203 = doubleArrayVar_192;
            break;
          case -1:
            if ((ShortArrayVar_181[shortIndArraySafeVar_207] <= (Math.min(shortVal_184, (shortVar_168 + shortVar_158))
                + lambdaFunctionTwoArgsShort_145.lambdaFunctionTwoArgsShort(
                    (Short) (short) (ShortArrayVar_181[shortIndArraySafeVar_207]),
                    (Short) (short) (ShortArrayVar_181[shortIndArraySafeVar_207]))))) {

            } else {

            }
            break;
          case 0:
            int intIndVar_214 = 94;
            while (intIndVar_214 < 1) {

              --intIndVar_214;

            }
            break;
          case 2:
            intVar_161 &= (intArrayVar_155[shortIndArraySafeVar_207]
                * intArrayVar_169[Math.min(Math.abs((intVar_185) / 2), (291))]);
            break;
          case 4:
            StringVar_188 = StringArrayVar_151[0];
            break;
          case 6:
            StringVar_200 = StringArrayVar_178[0];
            break;
          case 8:
            StringVar_188 = StringArrayVar_149[0];
            break;
          case 10:
            StringVar_188 = StringArrayVar_178[0];
            break;
          case 13:
            int intIndVar_215 = 1;
            while (intIndVar_215 < 83) {

              intIndVar_215++;

            }
            break;
          case 14:
            try {

            } catch (Exception exVar_216) {
              // System.out.println(exVar_216.getClass().getCanonicalName());
            }
            break;
          case 17:
            StringVar_201 = StringArrayVar_178[0];
            break;
          case 18:
            int intIndVar_217 = 1;
            while (intIndVar_217 <= 83) {

              ++intIndVar_217;

            }
          case 21:
            StringVar_190 = StringArrayVar_149[(intArrayVar_157[shortIndArraySafeVar_207 + 5] >>> 1) % 292];
            break;
          case 22:
            if ((StringVar_200.equals(String.valueOf(
                (intArrayVar_169[shortIndArraySafeVar_207 + 5] * intArrayVar_169[shortIndArraySafeVar_207]))))) {
              intVar_185 += --intVar_160;
            } else {
              StringVar_188 = (StringVar_188 + StringVar_188.substring(StringVar_188.length() / 6));
            }
            break;
          case 24:
            if (((shortVal_184 == shortVar_168)
                || (((short) (intArrayFieldVar_21[shortIndArraySafeVar_207]) != (shortIndArraySafeVar_207
                    - ((short) (longArrayVar_156[shortIndArraySafeVar_207]) - shortIndArraySafeVar_207)))
                    || (doubleArrayVar_192[shortIndArraySafeVar_207] != doubleArrayVar_192[shortIndArraySafeVar_207
                        + 4])))) {

            } else {

            }
            break;
          case 26:
            intVar_161 = intArrayVar_155[shortIndArraySafeVar_207 + 2];
            break;
          case 29:
            StringfloatNumericVar_136 = String.valueOf(intArrayVar_154[shortIndArraySafeVar_207 + 5]);
            break;
          case 31:
            doubleVar_179 += (doubleArrayVar_192[shortIndArraySafeVar_207]
                - (doubleVal_183 + doubleArrayVar_186[shortIndArraySafeVar_207 + 1]));
            break;
          case 32:
            shortVar_158 += (short) ((shortVar_158 << shortVar_158));
            break;
          default:
            StringVar_172 = StringArrayVar_178[0];
            break;
        }
      }

    }
    return (int) ((0 +
        (intVar_160 + intVar_161 + intVar_185 + intVar_206) + (intVal_135)
        + FuzzUtils.checksumCollectionVarsMethodint(
            new int[][] { intArrayVar_154, intArrayVar_155, intArrayVar_157, intArrayVar_169, intArrayVar_195 })
        + 0
        +
        (shortVar_158 + shortVar_168) + (shortVal_184) + 0
        +
        (byteVar_182)
        + FuzzUtils.checksumCollectionVarsMethodbyte(
            new byte[][] { byteArrayVar_163, byteArrayVar_164, byteArrayVar_171, byteArrayVar_205 })
        + 0
        +
        FuzzUtils.checksumCollectionVarsMethodlong(new long[][] { longArrayVar_156 }) + 0
        +
        FuzzUtils.checksumCollectionVarsMethodfloat(new float[][] { floatArrayVar_150, floatArrayVar_180 }) + 0
        +
        (doubleVar_179 + doubleVar_191) + (doubleVal_183)
        + FuzzUtils.checksumCollectionVarsMethoddouble(
            new double[][] { doubleArrayVar_186, doubleArrayVar_192, doubleArrayVar_203 })
        + 0
        +
        (IntegerVar_137 + IntegerVar_198)
        + FuzzUtils.checksumCollectionVarsMethodInteger(new Integer[][] { IntegerArrayVar_167 }) + 0
        +
        (ShortVar_187) + FuzzUtils.checksumCollectionVarsMethodShort(new Short[][] { ShortArrayVar_181 }) + 0
        +
        0
        +
        (LongVar_162 + LongVar_194) + 0
        +
        0
        +
        (DoubleVar_196 + DoubleVar_197) + 0
        +
        (StringVar_172 + StringVar_174 + StringVar_188 + StringVar_190 + StringVar_200 + StringVar_201).length()
        + (StringByteNumericVar_193 + StringDoubleNumericVar_189 + StringfloatNumericVar_136 + StringlongNumericVar_159
            + StringlongNumericVar_202).length()
        + (StringFloatNumericVal_153 + StringbyteNumericVal_204 + StringdoubleNumericVal_170).length() + 0
        +
        0));

  }

  // Test method
  public static double method_double_double_218(double doubleVal_219) {
    int intVar_235 = 10;
    Double DoubleVar_240 = -26.335025324149626D;
    Double DoubleVar_246 = 87.9546734116494D;
    byte byteIndVar_247 = 1;

    while (byteIndVar_247 <= 112) {
      if (doubleVal_219 != doubleVal_219) {
        intVar_235--;
      } else {
        if (method_double_double_22(doubleVal_219) != doubleVal_219) {
          double res = (intVar_235 < 1234.2 ? DoubleVar_240 : DoubleVar_246);

          if (res > 0) {
          }
        }
      }

      byteIndVar_247++;
    }

    return 10.0;
  }

  public void mainTest(String[] strArr1) {
    double doubleVal_266 = method_double_double_218(-87.63906324914454D);
    System.out.println(doubleVal_266);
    System.out.println("--------------------");
  }

  public static void main(String[] args) {
    TestReduceAllocationAndSetTypeTwice _instance = new TestReduceAllocationAndSetTypeTwice();

    for (int i = 0; i < 400; i++) {
      _instance.mainTest(args);
    }
  }
}
