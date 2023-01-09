import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static int Q;
    public static int NPow;
    public static int[][] map;
    public static int[] levelList;
    public static int[] yDir = {-1,1,0,0};
    public static int[] xDir = {0,0,-1,1};
    public static ArrayList<int[]> meltingList = new ArrayList<>();
    public static boolean[][] visited;

    public static int totalCount =0;
    public static int maxSize =0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        NPow = (int)Math.pow(2,N);
        map = new int[NPow][NPow];
        for (int i = 0; i < NPow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < NPow; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        levelList = new int[Q];
        for (int i = 0; i < Q; i++) {
            levelList[i] = Integer.parseInt(st.nextToken());
        }
        for (int k = 0; k < Q; k++) {
            int LPow = (int)Math.pow(2,levelList[k]);
            //회전시키기
            if(LPow != 1)
            {
                for (int i = 0; i < NPow; i+=LPow) {
                    for (int j = 0; j < NPow; j+=LPow) {
                        rotate(i,j,LPow);
                    }
                }
            }
            //얼음 녹이기
            for (int i = 0; i < NPow; i++) {
                for (int j = 0; j < NPow; j++) {
                    if(map[i][j] !=0)
                        melting(i,j);
                }
            }
            for (int i = 0; i < meltingList.size(); i++) {
                int x = meltingList.get(i)[1];
                int y = meltingList.get(i)[0];
                if(map[y][x]!=0)
                    map[y][x]--;
            }
            meltingList = new ArrayList<>();
        }
        //얼음 덩어리 찾기
        visited = new boolean[NPow][NPow];
        for (int i = 0; i < NPow; i++) {
            for (int j = 0; j < NPow; j++) {
                totalCount += map[i][j];
                if(map[i][j] != 0 && !visited[i][j])
                    bfs(i,j);
            }
        }

        System.out.println(totalCount);
        System.out.println(maxSize);

    }

    public static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int[] cur = {i,j};
        queue.offer(cur);
        int count=0;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for (int l = 0; l < size; l++) {
                int[] tmp = queue.poll();

                for (int k = 0; k < 4; k++) {
                    int y = tmp[0] + yDir[k];
                    int x = tmp[1] + xDir[k];
                    int[] next = {y,x};
                    if(x<0 || x>=NPow ||y<0 || y>=NPow)
                        continue;
                    if(map[y][x] > 0 && !visited[y][x])
                    {
                        queue.offer(next);
                        visited[y][x] = true;
                        count++;
                    }
                }
            }
        }
        maxSize = Math.max(maxSize,count);
    }

    public static void melting(int i,int j) {
        int count =0;
        //상하좌우에서 올바른 얼음 개수 세기
        for (int k = 0; k < 4; k++) {
            int y = i + yDir[k];
            int x = j + xDir[k];
            if(x<0 || x>=NPow || y<0 || y>=NPow )
                continue;
            if(map[y][x] >=1)
                count++;
        }
        int[] point = {i,j};
        if(count<3)
            meltingList.add(point);
    }

    public static void rotate(int y, int x,int LPow) {

        Queue<Integer> tmp = new LinkedList<>();
        int s = LPow-1;
        //위 -> 오른쪽
        for (int i = 0; i < s; i++) {
            tmp.offer(map[y+i][x+s]);
            map[y+i][x+s] = map[y][x+i];
        }
        //오른쪽 -> 아래
        for (int i = 0; i < s; i++) {
            tmp.offer(map[y+s][x+s-i]);
            map[y+s][x+s-i] = tmp.poll();
        }
        //아래->왼쪽
        for (int i = 0; i < s; i++) {
            tmp.offer(map[y+s-i][x]);
            map[y+s-i][x] = tmp.poll();
        }
        //왼쪽->위
        for (int i = 0; i < s; i++) {
            map[y][x+i] = tmp.poll();
        }
        if(LPow == 2)
            return;
        rotate(y+1,x+1,LPow-2);

    }
}