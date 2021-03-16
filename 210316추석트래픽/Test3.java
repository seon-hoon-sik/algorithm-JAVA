package test;


class Solution {
    public int solution(String[] lines) {
        int answer = 1;
        double[] start = new double[2000];
        double[] end = new double[2000];
        long[] start_tt = new long[2000];
        long[] end_tt = new long[2000];
        // 시작과 끝 나누기
        for(int i=0 ; i<lines.length ; i++) {
        	String tmp1 = lines[i].substring(11,23);
        	
        	String[] a = tmp1.split(":");
        	
        	int num = 3600;
        	for(int j=0 ; j<3 ; j++) {
        		end[i] += Double.parseDouble(a[j])*num;
        		num /= 60;
        	}
        	
        	String tmp2 = lines[i].substring(24);
        	String[] b = tmp2.split("s");
        	
        	start[i] = end[i] - Double.parseDouble(b[0]) + 0.001;
        	start_tt[i] = (int)Math.floor(start[i]*1000);
        	end_tt[i] = (int)Math.floor(end[i]*1000);
        }
        int number = lines.length;
        for(int i=0 ; i<number-1 ; i++) {
        	int cnt=1;
        	long end_t = end_tt[i] + 999;
        	for(int j=i+1 ; j<number ; j++) {
        		if(start_tt[j] <= end_t) {
        			cnt++;
        		}
        		else {
        			break;
        		}
        	}
        	answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
}

public class Test3 {

	public static void main(String[] args) {
		Solution ans = new Solution();
		String[] tmp = {"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"};
		System.out.println(ans.solution(tmp));
	}

}
