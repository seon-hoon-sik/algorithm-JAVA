package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M;
	static int[][] map;
	static int[][] visit;
	static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static int answer = -1;
	
	public static void main(String[] args) throws IOException {
		
		String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
		
		map = new int[N][M];
		visit = new int[N][M];
		for(int i=0 ; i<N ; i++) {
			input = br.readLine().split("");
			for(int j=0 ; j<M ; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		bfs(0,0);
		System.out.println(answer);
		br.close();
	}
	
	
	
	static void bfs(int r, int c) {
		Queue<TwoPair> q = new LinkedList<>();
		q.add(new TwoPair(r,c,0,1));
		
		// 초기 세팅
		visit[r][c] = 0; 
		while(!q.isEmpty()) {
			TwoPair tp = q.poll();
			
			if(tp.r == N-1 && tp.c == M-1) {
				answer = tp.cnt;
				return;
			}
			
			for(int i=0 ; i<4 ; i++) {
				int dx = tp.r + dir[i][0];
				int dy = tp.c + dir[i][1];
				
				if(!isTrue(dx,dy)) {continue;}
				
				if(visit[dx][dy] > tp.wall) {
					if(map[dx][dy] == 0) {
						q.add(new TwoPair(dx, dy, tp.wall+1, tp.cnt+1));
						visit[dx][dy] = tp.wall;
					}else {
						if(tp.wall == 0) { 
							q.add(new TwoPair(dx, dy, tp.wall + 1, tp.cnt + 1));
							visit[dx][dy] = tp.wall+1;
						}
					}
				}
				
			}
		}
		return;
	}
	
	static boolean isTrue(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static class TwoPair {
		int r;
		int c;
		int wall;
		int cnt;
		
		public TwoPair(int r, int c, int wall, int cnt) {
			this.r = r;
			this.c = c;
			this.wall = wall;
			this.cnt = cnt;
		}
	}
	
}
