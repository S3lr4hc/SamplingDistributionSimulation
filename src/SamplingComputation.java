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
		tableModel=new DefaultTableModel(
				new Object[][] {
						{"f(x)"}
					},
					new String[] {
						"x"
					});
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
			//tableModel.setValueAt(i, 0,i-lower+1);
			tableModel.setValueAt(value, 0, i-lower+1);
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
		/*for(int i=0;i<N;i++)
		{
			System.out.println(sampleValues[i]);
		}*/
		getPopDist(sampleValues,upper-lower+1);
		for(int i=lower;i<=upper;i++)
		{
			tableModel.addColumn(i);
			value=values[i-lower];
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			//tableModel.setValueAt(i, 0,i-lower+1);
			tableModel.setValueAt(value, 0, i-lower+1);
		}
		dataset=tempDataset;
	}
	
	public void popNormalDist(int N) {
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		int mid = (lower + upper)/2;
		int value = N;
		int check = 0;
		int temp = 0;
		
		switch((lower+upper)%2)
		{
		case 0:
			for(int i = lower; i <= mid; i++)
			{
				value /= 2;
			}
			for(int i = lower; i < mid;i++)
			{
				check += value*2;
				tableModel.addColumn(i);
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				tableModel.setValueAt(value, 0, i-lower+1);
				value*=2;
			}
			temp = value;
			value = N - check;
			for(int i = mid; i <= upper;i++)
			{
				if(i == mid+1)
				{
					value = temp;
					value/=2;
				}
				tableModel.addColumn(i);
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				tableModel.setValueAt(value, 0, i-lower+1);
				value/=2;
			}
			break;
		case 1:
			value /= upper - lower + 1;
			value--;
			for(int i = lower; i < mid;i++)
			{
				tableModel.addColumn(i);
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				tableModel.setValueAt(value, 0, i-lower+1);
				if(i+1 == mid)
					value+=2;
				else value++;
			}
			for(int i = mid; i <= upper;i++)
			{
				tableModel.addColumn(i);
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				tableModel.setValueAt(value, 0, i-lower+1);
				if(i!=mid)
					value-=2;
			}
		}
		dataset=tempDataset;
	}
	
	public void popBimodalDist(int N)
	{
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		int mid = (lower + upper)/2;
		int lmid = (lower + mid)/2;
		int umid = (upper + mid)/2;
		int value = N/(upper-lower+1);
		int count = 0;
		
		for(int i = lower; i <= lmid; i++)
		{
			count += value;
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
			value+=2;
		}
		value-=4;
		for(int i = lmid+1; i < umid; i++)
		{
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
			count += value;
		}
		value+=2;
		for(int i = umid; i <= upper; i++)
		{
			if(i == upper)
				value = N - count;
			if(value < 0)
				value = 0;
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
			value-=2;
			count += value;
		}
		dataset=tempDataset;
	}
	
	public void popSkewedDist(int N)
	{
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		int mid = (lower + upper)/2;
		int lmid = (lower + mid)/2;
		int value = N/2;
		int count = 0;
		
		for(int i = lower; i < lmid; i++)
		{
			value/=2;
		}
		for(int i = lower; i < lmid; i++)
		{
			count += value;
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
			value*=2;
		}
		count += value;
		for(int i = lmid; i <= upper;i++)
		{
			if(i == upper)
				value = N - count;
			if(value < 0)
				value = 0;
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
			value/=3;
			count+=value;
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
