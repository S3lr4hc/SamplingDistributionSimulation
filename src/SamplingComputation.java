import java.lang.reflect.Array;


public class SamplingComputation {
	
	private double mean;
	private double variance;
	private int values[];
	
	public double getMean() {
		return mean;
	}
	
	public double getVariance() {
		return variance;
	}
	
	public void calculateMean(int n, int lowerBound, int upperBound) {
		int j = 0;
		int temp = 0;
		
		for(int i = lowerBound; i <= upperBound; i++) {
			temp += i * values[j];
			j++;
		}
		
		mean = temp / n;
	}
	
	public void calculateVariance(int n, int lowerBound, int upperBound) {
		double temp = 0;
		
		for(int i = 0; i < Array.getLength(values); i++) {
			temp = (values[i] - mean) * (values[i] - mean);
			variance += temp;
		}
		variance /= n;
	}
	
	public void uniformDist(int n, int lowerBound, int upperBound) {
		int k = 0;
		int value = (int)(Math.random() * n) + 1;
		
		for(int i = lowerBound; i <= upperBound; i++) {
			values[k] = value;
			k++;
		}
	}
}
