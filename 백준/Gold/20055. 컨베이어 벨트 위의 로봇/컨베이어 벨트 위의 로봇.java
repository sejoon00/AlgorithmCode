import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int K;
    public static int[] belt;
    public static boolean[] robot;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];
        robot = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        int k=1;
        while(true)
        {
            rotate();
            move();
            putOn();
            if(check())
                break;
            k++;
        }

        System.out.println(k);

    }

    public static void putOn() {
        if(belt[0]!=0)
        {
            belt[0]--;
            robot[0] = true;
        }
    }

    public static void move() {
        for (int i = N-2; i >= 0 ; i--) {
            if(robot[i] && belt[i+1] != 0 && !robot[i+1])
            {
                belt[i+1]--;
                robot[i] = false;
                robot[i+1] = true;
            }
        }
        robot[N-1] = false;
    }
    public static boolean check()
    {
        int count =0;
        for (int i = 0; i < 2 * N; i++) {
            if(belt[i] == 0)
                count++;
            if(count == K)
                return true;
        }
        return false;
    }

    public static void rotate() {
        int tmp1 =belt[0];
        int tmp2 =0;
        for (int i = 0; i < 2 * N - 1; i++) {
            tmp2 = belt[i+1];
            belt[i+1] = tmp1;
            tmp1 = tmp2;
        }
        belt[0] = tmp2;

        boolean t1 = robot[0];
        boolean t2 = false;
        for (int i = 0; i < N - 1; i++) {
            t2 = robot[i+1];
            robot[i+1] = t1;
            t1 = t2;
        }
        robot[0] = t2;
        robot[N-1] = false;
    }
}