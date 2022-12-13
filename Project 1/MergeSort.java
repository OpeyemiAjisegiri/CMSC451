public class MergeSort implements MergeSortInterface{
	
	private long startTime, endTime;
	private int count = 0;

	@Override
	public int[] recursiveSort(int[] list) {
		/** Initializing the start time for the recursive mergesort to 
		avoid recursively re-initializing it to accurately time the sorting function. **/
		
		this.startTime = System.nanoTime();
		return recursiveMergeSort(list/*, 0, list.length - 1*/);
	}
	
	private int[] recursiveMergeSort(int[] list/*, int l, int r*/) {
		/**  Implementation of the recursive mergesort algorithm copied from 
		 * 	 "https://www.programiz.com/dsa/merge-sort", with a based case and
		 * 	 a return value added to match the UML diagram  **/
		
		 /* 
		   // Divide the array into two subarrays, sort them and merge them
		  //Added base case if the length is less than or equal to zero
		if(l == r)
			return list;
		if (l < r) {
		      // m is the point where the array is divided into two subarrays
		      int m = (l + r) / 2;

		      recursiveMergeSort(list, l, m);
		      recursiveMergeSort(list, m + 1, r);

		      // Merge the sorted subarrays
		      merge(list, l, m, r);
		    }*/
		
	    ///public static void mergeSort(int[] array)
	    //{
	        if(list == null)
	        {
	         return list;
	        }
			
	        if(list.length > 1)
	        {
			//if(list.length <= 1)
				//return list;
			//else {
	            int mid = list.length / 2;
	 
	            // Split left part
	            int[] left = new int[mid];
	            for(int i = 0; i < mid; i++)
	            {
	                left[i] = list[i];
	            }
	             
	            // Split right part
	            int[] right = new int[list.length - mid];
	            for(int i = mid; i < list.length; i++)
	            {
	                right[i - mid] = list[i];
	            }
	            recursiveMergeSort(left);
	            recursiveMergeSort(right);
	            
	            //merge(list, 0, mid, right.length);
	            int i = 0, j = 0, k = 0;
	 
	            // Merge left and right arrays
	            while(i < left.length && j < right.length)
	            {
	                if(left[i] < right[j])
	                {
	                    list[k] = left[i];
	                    count++;
	                    i++;
	                }
	                else
	                {
	                    list[k] = right[j];
	                    count++;
	                    j++;
	                }
	                k++;
	            }
	            // Collect remaining elements
	            while(i < left.length)
	            {
	                list[k] = left[i];
	                count++;
	                i++;
	                k++;
	            }
	            while(j < right.length)
	            {
	                list[k] = right[j];
	                count++;
	                j++;
	                k++;
	            }
	        }
	        this.endTime = System.nanoTime();
	    //}
		try {
			isSorted(list);
		}catch(UnsortedException e) {
			System.out.println("Unsorted Exception caught; The array is not sorted");
		}
		return list;
	}

	@Override
	public int[] iterativeSort(int[] list) {	
		/** Implementation of the iterative implementation of MergeSort sans the array copying 
		 *  was copied from  "https://www.techiedelight.com/iterative-merge-sort-algorithm-bottom-up/" 
		 *  and a return value added to match the UML diagram.	**/
		
		this.startTime = System.nanoTime();
		/* Iterative mergesort function to sor
	    t arr[0...n-1] */
	    //static void mergeSort(int arr[], int n)
	    //{
	         
	        // For current size of subarrays to
	        // be merged curr_size varies from
	        // 1 to n/2
	        int curr_size;
	                     
	        // For picking starting index of
	        // left subarray to be merged
	        int left_start;
	                         
	         
	        // Merge subarrays in bottom up
	        // manner. First merge subarrays
	        // of size 1 to create sorted
	        // subarrays of size 2, then merge
	        // subarrays of size 2 to create
	        // sorted subarrays of size 4, and
	        // so on.
	        for (curr_size = 1; curr_size <= list.length-1;
	                      curr_size = 2*curr_size)
	        {
	             
	            // Pick starting point of different
	            // subarrays of current size
	            for (left_start = 0; left_start < list.length-1;
	                        left_start += 2*curr_size)
	            {
	                // Find ending point of left
	                // subarray. mid+1 is starting
	                // point of right
	                int mid = Math.min(left_start + curr_size - 1, list.length-1);
	         
	                int right_end = Math.min(left_start
	                             + 2*curr_size - 1, list.length-1);
	         
	                // Merge Subarrays arr[left_start...mid]
	                // & arr[mid+1...right_end]
	                merge(list, left_start, mid, right_end);
	            }
	        }
	        this.endTime = System.nanoTime();
	    //}
			try {
				isSorted(list);
			}catch(UnsortedException e) {
				System.out.println("Unsorted Exception caught; The array is not sorted");
			}
		return list;
	}
	
	  // Merge two subarrays L and M into arr
	  void merge(int arr[], int p, int q, int r) {

	    // Create L ← A[p..q] and M ← A[q+1..r]
	    int n1 = q - p + 1;
	    int n2 = r - q;

	    int L[] = new int[n1];
	    int M[] = new int[n2];

	    for (int i = 0; i < n1; i++)
	      L[i] = arr[p + i];
	    for (int j = 0; j < n2; j++)
	      M[j] = arr[q + 1 + j];

	    // Maintain current index of sub-arrays and main array
	    int i, j, k;
	    i = 0;
	    j = 0;
	    k = p;

	    // Until we reach either end of either L or M, pick larger among
	    // elements L and M and place them in the correct position at A[p..r]
	    while (i < n1 && j < n2) {
	      if (L[i] <= M[j]) {
	    	count++;
	        arr[k] = L[i];
	        i++;
	      } else {
	    	count++;
	        arr[k] = M[j];
	        j++;
	      }
	      k++;
	    }

	    // When we run out of elements in either L or M,
	    // pick up the remaining elements and put in A[p..r]
	    while (i < n1) {
	      count++;
	      arr[k] = L[i];
	      i++;
	      k++;
	    }

	    while (j < n2) {
	      count++;
	      arr[k] = M[j];
	      j++;
	      k++;
	    }
	  }

	@Override
	public int getCount() {
		return this.count;
	}

	/**  Getting the duration of time it took to implement the sorting algorithm **/
	@Override
	public long getTime() {
		//return 0;
		return endTime - startTime;
	}
	
	private void isSorted(int[] arr) throws UnsortedException {
		for (int i = 0; i < arr.length - 1; i++) {
			//if the prev element < the next element 
			//then the arr is sorted. 
			if(arr[i] > arr[i+1])
				throw new UnsortedException();
		}
	}

	void setCount(int count) {
		this.count = count;
	}

}
