package test;

class Solution3 {
    public String solution(int n) {
        String cur = "";
        String answer = "";
        String[] arr = {"1","2","4"};
        
        while(n-1>=0) {
        	int idx = (n-1) % 3;
        	answer = arr[idx];
        	answer += cur;
        	cur = answer;
        	
        	if((n/3) == (n/3.0)) {
        		n /= 3;
        		n -= 1;
        	}
        	else {
        		n = (int)Math.floor(n/3.0);
        	}
        }
        
        return answer;
    }
}
public class Test2 {
	public static void main(String[] args) {
		Solution3 ans = new Solution3();
		System.out.println(ans.solution(10));
	}
}
