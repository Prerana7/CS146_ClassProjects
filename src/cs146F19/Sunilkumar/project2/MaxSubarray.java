package cs146F19.Sunilkumar.project2;

//Class that has the maxSum, index of arrival and index of departure as fields
public class MaxSubarray {
	
	int sum;
	int indexArrive;
	int indexDepart;
	
	public MaxSubarray(int sum, int indexArrive, int indexDepart) {
		this.sum = sum;
		this.indexArrive = indexArrive;
		this.indexDepart = indexDepart;
	}
	
	//brute force algorithm for finding the MaxSubarray
	public MaxSubarray algorithmA(int[] a) {
		
		//making no profit
		if(a.length==1 && a[0]>0)
			return new MaxSubarray(a[0], 0, 0);
		
		int max = 0;
		int indexArrive = -1;
		int indexDepart = -1;
		for(int i=0; i<a.length-1; i++) {
			int sum = a[i];
			for(int j=i+1; j<a.length; j++) {
				sum = sum + a[j];
				if(sum>max) {
					max = sum;
					indexArrive = i;
					indexDepart = j;
				}
			}
		}
		//If profit <=0, don't go
		if(max<=0)
			return new MaxSubarray(max, indexArrive, indexDepart);
		
		return new MaxSubarray(max, indexArrive, indexDepart);
	}
	
	//Divide and Conquer algorithm for finding the MaxSubarray
	public MaxSubarray algorithmB(int[] a, int low, int high) {
		//trivially the max sum
		if(high==low) {
			if(a[low]<=0) //If profit <=0, don't go
				return new MaxSubarray(0, -1, -1);
			return new MaxSubarray(a[low], low, high);
		}
		
		int mid = (low+high)/2;
		
		//recursively find maxSubarray on the left, right, and across the left and right sub-arrays
		MaxSubarray left = algorithmB(a, low, mid);
		MaxSubarray right = algorithmB(a, mid+1, high);
		MaxSubarray cross = findCrossArray(a, low, mid, high);
		
		//If profit <=0, don't go
		if(left.sum<=0 && right.sum<=0 && cross.sum<=0)
			return new MaxSubarray(0, -1, -1);
		
		if(left.sum >= right.sum && left.sum >= cross.sum)
			return left;
		else if(right.sum >= left.sum && right.sum >= cross.sum)
			return right;
		else
			return cross;
	}
	
	//Algorithm for finding the maxSubarray if arrival is on the left and departure is on the right sub-array
	public MaxSubarray findCrossArray(int[] a, int low, int mid, int high) {
		//find the max sum on the left and record the index of arrival
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int maxleft = -1;
		for(int i=mid; i>=low; i--) {
			sum = sum+a[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxleft = i;
			}
		}
		
		//find the max sum on the right and record the index of departure
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		int maxright = -1;
		for(int j=mid+1; j<=high; j++) {
			sum = sum+a[j];
			if (sum > rightSum) {
				rightSum = sum;
				maxright = j;
			}
		}
		return new MaxSubarray(leftSum+rightSum, maxleft, maxright);
	}

	//Kadane's algorithm for finding the maxSubarray 
	public MaxSubarray algorithmC(int[] a) {
		
		//find the max sum by reseting maxTemp to 0 every time the sum becomes negative
		int maxSum = 0;
		int maxTemp= 0;
		int arrive= 0; 
		int depart= -1;
		int tempArrive=0;
		for(int i=0; i<a.length; i++) {
			maxTemp = maxTemp + a[i];
			if(maxTemp < 0) {
				maxTemp = 0;
				arrive=i+1;
			} 
			if(maxSum < maxTemp) {
				maxSum = maxTemp;
				depart=i;
				tempArrive=arrive;
			}
		}
		arrive=tempArrive;
		//If profit <=0, don't go
		if(maxSum<=0) {
			return new MaxSubarray(0, -1, -1);
		}
		return new MaxSubarray(maxSum, arrive, depart);
	}
	
	
	//returns true if the sum, arrival and departure in both MaxSubarrays are the equal
	@Override
	public boolean equals(Object o) {
		MaxSubarray other = (MaxSubarray)o;
		if(this.sum==other.sum && this.indexArrive==other.indexArrive && this.indexDepart==other.indexDepart)
			return true;
		return false;
	}
	
}
