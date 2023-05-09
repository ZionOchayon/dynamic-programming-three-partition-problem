import java.util.Arrays;

public class Main {

    	public static boolean [][][][] opt_partition(int[] S) {
	    int n = S.length;
	    int sum = Arrays.stream(S).sum();
	    boolean [][][][] opt = new boolean[n + 1][sum + 1][sum + 1][sum + 1];
	    for (int i = 0; i <= n; i++) {
	        for (int a = 0; a <= sum; a++) {
	            for (int b = 0; b <= sum; b++) {
	                for (int c = 0; c <= sum; c++) {
	                    opt[i][a][b][c] = false;
	                }
	            }
	        }
	    }
	    opt[0][0][0][0] = true;
	    for (int i = 1; i <= n; i++) {
	        for (int a = 0; a <= sum; a++) {
	            for (int b = 0; b <= sum; b++) {
	                for (int c = 0; c <= sum; c++) {
	                    if (a >= S[i - 1]) {
	                        opt[i][a][b][c] |= opt[i - 1][a - S[i - 1]][b][c];
	                    }
	                    if (b >= S[i - 1]) {
	                        opt[i][a][b][c] |= opt[i - 1][a][b - S[i - 1]][c];
	                    }
	                    if (c >= S[i - 1]) {
	                        opt[i][a][b][c] |= opt[i - 1][a][b][c - S[i - 1]];
	                    }
	                    opt[i][a][b][c] |= opt[i - 1][a][b][c];
	                }
	            }
	        }
	    }
	    return opt;
	}

	public static boolean partition(int[] S) {
	    int n = S.length;
	    int sum = Arrays.stream(S).sum();
	    if (sum % 3 != 0) {
	        return false;
	    }
	    int part = sum / 3;
	    boolean [][][][] opt = opt_partition(S);
	    if (opt[n][part][part][part]) {
	        reconstruct(opt, S, n, part, part, part);
	        return true;
	    }
	    return false;
	}

	public static void reconstruct(boolean [][][][] opt_arr, int[] S, int i, int a, int b, int c) {
	    if (i == 0) {
	        return;
	    }
	    if (a >= S[i - 1] && opt_arr[i][a][b][c] == opt_arr[i - 1][a - S[i - 1]][b][c]) {
	        System.out.println("Item number:" + i + ", " + "value:" + S[i - 1] + " belongs to set 1");
	        reconstruct(opt_arr, S, i - 1, a - S[i - 1], b, c);
	    } else if (b >= S[i - 1] && opt_arr[i][a][b][c] == opt_arr[i - 1][a][b - S[i - 1]][c]) {
	    	System.out.println("Item number:" + i + ", " + "value:" + S[i - 1] + " belongs to set 2");
	        reconstruct(opt_arr, S, i - 1, a, b - S[i - 1], c);
	    } else if (c >= S[i - 1] && opt_arr[i][a][b][c] == opt_arr[i - 1][a][b][c - S[i - 1]]){
	    	reconstruct(opt_arr, S, i - 1, a, b, c - S[i - 1]);
	    	System.out.println("Item number:" + i + ", " + "value:" + S[i - 1] + " belongs to set 3");
	    }
	}

	public static void main(String[] args)
	{
	    // Input: a set of integers
	    int[] S = {7,3,2,1,5,4,8};

	    if (partition(S)) {
	        System.out.println("Set can be partitioned");
	    }
	    else {
	        System.out.println("Set cannot be partitioned");
	    }
	}
}