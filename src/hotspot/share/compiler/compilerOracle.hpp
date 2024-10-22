/*
 * Copyright (c) 1998, 2024, Oracle and/or its affiliates. All rights reserved.
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
 *
 */

#ifndef SHARE_COMPILER_COMPILERORACLE_HPP
#define SHARE_COMPILER_COMPILERORACLE_HPP

#include "memory/allStatic.hpp"
#include "oops/oopsHierarchy.hpp"
#include "utilities/istream.hpp"

class methodHandle;


// CompilerOracle is an interface for turning on and off compilation
// for some methods

//       OPTION_TYPES: type, name
#define OPTION_TYPES(type) \
  type(Intx, "intx") \
  type(Uintx, "uintx") \
  type(Bool, "bool") \
  type(Ccstr, "ccstr") \
  type(Ccstrlist, "ccstrlist") \
  type(Double, "double")

//       COMPILECOMMAND_OPTIONS: option, name, type, applicable_levels
#define COMPILECOMMAND_OPTIONS(option)                                                                                                           \
            option(Help,                          "help",                          Unknown,       CompLevel::CompLevel_all)                      \
            option(Quiet,                         "quiet",                         Unknown,       CompLevel::CompLevel_all)                      \
            option(Log,                           "log",                           Bool,          CompLevel::CompLevel_all)                      \
            option(Print,                         "print",                         Bool,          CompLevel::CompLevel_all)                      \
            option(Inline,                        "inline",                        Bool,          CompLevel::CompLevel_full_optimization)        \
            option(DontInline,                    "dontinline",                    Bool,          CompLevel::CompLevel_full_optimization)        \
            option(Blackhole,                     "blackhole",                     Bool,          CompLevel::CompLevel_all)                      \
            option(CompileOnly,                   "compileonly",                   Bool,          CompLevel::CompLevel_all)                      \
            option(Exclude,                       "exclude",                       Bool,          CompLevel::CompLevel_all)                      \
            option(Break,                         "break",                         Bool,          CompLevel::CompLevel_all)                      \
            option(BreakAtExecute,                "BreakAtExecute",                Bool,          CompLevel::CompLevel_all)                      \
            option(BreakAtCompile,                "BreakAtCompile",                Bool,          CompLevel::CompLevel_all)                      \
            option(MemLimit,                      "MemLimit",                      Intx,          CompLevel::CompLevel_all)                      \
            option(MemStat,                       "MemStat",                       Uintx,         CompLevel::CompLevel_all)                      \
            option(PrintAssembly,                 "PrintAssembly",                 Bool,          CompLevel::CompLevel_all)                      \
            option(PrintCompilation,              "PrintCompilation",              Bool,          CompLevel::CompLevel_all)                      \
            option(PrintInlining,                 "PrintInlining",                 Bool,          CompLevel::CompLevel_full_optimization)        \
            option(PrintIntrinsics,               "PrintIntrinsics",               Bool,          CompLevel::CompLevel_all)                      \
            option(PrintNMethods,                 "PrintNMethods",                 Bool,          CompLevel::CompLevel_all)                      \
            option(PrintOptoAssembly,             "PrintOptoAssembly",             Bool,          CompLevel::CompLevel_full_optimization)        \
            option(PrintDebugInfo,                "PrintDebugInfo",                Bool,          CompLevel::CompLevel_all)                      \
            option(PrintRelocations,              "PrintRelocations",              Bool,          CompLevel::CompLevel_all)                      \
            option(PrintDependencies,             "PrintDependencies",             Bool,          CompLevel::CompLevel_all)                      \
            option(BackgroundCompilation,         "BackgroundCompilation",         Bool,          CompLevel::CompLevel_all)                      \
            option(RepeatCompilation,             "RepeatCompilation",             Intx,          CompLevel::CompLevel_all)                      \
            option(ReplayInline,                  "ReplayInline",                  Bool,          CompLevel::CompLevel_full_optimization)        \
            option(DumpReplay,                    "DumpReplay",                    Bool,          CompLevel::CompLevel_all)                      \
            option(DumpInline,                    "DumpInline",                    Bool,          CompLevel::CompLevel_full_optimization)        \
            option(CompileThresholdScaling,       "CompileThresholdScaling",       Double,        CompLevel::CompLevel_all)                      \
            option(ControlIntrinsic,              "ControlIntrinsic",              Ccstrlist,     CompLevel::CompLevel_all)                      \
            option(DisableIntrinsic,              "DisableIntrinsic",              Ccstrlist,     CompLevel::CompLevel_all)                      \
            option(BlockLayoutByFrequency,        "BlockLayoutByFrequency",        Bool,          CompLevel::CompLevel_full_optimization)        \
            option(TraceOptoPipelining,           "TraceOptoPipelining",           Bool,          CompLevel::CompLevel_full_optimization)        \
            option(TraceOptoOutput,               "TraceOptoOutput",               Bool,          CompLevel::CompLevel_full_optimization)        \
            option(TraceSpilling,                 "TraceSpilling",                 Bool,          CompLevel::CompLevel_all)                      \
            option(Vectorize,                     "Vectorize",                     Bool,          CompLevel::CompLevel_full_optimization)        \
            option(CloneMapDebug,                 "CloneMapDebug",                 Bool,          CompLevel::CompLevel_full_optimization)        \
            option(IncrementalInlineForceCleanup, "IncrementalInlineForceCleanup", Bool,          CompLevel::CompLevel_full_optimization)        \
            option(MaxNodeLimit,                  "MaxNodeLimit",                  Intx,          CompLevel::CompLevel_full_optimization)        \
NOT_PRODUCT(option(TraceEscapeAnalysis,           "TraceEscapeAnalysis",           Bool,          CompLevel::CompLevel_full_optimization))       \
NOT_PRODUCT(option(PrintIdeal,                    "PrintIdeal",                    Bool,          CompLevel::CompLevel_full_optimization))       \
NOT_PRODUCT(option(PrintIdealPhase,               "PrintIdealPhase",               Ccstrlist,     CompLevel::CompLevel_full_optimization))       \
NOT_PRODUCT(option(IGVPrintLevel,                 "IGVPrintLevel",                 Intx,          CompLevel::CompLevel_full_optimization))       \
NOT_PRODUCT(option(TraceAutoVectorization,        "TraceAutoVectorization",        Ccstrlist,     CompLevel::CompLevel_full_optimization))       \
NOT_PRODUCT(option(TestOptionInt,                 "TestOptionInt",                 Intx,          CompLevel::CompLevel_all))                     \
NOT_PRODUCT(option(TestOptionUint,                "TestOptionUint",                Uintx,         CompLevel::CompLevel_all))                     \
NOT_PRODUCT(option(TestOptionBool,                "TestOptionBool",                Bool,          CompLevel::CompLevel_all))                     \
NOT_PRODUCT(option(TestOptionBool2,               "TestOptionBool2",               Bool,          CompLevel::CompLevel_all))                     \
NOT_PRODUCT(option(TestOptionStr,                 "TestOptionStr",                 Ccstr,         CompLevel::CompLevel_all))                     \
NOT_PRODUCT(option(TestOptionList,                "TestOptionList",                Ccstrlist,     CompLevel::CompLevel_all))                     \
NOT_PRODUCT(option(TestOptionDouble,              "TestOptionDouble",              Double,        CompLevel::CompLevel_all))                     \
            option(Option,                        "option",                        Unknown,       CompLevel::CompLevel_all)                      \
            option(Unknown,                       "unknown",                       Unknown,       CompLevel::CompLevel_all)

enum class CompileCommandEnum : int {
  #define enum_of_options(option, name, ctype, levels) option,
    COMPILECOMMAND_OPTIONS(enum_of_options)
  #undef enum_of_options
  Count
};

enum class OptionType {
#define enum_of_types(type, name) type,
    OPTION_TYPES(enum_of_types)
#undef enum_of_types
    Unknown
};

enum class MemStatAction {
  collect = 1, print = 2
};

class CompilerOracle : AllStatic {
 public:
  typedef bool parse_from_line_fn_t(char*);

 private:
  static bool _quiet;
  static void print_parse_error(char* error_msg, char* original_line);
  static void print_command(CompileCommandEnum option, const char* name, enum OptionType type);

  // The core parser.
  static bool parse_from_input(inputStream::Input* input,
                               parse_from_line_fn_t* parse_from_line);

 public:
  // True if the command file has been specified or is implicit
  static bool has_command_file();

  // Reads from file and adds to lists
  static bool parse_from_file();

  // Tells whether we want to exclude compilation of this method
  static bool should_exclude(const methodHandle& method, int comp_level);
  static bool be_quiet() { return _quiet; }

  // Tells whether we want to inline this method
  static bool should_inline(const methodHandle& method, int comp_level);

  // Tells whether we want to disallow inlining of this method
  static bool should_not_inline(const methodHandle& method, int comp_level);

  // Tells whether this method changes Thread.currentThread()
  static bool changes_current_thread(const methodHandle& method, int comp_level);

  // Tells whether we should print the assembly for this method
  static bool should_print(const methodHandle& method, int comp_level);

  // Tells whether we should log the compilation data for this method
  static bool should_log(const methodHandle& method, int comp_level);

  // Tells whether to break when compiling method
  static bool should_break_at(const methodHandle& method, int comp_level);

  // Tells whether there are any methods to print for print_method_statistics()
  static bool should_print_methods();

  // Tells whether there are any methods to (collect|collect+print) memory statistics for
  static bool should_collect_memstat();
  static bool should_print_final_memstat_report();

  // Tags the method as blackhole candidate, if possible.
  static void tag_blackhole_if_possible(const methodHandle& method, int comp_level);

  // A wrapper for checking bool options
  static bool has_option(const methodHandle& method, CompileCommandEnum option, int comp_level);

  // Check if method has option and value set. If yes, overwrite value and return true,
  // otherwise leave value unchanged and return false.
  template<typename T>
  static bool has_option_value(const methodHandle& method, CompileCommandEnum option, int comp_level, T& value);

  // This check is currently only needed by whitebox API
  template<typename T>
  static bool option_matches_type(CompileCommandEnum option, T& value);

  // Reads from string instead of file
  static bool parse_from_string(const char* option_string,
                                parse_from_line_fn_t* parser);
  static bool parse_from_line(char* line);
  static bool parse_from_line_quietly(char* line);
  static bool parse_compile_only(char* line);

  // Fast check if there is any option set that compile control needs to know about
  static bool has_any_command_set();

  // convert a string to a proper compilecommand option - used from whitebox.
  // returns CompileCommandEnum::Unknown on names not matching an option.
  static CompileCommandEnum string_to_option(const char* name);

  // convert a string to a proper compilecommand option
  // returns CompileCommandEnum::Unknown if name is not an option.
  static CompileCommandEnum parse_option_name(const char* name);

  // convert a string to a proper option type
  // returns OptionType::Unknown on strings not matching an option type.
  static enum OptionType parse_option_type(const char* type_str);
};

#endif // SHARE_COMPILER_COMPILERORACLE_HPP
