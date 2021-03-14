package test;

import java.util.*;
/*
 * import java.util.LinkedList
 * import java.util.Queue
 * import java.util.Arrays
 * import java.util.Collections*/

class Solution2 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        for(int i : truck_weights) {
        	q.offer(i);
        }
        for(int i=0 ; i<bridge_length ; i++) {
        	bridge.offer(0);
        }
        
        int bridge_sum = 0;
        do {
        	int w;
        	if(q.isEmpty()) {
        		w = 0;
        	}
        	else {
        		w = q.peek();
        	}
        	int minus = bridge.poll();
        	bridge_sum -= minus;
        	
        	// 다리위를 지나갈 수 있을 때
        	if(bridge_sum + w <= weight) {
        		bridge_sum += w;
        		
        		bridge.offer(w);
        		if(!q.isEmpty()) {
        			q.poll();
        		}
        	}else { // 다리위를 지나갈 수 없을 때
        		bridge.offer(0);
        	}
        	answer++;
        }while(bridge_sum != 0);
        
        return answer;
    }
}
public class Test1 {

	public static void main(String[] args) {
		Solution2 ans = new Solution2();
		int[] tmp = {7,4,5,6};
		System.out.println(ans.solution(
				2, 10, tmp));
		
	}

}
