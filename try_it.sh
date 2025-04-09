#!/bin/bash


/wf/tortugo-master/build/linux-x86_64-server-fastdebug/jdk/bin/javac -cp build/linux-x86_64-server-fastdebug/support/test/lib/test-lib.jar:/wf/tortugo-master/build/linux-x86_64-server-fastdebug/support/test/lib/wb_classes/ TestMethodEvictionAndProfiling.java

/wf/tortugo-master/build/linux-x86_64-server-fastdebug/jdk/bin/java         \
    -Xbootclasspath/a:/wf/tortugo-master/build/linux-x86_64-server-fastdebug/support/test/lib/wb_classes/       \
    -cp .:/wf/tortugo-master/build/linux-x86_64-server-fastdebug/support/test/lib/test-lib.jar                  \
    -XX:+UnlockDiagnosticVMOptions                                              \
    -XX:+WhiteBoxAPI                                                            \
    -Xbatch                                                                     \
    -XX:+SegmentedCodeCache                                                     \
    -XX:+UseG1GC                                                                \
    -XX:+PrintTieredEvents                                                      \
    -XX:+CIPrintRequests                                                        \
    -Xlog:codecache=debug                                                       \
    -XX:ReservedCodeCacheSize=10m                                               \
    -XX:CompileCommand=PrintCompilation,*TestMethodEvictionAndProfiling::*      \
    TestMethodEvictionAndProfiling

