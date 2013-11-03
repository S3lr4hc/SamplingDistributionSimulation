

public class SamplingComputation {
	
	private double mean;
	private double variance;
	private int[] x;
	private int[] values;
	
	public double getMean() {
		return mean;
	}
	
	public double getVariance() {
		return variance;
	}
	
	public int[] getValues() {
		return values;
	}
	
	public void setX(int lowerBound, int upperBound) {
		int j = lowerBound;
		x = new int[upperBound - lowerBound + 1];
		
		for(int i = 0; i < upperBound - lowerBound + 1; i++) {
			x[i] = j;
			j++;
		}
	}
	
	public void calculateMean(int n) {
		int temp = 0;
		
		for(int i = 0; i < x.length; i++) {
			temp += x[i] * values[i];
		}
		
		mean = temp / n;
	}
	
	public void calculateVariance(int n) {
		double temp = 0;
		
		for(int i = 0; i < x.length; i++) {
			temp = (values[i] - mean) * (values[i] - mean);
			variance += temp;
		}
		variance /= n;
	}
	
	public void popUniformDist(int n) {
		int value = (int)(Math.random() * n) + 1;
		
		for(int i = 0; i < x.length; i++) {
			values[i] = value;
		}
	}
	
	public void popRandDist(int n) {
		int value;
		
		for(int i = 0; i < x.length; i++) {
			value = (int)(Math.random() * n) + 1;
			values[i] = value;
		}
	}
}
