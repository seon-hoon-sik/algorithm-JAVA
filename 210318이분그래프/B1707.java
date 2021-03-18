package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class B1707 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<ArrayList<Integer>> aList;
	
	static final int RED = 1;
	static final int BLUE = -1;
	static int[] colors; // 색이 RED 인지 BLUE인지 확인
	static boolean isBipartile; // 이분 그래프인지 아닌지 확인
	
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split("");
		int testCase = Integer.parseInt(input[0]);
		while(testCase-- != 0) {
			input = br.readLine().split(" ");
			int V = Integer.parseInt(input[0]); // 정점 개수
			int E = Integer.parseInt(input[1]); // 간선 개수
			
			aList = new ArrayList<>(); // 전체를 감싸는 리스트
			colors = new int[V+1]; // 각 정점의 색을 구분함
			isBipartile = true; // 초기 세팅은 이분 그래프임
			
			// ex) aList[1] -> {2,3,4} 를 위한 세팅
			for(int i=0 ; i<=V ; i++) {
				aList.add(new ArrayList<>()); // 정점 수 + 1만큼 추가
				colors[i] = 0; // 0은 방문하지 않은 정점의 색임
			}
			
			// 양방향으로 그래프 연결
			for(int i=0 ; i<E ; i++) {
				input = br.readLine().split(" ");
				int v1 = Integer.parseInt(input[0]);
				int v2 = Integer.parseInt(input[1]);
				
				aList.get(v1).add(v2);
				aList.get(v2).add(v1);
			}
			
			// 이분 그래프 : 같은 레벨의 꼭짓점끼리는 무조건 같은 색이어야하며
			// 			 인접한 정점사이는 다른 색임.
			// 주의 사항 : 연결 그래프와 비연결 그래프(모든 정점을 돌면서 확인) 
			//          모두 고려해야함
			for(int i=1 ; i<=V ; i++) {
				// 이분 그래프가 아니라면 반복문 종료
				if(!isBipartile) break;
				
				// 방문하지 않은 정점에 대하여 탐색을 시작
				if(colors[i] == 0) {
					//dfs(i, RED);
					bfs(i, RED);
				}
			}
			System.out.println(isBipartile? "YES" : "NO");
		}
		
		br.close();
	}
	static void dfs(int v, int color) {
		if(!isBipartile) return;
		
		colors[v] = color;
		
		for(int adjV : aList.get(v)) {
			// 점검하려는 정점과 인접한 정점이 같으면, 이분그래프가 아님!!
			if(colors[adjV] == color) {
				isBipartile = false;
				return;
			}
			// 한번도 방문하지 않은 정점이라면 dfs 실행
			if(colors[adjV] == 0) {
				dfs(adjV, -color);
			}
		}
	}
	
	static void bfs(int v, int color) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v); // root 정점을 큐에 삽입
		colors[v] = color; // root 정점 방문 표시 + 색 표시
		
		while(!q.isEmpty()) {
			v = q.poll();
			
			for(int adjV : aList.get(v)) {
				// 방문하지 않은 정점일 때
				if(colors[adjV] == 0) {
					q.offer(adjV);
					colors[adjV] = colors[v]*-1;
				}
				// 서로 인접한 정점의 색이 같은 색이라면 이분그래프가 아님
				else if(colors[v] + colors[adjV] != 0) {
					isBipartile = false;
					return;
				}
			}
		}
		return;
	}

}
