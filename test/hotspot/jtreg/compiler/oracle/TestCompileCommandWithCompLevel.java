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
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TestCompileCommandWithCompLevel {
    private static final String COMMAND_TEMPLATE = "-XX:CompileCommand=<command>,com/oracle/Test.test,<value>,<level>";
    private static final String EXPECTED_VALID_OUTPUT = "<command> com/oracle/Test.test <type> <command> = <value> for compilation level <level>";

    private static void verifyValidOption(String[] arguments, String[] expected_outputs) throws Exception {
        ProcessBuilder pb = ProcessTools.createLimitedTestJavaProcessBuilder(arguments);
        OutputAnalyzer out = new OutputAnalyzer(pb.start());

        for (String expected_output : expected_outputs) {
            out.shouldContain(expected_output);
        }

        out.shouldNotContain("CompileCommand: An error occurred during parsing");
        out.shouldHaveExitValue(0);
    }

    public static void main(String[] args) throws Exception {
        List<String> booleans = List.of("true", "false");
        List<String> invalid_bools = List.of("truefalse", "falsetrue", "false,true", "true,false");

        List<String> valid_phases = List.of("ITER_GVN1", "AFTER_EA");
        List<String> invalid_phases = List.of("phase_a", "phase_b");

        List<String> valid_igv_levels = List.of("2", "3");
        List<String> invalid_igv_levels = List.of("10", "30");

        List<String> valid_auto_vec_inputs = List.of("phase_a", "phase_b");
        List<String> invalid_auto_vec_inputs = List.of("phase_a", "phase_b");

        List<String> valid_max_node_limits = List.of("1000", "6789");
        List<String> invalid_max_node_limits = List.of("aaa", "xyz");

        List<String> valid_control_intrinsic_inputs = List.of("phase_a", "phase_b");
        List<String> invalid_control_intrinsic_inputs = List.of("rand", "rotate");

        List<String> valid_disable_intrinsic_inputs = List.of("phase_a", "phase_b");
        List<String> invalid_disable_intrinsic_inputs = List.of("java.lang", "Integer");

        List<String> valid_scaling_inputs = List.of("1.0", "2.0");
        List<String> invalid_scaling_inputs = List.of("a", "b");

        List<String> valid_repeat_comp_inputs = List.of("3", "4", "0");
        List<String> invalid_repeat_comp_inputs = List.of("a", "1m");

        List<String> valid_mem_limit_inputs = List.of("10", "123");
        List<String> invalid_mem_limit_inputs = List.of("xyz", "0m", "m1");

        List<String> valid_mem_stat_inputs = List.of("collect", "print");
        List<String> invalid_mem_stat_inputs = List.of("add", "sub");

        List<Integer> all_levels = List.of(1, 2, 3, 4);
        List<Integer> full_optimization_level = List.of(4);

        List<CommandDescription> command_descriptions = List.of(
            new CommandDescription("log", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("print", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("inline", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("dontinline", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("blackhole", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("compileonly", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("exclude", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("break", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("BreakAtExecute", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("BreakAtCompile", "bool", booleans, invalid_bools, all_levels),
//            new CommandDescription("MemLimit", "intx", valid_mem_limit_inputs, invalid_mem_limit_inputs, all_levels),
//            new CommandDescription("MemStat", "uintx", valid_mem_stat_inputs, invalid_mem_stat_inputs, all_levels),
            new CommandDescription("PrintAssembly", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("PrintCompilation", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("PrintInlining", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("PrintIntrinsics", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("PrintNMethods", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("PrintOptoAssembly", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("PrintDebugInfo", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("PrintRelocations", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("PrintDependencies", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("BackgroundCompilation", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("RepeatCompilation", "intx", valid_repeat_comp_inputs, invalid_repeat_comp_inputs, all_levels),
            new CommandDescription("ReplayInline", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("DumpReplay", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("DumpInline", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("CompileThresholdScaling", "Double", valid_scaling_inputs, invalid_scaling_inputs, all_levels),
            new CommandDescription("ControlIntrinsic", "Ccstrlist", valid_control_intrinsic_inputs, invalid_control_intrinsic_inputs, all_levels),
            new CommandDescription("DisableIntrinsic", "Ccstrlist", valid_disable_intrinsic_inputs, invalid_disable_intrinsic_inputs, all_levels),
            new CommandDescription("BlockLayoutByFrequency", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("TraceOptoPipelining", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("TraceOptoOutput", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("TraceSpilling", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("Vectorize", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("CloneMapDebug", "bool", booleans, invalid_bools, all_levels),
            new CommandDescription("IncrementalInlineForceCleanup", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("MaxNodeLimit", "intx", valid_max_node_limits, invalid_max_node_limits, full_optimization_level),
            new CommandDescription("TraceEscapeAnalysis", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("PrintIdeal", "bool", booleans, invalid_bools, full_optimization_level),
            new CommandDescription("PrintIdealPhase", "Ccstrlist", valid_phases, invalid_phases, full_optimization_level),
            new CommandDescription("IGVPrintLevel", "intx", valid_igv_levels, invalid_igv_levels, full_optimization_level),
            new CommandDescription("TraceAutoVectorization", "Ccstrlist", valid_auto_vec_inputs, invalid_auto_vec_inputs, full_optimization_level)
        );

        for (CommandDescription cdesc : command_descriptions) {
            for (String valid_input : cdesc.sample_of_valid_values) {
                for (Integer valid_level : cdesc.valid_levels) {
                    String[] arguments = {
                                            "-XX:+UnlockExperimentalVMOptions",
                                            "-XX:+UnlockDiagnosticVMOptions",
                                            COMMAND_TEMPLATE.replace("<command>", cdesc.command)
                                                            .replace("<value>", valid_input)
                                                            .replace("<level>", Integer.toString(valid_level)),
                                            "-version"
                                            };

                    String[] expected_output = { EXPECTED_VALID_OUTPUT
                                                    .replace("<command>", cdesc.command)
                                                    .replace("<type>", cdesc.type)
                                                    .replace("<value>", valid_input)
                                                    .replace("<level>", Integer.toString(valid_level))
                                               };

                    System.out.println( Arrays.toString(arguments) );
                    System.out.println( Arrays.toString(expected_output) );

                    verifyValidOption(arguments, expected_output);
                }
            }
        }

    }
}

class CommandDescription {
    public String command;
    public String type;
    public ArrayList<String> sample_of_valid_values;
    public ArrayList<String> sample_of_invalid_values;
    public ArrayList<Integer> valid_levels;

    public CommandDescription(String cmd, String type, List<String> valid_values, List<String> invalid_values, List<Integer> levels) {
        this.command = cmd;
        this.type = type;
        this.sample_of_valid_values = new ArrayList<>(valid_values);
        this.sample_of_invalid_values = new ArrayList<>(invalid_values);
        this.valid_levels = new ArrayList<>(levels);
    }
}
