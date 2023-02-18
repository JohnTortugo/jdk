/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Supplier;
import java.util.random.RandomGenerator;

public abstract class AbstractCompressExpandTest {

    static int testCompress(int i, int mask) {
        int result = 0;
        int rpos = 0;
        while (mask != 0) {
            if ((mask & 1) != 0) {
                result |= (i & 1) << rpos;
                rpos++; // conditional increment
            }
            i >>>= 1; // unconditional shift-out
            mask >>>= 1;
        }
        return result;
    }

    static int testExpand(int i, int mask) {
        int result = 0;
        int rpos = 0;
        while (mask != 0) {
            if ((mask & 1) != 0) {
                result |= (i & 1) << rpos;
                i >>>= 1; // conditional shift-out
            }
            rpos++; // unconditional increment
            mask >>>= 1;
        }
        return result;
    }

    static long testCompress(long i, long mask) {
        long result = 0;
        int rpos = 0;
        while (mask != 0) {
            if ((mask & 1) != 0) {
                result |= (i & 1) << rpos++;
            }
            i >>>= 1;
            mask >>>= 1;
        }
        return result;
    }

    static long testExpand(long i, long mask) {
        long result = 0;
        int rpos = 0;
        while (mask != 0) {
            if ((mask & 1) != 0) {
                result |= (i & 1) << rpos;
                i >>>= 1;
            }
            rpos++;
            mask >>>= 1;
        }
        return result;
    }

    abstract int actualCompress(int i, int mask);

    abstract int actualExpand(int i, int mask);

    abstract int expectedCompress(int i, int mask);

    abstract int expectedExpand(int i, int mask);

    abstract long actualCompress(long i, long mask);

    abstract long actualExpand(long i, long mask);

    abstract long expectedCompress(long i, long mask);

    abstract long expectedExpand(long i, long mask);

    static int SIZE = 1024;

    <T> Supplier<T> supplierWithToString(Supplier<T> s, String name) {
        return new Supplier<>() {
            @Override
            public T get() {
                return s.get();
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

    @DataProvider
    Object[][] maskLongProvider() {
        RandomGenerator rg = RandomGenerator.getDefault();

        return new Object[][]{
                {supplierWithToString(() -> rg.longs(SIZE).toArray(), "random masks")},
                {supplierWithToString(this::contiguousMasksLong, "contiguous masks")}
        };
    }

    long[] contiguousMasksLong() {
        int size = 64 * (64 + 1) / 2 + 1; // 2080 + 1
        long[] masks = new long[size];

        int i = 0;
        masks[i++] = 0L;
        for (int len = 1; len < 64; len++) {
            for (int pos = 0; pos <= 64 - len; pos++) {
                masks[i++] = ((1L << len) - 1) << pos;
            }
        }
        masks[i++] = -1L;

        assert i == masks.length;
        return masks;
    }



    @Test(dataProvider = "maskLongProvider")
    public void testCompressExpandLong(Supplier<long[]> maskProvider) {
        RandomGenerator rg = RandomGenerator.getDefault();

        long[] values = rg.longs(SIZE).toArray();
        long[] masks = maskProvider.get();

        for (long i : values) {
            for (long m : masks) {
                long a = actualCompress(actualExpand(i, m), m);
                Assert.assertEquals(a, normalizeCompressedValue(i, m));

                long b = actualCompress(actualExpand(i, ~m), ~m);
                Assert.assertEquals(b, normalizeCompressedValue(i, ~m));

                a = actualExpand(actualCompress(i, m), m);
                b = actualExpand(actualCompress(i, ~m), ~m);
                Assert.assertEquals(a & b, 0);
                Assert.assertEquals(a | b, i);
            }
        }
    }

    static long normalizeCompressedValue(long i, long mask) {
        int mbc = Long.bitCount(mask);
        if (mbc != 64) {
            return i & ((1L << mbc) - 1);
        } else {
            return i;
        }
    }
}
