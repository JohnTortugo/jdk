 import java.lang.reflect.Method;
 import jdk.test.whitebox.WhiteBox;
 import jdk.test.whitebox.code.BlobType;
 import jdk.test.whitebox.code.NMethod;

 public class TestMethodEvictionAndProfiling {

     private static final WhiteBox WHITE_BOX = WhiteBox.getWhiteBox();
     public static double FUNCTION_RESULT = 0;

     public static void main(String [] args) throws Exception {
        Method method = TestMethodEvictionAndProfiling.class.getMethod("target_method");

        // Call target_method enough to compile
        callFunction();

        for (int i = 0; i < 10000; i++) {
            WHITE_BOX.printMethodData(method);
            System.out.println("\n\n\n\nExecuted. Evict?");
            System.in.read();

            evict(method);

            System.out.println("Evicted. Trigger comp again?");
            System.in.read();
            recompile(method);
        }
     }

     private static void recompile(Method method) {
        for (int i=0; i<10000; i++) {
            if (WHITE_BOX.isMethodCompiled(method, false)) {
                System.out.println("Compiled after " + i + " more invocations.");
                return;
            }
            target_method();
        }
        System.out.println("Not compiled after 10k more invocations.");
     }

     private static void evict(Method method) {
        for (int i=0; i<1000; i++) {
            if (!WHITE_BOX.isMethodCompiled(method, false))
                break;
            WHITE_BOX.evictNmethod(method);
        }
     }

     // Call target_method multiple times to trigger compilation
     private static void callFunction() {
         for (int i = 0; i < 16000; i++) {
             target_method();
         }
     }

     public static void target_method() {
         FUNCTION_RESULT = Math.random();
     }
 }
