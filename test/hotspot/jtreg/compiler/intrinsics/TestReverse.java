/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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

/**
 * @test
 * @bug 8248830
 * @summary Test intrinsification of Integer.reverse(int)
 * @library /test/lib
 * @requires vm.compiler2.enabled
 *
 * @run main/othervm/timeout=600 -XX:-TieredCompilation -XX:CompileThreshold=1000 -Xbatch
 *      compiler.intrinsics.TestReverse
 *
 */

package compiler.intrinsics;

import java.util.Random;
import java.lang.Integer;

public class TestReverse {
    static int ITERS = 100000;

    // Previous implementation of Integer.reverse(int)
    static int shift_based_reverse(int i) {
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;

        return Integer.reverseBytes(i);
    }

    static boolean test(int test_value) {
      int correct_value = shift_based_reverse(test_value);
      int computed_value = Integer.reverse(test_value);
      int rev_back_value = Integer.reverse(computed_value);

      if (correct_value != computed_value) {
        System.err.println("Expected reverse of '" + test_value + "' was '" + correct_value + "' but got '" + computed_value + "'.");
        return false;
      }

      if (test_value != rev_back_value) {
        System.err.println("Expected reverse of '" + computed_value + "' was '" + test_value + "' but got '" + rev_back_value + "'.");
        return false;
      }

      return true;
    }

    public static void main(String args[]) throws Exception {
      Random rand = new Random(8248830);

      try {
        int test_value = 0;

        for (int a = 0  ; a < 16; a++) {
          test_value = a << 24;

          for (int b = 0 ; b <  16 ; b++) {
            test_value |= (b << 16);

            for (int c = 0 ; c <  16 ; c++) {
              test_value |= (c << 8);

              for (int d = 0 ; d <  16 ; d++) {
                test_value |= d;

                if (test(test_value) == false) {
                  System.out.println("test status : FAIL");
                  break;
                }
              }
            }
          }
        }

        for (int i = 0; i < ITERS; i++) {
          test_value = rand.nextInt();

          if (test(test_value) == false) {
            System.out.println("test status : FAIL");
            break;
          }
        }

        System.out.println("test status : PASS");
      } catch (Exception e) {
        System.out.println("test status : FAIL");
        System.out.println(e.getMessage());
      }
    }
}
