#!/bin/bash

. clear.sh

make CONF=fast

/wf/tortugo-jdk-casts/build/linux-x86_64-server-fastdebug/jdk/bin/java \
	-XX:-TieredCompilation \
	-XX:CompileCommand=compileonly,*::*apply* \
	-XX:CompileCommand=compileonly,*::*getSymbols* \
	-XX:CompileCommand=compileonly,*DirectMethodHandle*::*allocateInstance* \
	-XX:CompileCommand=compileonly,*::*newInvokeSpecial* \
	-XX:CompileCommand=compileonly,*::*linkToTargetMethod* \
	-Xbatch \
	-cp /wf/tortugo-jdk-casts/build/linux-x86_64-server-fastdebug/test-support/jtreg_test_hotspot_jtreg_hotspot_all/classes/0/runtime/Metaspace/FragmentMetaspace.d:/wf/tortugo-jdk-casts/build/linux-x86_64-server-fastdebug/test-support/jtreg_test_hotspot_jtreg_hotspot_all/classes/0/test/lib:/wf/tortugo-jdk-casts/test/hotspot/jtreg/runtime/Metaspace:/wf/tortugo-jdk-casts/test/lib:/wf/tools/jtreg/lib/javatest.jar:/wf/tools/jtreg/lib/jtreg.jar \
	FragmentMetaspace


#-cp /wf/tortugo-jdk-casts/build/linux-x86_64-server-fastdebug/test-support/jtreg_test_hotspot_jtreg_hotspot_all/classes/0/runtime/Metaspace/FragmentMetaspace.d:/wf/tortugo-jdk-casts/build/linux-x86_64-server-fastdebug/test-support/jtreg_test_hotspot_jtreg_hotspot_all/classes/0/test/lib:/wf/tortugo-jdk-casts/test/hotspot/jtreg/runtime/Metaspace:/wf/tortugo-jdk-casts/test/lib:/wf/tools/jtreg/lib/javatest.jar:/wf/tools/jtreg/lib/jtreg.jar \
