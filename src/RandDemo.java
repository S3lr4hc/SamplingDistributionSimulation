import java.util.Random; 
class RandDemo { 
	public static void main(String args[]) { 
		int[] values = new int[11];
		int N = 103;
		int lower = 1;
		int upper = 10;
		int mid = (lower + upper)/2;
		int lmid = (lower + mid)/2;
		int temp =0;
		int k = 0;
		int j = 0;
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
		for(int b = lower-1; b < upper; b++) {
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
				System.out.println(values[i-lower]);
				d += values[i-lower];
			}
			values[mid] = N - (d*2);
			System.out.println(values[mid]);
			d += values[mid];
			for(int i = mid + 1; i <= upper; i++) {
				values[i-1] = values[upper - i];
				System.out.println(values[i-1]);
				d += values[i-1];
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
				System.out.println(values[i-lower]);
				d += values[i-lower];
			}
			values[mid] = (N - (d*2))/2;
			System.out.println(values[mid]);
			d += values[mid];
			values[mid+1] = values[mid];
			if(d*2 < N)
			values[mid+1] += N-(d*2); 
			System.out.println(values[mid+1]);
			d += values[mid+1];
			for(int i = mid + 2; i <= upper; i++) {
				values[i-1] = values[upper - i];
				System.out.println(values[i-1]);
				d += values[i-1];
			}
			break;
		}
		System.out.println();
		System.out.println(d);
	}
}