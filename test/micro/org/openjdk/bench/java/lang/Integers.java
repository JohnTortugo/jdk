/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
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
package org.openjdk.bench.java.lang;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Tests java.lang.Integer
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Fork(jvmArgsAppend = "--enable-preview")
public class Integers {

    @Param("500")
    private int size;

    private String[] strings;

    @Setup
    public void setup() {
        Random r = new Random(0);
        strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = "" + (r.nextInt(10000) - 5000);
        }
    }

    @Benchmark
    public void parseInt(Blackhole bh) {
        for (String s : strings) {
            bh.consume(Integer.parseInt(s));
        }
    }

    @Benchmark
    public void reverse(Blackhole bh) {
        for (int i=Integer.MIN_VALUE; i<Integer.MAX_VALUE; i++) {
            bh.consume(Integer.reverse(i));
        }
    }

    @Benchmark
    public void reverse_shifts(Blackhole bh) {
        for (int i=Integer.MIN_VALUE; i<Integer.MAX_VALUE; i++) {
            bh.consume(Integers.original_reverse(i));
        }
    }

    private static int original_reverse(int i) {
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;

        return Integer.reverseBytes(i);
    }
}
