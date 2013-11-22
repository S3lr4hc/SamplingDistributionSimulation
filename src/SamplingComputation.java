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
		/*int mid = (lower + upper)/2;
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
		}*/
		//int[] values = new int[10];
		//int N = 100;
		//int lower = 1;
		//int upper = 10;
		int mid = (lower + upper)/2;
		int temp =0;
		int k = 0;
		int j = 0;
		int a = 0;
		int d = 0;
		int value = N/(upper-lower+1);
		System.out.println("value: " + value);
		int x = 0;
		values=new int[upper-lower+1];
		for(int y = lower; y < mid -1; y++){
			a++;
		}
		System.out.println("a: " + a);
		x = (a*(a+1)/2);
		System.out.println("x: "+ x);
		for(int b = 0; b < upper-lower+1; b++) {
			values[b] = value;
		}
		
		switch((lower + upper) %2)
		{
		case 0:
			k = mid - lower - 1;
			for(int i = lower; i <= upper; i++) {
				if(i < mid - 1) {
					values[i-lower] -= k;
					k--;
				} 
				else if( i > mid + 1) {
					values[i-lower] = values[upper-i];
				}
				else if(i == mid) {
					temp = x*2;
					values[i-lower] += temp;
					values[i-lower] += N%(upper-lower+1);
					//if(d*2 + values[i-lower]< N)
					//values[i-lower] += N - ((d*2)+values[i-lower]);
				}
				System.out.println(values[i-lower]);
				
				tableModel.addColumn(i);
				
				value=values[i-lower];
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				
				tableModel.setValueAt(value, 0, i-lower+1);
				
				
				d += values[i-lower];
			}
			break;
		case 1:
			k = mid - lower;
			if(N%2 == 0) {
				for(int i = lower; i <= upper; i++) {
					if(i < mid) {
						values[i-lower] -= k;
						k--;
					} 
					else if( i > mid + 1) {
						values[i-lower] = values[upper-i];
					}
					else if(i == mid || i == mid + 1) {
						temp = x;
						values[i-lower] += temp;
						values[i-lower] += N%(upper-lower+1);
						if(i == mid)
							if(d*2 + (values[i-lower]*2) < N)
							{
								j = (N-((d*2)+(values[i-lower]*2)))/2;
								values[i-lower] += (N-((d*2)+(values[i-lower]*2)))/2;
							}
						if(i == mid + 1) {
							values[i-lower] += j;
						}
					}
					System.out.println(values[i-lower]);
					tableModel.addColumn(i);
					
					value=values[i-lower];
					tempDataset.addValue(value, "Frequency", Integer.toString(i));
					
					tableModel.setValueAt(value, 0, i-lower+1);
					d += values[i-lower];
				}
			}
			//else System.out.println("BAD!");
			break;
		}
		System.out.println();
		System.out.println(d);
		dataset=tempDataset;
	}
	
	public void popBimodalDist(int N)
	{
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		values = new int[upper-lower+1];
		//int N = 103;
		//int lower = 1;
		//int upper = 10;
		int mid = (lower + upper)/2;
		int lmid = (lower + mid)/2;
		int temp =0;
		int k = 0;
		int a = 0;
		int d = 0;
		int value = N/(upper-lower+1);
		System.out.println("value: " + value);
		int x = 0;
		for(int y = lower; y < mid -1; y++){
			a++;
		}
		System.out.println("a: " + a);
		x = (a*(a+1)/2);
		System.out.println("x: "+ x);
		for(int b = 0; b < upper-lower+1; b++) {
			values[b] = value;
		}
		
		switch((lower + upper) %2)
		{
		case 0:
			k = lmid - lower - 1;
			for(int i = lower; i < mid; i++) {
				if(i < lmid - 1) {
					values[i-lower] -= k;
					k--;
				}
				else if( i > lmid + 1) {
					values[i-lower] = values[mid-i];
				}
				else if(i == lmid) {
					temp = x;
					values[i-lower] += temp;
					values[i-lower] += N%(mid-lower+1);
					//if(d*2 + values[i-lower]< N)
					//values[i-lower] += N - ((d*2)+values[i-lower]);
				}
				tableModel.addColumn(i);
				
				value=values[i-lower];
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				
				tableModel.setValueAt(value, 0, i-lower+1);
				System.out.println(values[i-lower]);
				d += values[i-lower];
			}
			values[mid-lower] = N - (d*2);
			tableModel.addColumn(mid);
			
			value=values[mid-lower];
			tempDataset.addValue(value, "Frequency", Integer.toString(mid));
			
			tableModel.setValueAt(value, 0, mid-lower+1);
			System.out.println(values[mid-lower]);
			d += values[mid-lower];
			for(int i = mid + 1; i <= upper; i++) {
				values[i-lower] = values[upper - i];
				tableModel.addColumn(i);
				
				value=values[i-lower];
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				
				tableModel.setValueAt(value, 0, i-lower+1);
				System.out.println(values[i-lower]);
				d += values[i-lower];
			}
			break;
		case 1:
			k = lmid - lower - 1;
			for(int i = lower; i < mid; i++) {
				if(i < lmid - 1) {
					values[i-lower] -= k;
					k--;
				} 
				else if( i > lmid + 1) {
					values[i-lower] = values[upper-i];
				}
				else if(i == lmid) {
					temp = x;
					values[i-lower] += temp;
					values[i-lower] += N%(mid-lower+1);
					//if(d*2 + values[i-lower]< N)
					//values[i-lower] += N - ((d*2)+values[i-lower]);
				}
				tableModel.addColumn(i);
				
				value=values[i-lower];
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				
				tableModel.setValueAt(value, 0, i-lower+1);
				System.out.println(values[i-lower]);
				d += values[i-lower];
			}
			values[mid-lower] = (N - (d*2))/2;
			tableModel.addColumn(mid);
			
			value=values[mid-lower];
			tempDataset.addValue(value, "Frequency", Integer.toString(mid));
			
			tableModel.setValueAt(value, 0, mid-lower+1);
			System.out.println(values[mid-lower]);
			d += values[mid-lower];
			values[mid-lower+1] = values[mid-lower];
			if(d*2 < N)
			values[mid-lower+1] += N-(d*2); 
			tableModel.addColumn(mid + 1);
			
			value=values[mid + 1 -lower];
			tempDataset.addValue(value, "Frequency", Integer.toString(mid + 1));
			
			tableModel.setValueAt(value, 0, mid + 1 -lower+1);
			System.out.println(values[mid+1-lower]);
			d += values[mid+1-lower];
			for(int i = mid + 2; i <= upper; i++) {
				values[i-lower] = values[upper - i];
				tableModel.addColumn(i);
				
				value=values[i-lower];
				tempDataset.addValue(value, "Frequency", Integer.toString(i));
				
				tableModel.setValueAt(value, 0, i-lower+1);
				System.out.println(values[i-lower]);
				d += values[i-lower];
			}
			break;
		}
		System.out.println();
		System.out.println(d);
		dataset=tempDataset;
	}
	
	public void popSkewedDist(int N)
	{
		/*DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
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
		dataset=tempDataset;*/
		values = new int[upper-lower+1];
		DefaultCategoryDataset tempDataset=new DefaultCategoryDataset();
		int mid = (lower + upper)/2;
		int umid = (upper + mid)/2;
		int value = N/(upper-lower+1);
		int count = 0;
		int a = 0;
		int x = 0;
		System.out.println("value: " + value);
		for(int y = lower; y < mid -1; y++){
			a++;
		}
		System.out.println("a: " + a);
		x = (a*(a+1)/2);
		System.out.println("x: "+ x);
		/*for(int b = 0; b < upper-lower+1; b++) {
			values[b] = value;
		}*/
		for(int i = lower; i < umid; i++)
		{
			value = i-lower+1;
			count += value;
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
		}
		value = N - count - (upper - umid + 1);
		tableModel.addColumn(umid);
		tempDataset.addValue(value, "Frequency", Integer.toString(umid));
		tableModel.setValueAt(value, 0, umid-lower+1);
		count += value;
		for(int i = umid+1; i <= upper;i++)
		{
			value = upper - i + 1;
			tableModel.addColumn(i);
			tempDataset.addValue(value, "Frequency", Integer.toString(i));
			tableModel.setValueAt(value, 0, i-lower+1);
			count+=value;
		}
		System.out.println(count);
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
