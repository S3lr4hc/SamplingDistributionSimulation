

public class SamplingComputation {
	
	private double mean;
	private double variance;
	private int lower;
	private int upper;
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
		upper=upperBound;
		lower=lowerBound;
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
		values=new int[n];
		int value = (int)(lower + (Math.random() * (upper - lower+1))); 
		
		for(int i = 0; i < n; i++) {
			values[i] = value;
		}
	}
	
	public void popRandDist(int n) {
		int value;
		values=new int[n];
		for(int i = 0; i < n; i++) {
			value = (int)(lower + (Math.random() * (upper - lower+1)));
			values[i] = value;
		}
		for(int i=0;i<n;i++)
		{
			System.out.println(values[i]);
		}
	}
}
