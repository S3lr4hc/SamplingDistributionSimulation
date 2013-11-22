import java.util.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		int nVal, fVal = 25, counter = 0;
		do{
			double val = rand.nextGaussian()*4+5;
			nVal = (int) Math.round(val);
			counter += nVal;
			System.out.println(nVal);
		}while(fVal > counter);
		System.out.println("Counter: " + counter);
	}
}
