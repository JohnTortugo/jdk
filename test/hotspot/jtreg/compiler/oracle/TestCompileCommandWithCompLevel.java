/*
 * Copyright (c) 2014, 2023, Oracle and/or its affiliates. All rights reserved.
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
 * @test TestCompileCommandWithCompLevel
 * @summary Tests parsing of -XX:CompileCommand=....,<comp_level>
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 *          java.management
 * @requires vm.flagless
 * @requires vm.debug == true
 * @run driver compiler.oracle.TestCompileCommandWithCompLevel
 */

package compiler.oracle;

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;

public class TestCompileCommandWithCompLevel {
    private static final String[] COMPILE_COMMANDS = {
        "log",
        "print",
        "inline",
        "dontinline",
        "blackhole",
        "compileonly",
        "exclude",
        "break",
        "BreakAtExecute",
        "BreakAtCompile",
        "PrintAssembly",
        "PrintCompilation",
        "PrintInlining",
        "PrintIntrinsics",
        "PrintNMethods",
        "PrintOptoAssembly",
        "PrintDebugInfo",
        "PrintRelocations",
        "PrintDependencies",
        "BackgroundCompilation",
        "ReplayInline",
        "DumpReplay",
        "DumpInline",
        "BlockLayoutByFrequency",
        "TraceOptoPipelining",
        "TraceOptoOutput",
        "TraceSpilling",
        "Vectorize",
        "CloneMapDebug",
        "IncrementalInlineForceCleanup",
        "TraceEscapeAnalysis",
        "PrintIdeal"
    };

    private static final String[] BOOLEAN_VALUES = {
        ",true,",
        ",false,",
        ","
    };

    private static final String[] COMPILATION_LEVELS = {
        "1",
        "2",
        "3",
        "4"
    };

    private static final String BOOLEAN_TEMPLATE = "-XX:CompileCommand=<command>,com/oracle/Test.test,<value>,<level>";
    private static final String BOOLEAN_TEMPLATE_EXPECTED_OUTPUT = "<command> com/oracle/Test.test bool <command> = <value> for compilation level <level>";

    private static void verifyValidOption(String[] arguments, String[] expected_outputs) throws Exception {
        ProcessBuilder pb;
        OutputAnalyzer out;

        pb = ProcessTools.createLimitedTestJavaProcessBuilder(arguments);
        out = new OutputAnalyzer(pb.start());

        for (String expected_output : expected_outputs) {
            out.shouldContain(expected_output);
        }

        out.shouldNotContain("CompileCommand: An error occurred during parsing");
        out.shouldHaveExitValue(0);
    }

    public static void main(String[] args) throws Exception {
        for (String command : COMPILE_COMMANDS) {
            for (String boolean_val : BOOLEAN_VALUES) {
                for (String compilation_level : COMPILATION_LEVELS) {
                    String[] arguments = {
                                            "-XX:+UnlockExperimentalVMOptions",
                                            "-XX:+UnlockDiagnosticVMOptions",
                                            BOOLEAN_TEMPLATE.replace("<command>", command)
                                                            .replace(",<value>,", boolean_val)
                                                            .replace("<level>", compilation_level),
                                            "-version"
                                         };

                    String[] expected_output = { BOOLEAN_TEMPLATE_EXPECTED_OUTPUT
                                                    .replace("<command>", command)
                                                    .replace("<value>", boolean_val.equals(",") ? "true" : boolean_val.replace(",", ""))
                                                    .replace("<level>", compilation_level)
                                               };

                    verifyValidOption(arguments, expected_output);
                }
            }
        }
    }
}
