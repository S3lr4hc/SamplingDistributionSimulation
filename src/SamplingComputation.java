import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;



public class SamplingComputation {
	
	private double mean;
	private double variance;
	private int lower;
	private int upper;
	private int[] x;
	private int[] values;
	private CategoryDataset dataset;
	private DefaultTableModel tableModel;
	
	public SamplingComputation()
	{
		tableModel=new DefaultTableModel();
	}
	public double getMean() {
		return mean;
	}
	public CategoryDataset getDataset()
	{
		return dataset;
	}
	public double getVariance() {
		return variance;
	}
	
	public int[] getValues() {
		return values;
	}
	public DefaultTableModel getTableModel()
	{
	return tableModel;
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
	
	public void popUniformDist(int N) { //for the table; function for graphing to follow
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		values=new int[upper-lower+1];
		//int value = (int)(lower + (Math.random() * (upper - lower+1))); //upper and lower bounds are for values of members of the population, right?
		int value=N/(upper-lower+1);
		for(int i = lower; i <=upper; i++) {
			tableModel.addColumn(i);
			values[i-lower] = value;
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
		}
		dataset=tempDataset;
	}
	
	public void popRandDist(int N) {
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		int value;
		int[] sampleValues=new int[N];
		
		for(int i = 0; i < N; i++) {
			value = (int)(lower + (Math.random() * (upper - lower+1)));
			sampleValues[i] = value;
		}
		for(int i=0;i<N;i++)
		{
			System.out.println(sampleValues[i]);
		}
		getPopDist(sampleValues,upper-lower+1);
		for(int i=lower;i<=upper;i++)
		{
			tableModel.addColumn(i);
			value=values[i-lower];
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
		}
		dataset=tempDataset;
		
	}
	public void getPopDist(int[] sampleValues, int range)
	{
		values=new int[range];
		for(int value: values)
		{
			value=0;
		}
		for(int value: sampleValues)
		{
			values[value-lower]+=1;
		}
	}
}
