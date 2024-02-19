package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Team implements Comparable<Team>{
    int id;
    int lastSubmit;
    int totalScore;

    int count;

    @Override
    public int compareTo(Team o) {
        // 총 점수 내림차순
        if (this.totalScore!=o.totalScore){
            return Integer.compare(o.totalScore,this.totalScore);
        }
        // 제출 횟수 오름차순
        else if(this.count!=o.count){
            return Integer.compare(this.count,o.count);
        }
        // 제출 시간 오름차순
        return Integer.compare(this.lastSubmit,o.lastSubmit);
    }

    @Override
    public String toString(){
        return "["+id+"], lastSubmit: "+lastSubmit+", totalScore: "+totalScore+", count: "+count;
    }
}

public class BOJ_3758_KCPC_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int T;


    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        T=Integer.parseInt(br.readLine().trim());
        sb=new StringBuilder();

        for (int tc=0;tc<T;tc++){
            st=new StringTokenizer(br.readLine().trim());
            
            int n=Integer.parseInt(st.nextToken());     // 팀의 개수
            int k=Integer.parseInt(st.nextToken());     // 문제의 개수
            int t=Integer.parseInt(st.nextToken());     // 팀 ID
            int m=Integer.parseInt(st.nextToken());     // 로그 엔트리의 개수

            // 점수를 기록한다.
            int[][] scores=new int[n+1][k+1];
            Team[] team=new Team[n+1];
            for (int i=1;i<=n;i++){
                team[i]=new Team();
                team[i].id=i;
            }
            for (int log=0;log<m;log++){
                st=new StringTokenizer(br.readLine().trim());
                int id=Integer.parseInt(st.nextToken());
                int problem=Integer.parseInt(st.nextToken());
                int score=Integer.parseInt(st.nextToken());
                team[id].lastSubmit=log;
                team[id].count+=1;
                scores[id][problem]=Math.max(scores[id][problem],score);
            }
            for (int i=1;i<=n;i++){
                // 최종 점수를 구한다.
                for (int prob=1;prob<=k;prob++){
                    team[i].totalScore+=scores[i][prob];
                }
            }
            // 정렬한다.
            List<Team> result=Arrays.asList(Arrays.copyOfRange(team,1,n+1));
            Collections.sort(result);
            for (int idx=0;idx<result.size();idx++){
                if (result.get(idx).id==t){
                    sb.append(idx+1).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
