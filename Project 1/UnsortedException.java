/** If the array is not sorted, an exception should be thrown. **/
public class UnsortedException extends Exception {
	
    public UnsortedException() {
    }
    
    public UnsortedException(String message) {
    	super(message);
    }
    
}
