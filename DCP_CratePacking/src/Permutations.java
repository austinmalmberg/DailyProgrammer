
// Java program to print all permutations of a 
// given string. 
public class Permutations {
	
    public Permutations() {
    	
    }
  
    /** 
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r end index 
     */
    public void generate(int[] arr, int l, int r) 
    {    	
        if (l == r) 
            System.out.println(arr); 
        else { 
            for (int i = l; i <= r; i++) { 
                arr = swap(arr, l, i); 
                generate(arr, l + 1, r); 
                arr = swap(arr, l, i); 
            } 
        } 
    } 
  
    /** 
     * Swap Characters at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */
    public int[] swap(int[] arr, int i, int j) 
    { 
        int[] res = arr.clone();
        
        int temp = res[i];
        res[i] = res[j];
        res[j] = temp;
        
        return res;
    } 
} 
