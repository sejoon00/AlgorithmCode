class Solution {
    int N;
    int M;
    int x;
    int y;
    int r;
    int c;
    int k;
    int[] yPoint = {1,0,0,-1};
    int[] xPoint = {0,-1,1,0};
    char[] dir = {'d','l','r','u'};
    String answer="";
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        char[] ans = new char[k];
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if ((k - dist) % 2 == 1 || k < dist) {
            return "impossible";
        }
        for(int i=0;i<k;i++)
            ans[i] ='y';
        String s = String.valueOf(ans);
        this.answer = String.valueOf(ans);
        this.N =n; this.M=m;this.x=x;this.y=y;this.r=r;this.c=c;this.k=k;
        
        dfs(0,y,x,new char[k]);
        if(answer.equals(s))
            answer = "impossible";
        return answer;
    }
    
    public void dfs(int depth,int curX, int curY,char[] tmpAns){
        if (k < depth + Math.abs(curX - c) + Math.abs(curY - r)) {
            return;
        }
        if(depth==k)
        {
            if(curX==c && curY==r)
            {
                if(String.valueOf(tmpAns).compareTo(answer) <0) {
                    answer = String.valueOf(tmpAns);
                }
            }
            return;
        }

        for (int j = 0; j < 4; j++) {
            int tmpY = curY + yPoint[j];
            int tmpX = curX + xPoint[j];
            if(tmpX>=1 && tmpX<=M && tmpY>=1 && tmpY<= N && answer.compareTo(String.valueOf(tmpAns))>=0)
            {
                tmpAns[depth] = dir[j];
                dfs(depth+1,tmpX,tmpY,tmpAns);
            }
        }
    }
}