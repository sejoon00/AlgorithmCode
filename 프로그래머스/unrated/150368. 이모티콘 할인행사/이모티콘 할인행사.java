class Solution {
    public static int[] salePercent = {10,20,30,40};
    public static int[][] users;
    public static int[] emoticons;
    public static double[][] saleCost;
    public static int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.saleCost = new double[emoticons.length][2];
        answer = new int[2];
        dfs(0);

        return answer;
    }

    public void dfs(int depth) {
        if(depth == emoticons.length)
        {
            int count=0;
            int totalCost=0;
            for (int i = 0; i < users.length; i++) {
                long buyCost=0;
                for (int j = 0; j < emoticons.length; j++) {
                    if(users[i][0] <= saleCost[j][0])
                        buyCost+=saleCost[j][1];
                }
                if(buyCost >= users[i][1])
                    count++;
                else
                    totalCost+=buyCost;
            }
            if(answer[0]<count)
            {
                answer[0]=count;
                answer[1]=totalCost;
            }
            if(answer[0]==count)
                answer[1] = Math.max(answer[1],totalCost );
            return;
        }

        for (int i = 0; i < 4; i++) {
            saleCost[depth][0] = salePercent[i];
            saleCost[depth][1] = emoticons[depth]*(100-salePercent[i])/100.0;
            dfs(depth+1);
            saleCost[depth][0]=0;
            saleCost[depth][1]=0;
        }
    }
}