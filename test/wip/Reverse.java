import java.util.Random;

public class Reverse {

	public static void main(String[] args) {
		String output = "";

		for (int i=0; i<20000; i++) {
			int value = new Random().nextInt();
			int as_int = Integer.reverse(3000000 + value);
			output += String.format("%64s", Integer.toBinaryString(as_int)).replace(" ", "0");
		}

		System.out.println("Result is: " + output.substring(100, 120));

	}
}
