package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1937 {
	static int N;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static int map[][];
	static int panda_day[][];
	static int answer = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		panda_day = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(panda_day[i][j]==0) {
					DFS(i, j);
				}
			}
		}
		
		bw.write(answer + "\n");
        bw.flush();
        bw.close();
		br.close();
	}
	public static boolean isTrue(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	public static void DFS(int r, int c) {
		int cnt = 0;
		
		for (int i = 0; i < 4; i++) {
			int dx = r + dir[i][0];
			int dy = c + dir[i][1];
			
			if (isTrue(dx,dy) && map[dx][dy] > map[r][c]) {
				if(panda_day[dx][dy] == 0) DFS(dx, dy);
				
				cnt = Math.max(cnt, panda_day[dx][dy]);
			}
		}
		panda_day[r][c] = cnt + 1;
		answer = Math.max(answer, panda_day[r][c]);
	}
	
}
