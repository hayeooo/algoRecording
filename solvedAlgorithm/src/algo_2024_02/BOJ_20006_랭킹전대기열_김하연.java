package algo_2024_02;

import java.io.*;
import java.util.*;

class Room{
    int level;
    List<Player> players;
}
class Player implements Comparable<Player>{
    String nickname;
    int level;

    Player(String nickname,int level){
        this.nickname=nickname;
        this.level=level;
    }
    @Override
    public int compareTo(Player other){
        return this.nickname.compareTo(other.nickname);
    }
}
public class BOJ_20006_랭킹전대기열_김하연{

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int p;           // 플레이어의 수
    static int m;           // 방의 정원



    public static void main(String[] args) throws IOException{
        br=new BufferedReader(new InputStreamReader(System.in));
        // 플레이어 수(p) 방의 정원(m)을 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        p=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        List<Room> rooms=new ArrayList<>();
        sb=new StringBuilder();

        for (int person=0;person<p;person++){
            // 플레이어의 레벨과 닉네임을 입력받는다.
            st=new StringTokenizer(br.readLine().trim());
            int l=Integer.parseInt(st.nextToken());
            String n=st.nextToken();

            Player player=new Player(n,l);

            // 들어갈 방을 찾는다.
            boolean find=false;
            for (int idx=0;idx<rooms.size();idx++){
                // 처음 입장한 플레이어 레벨을 기준으로 -10부터 +10일 때
                Room room=rooms.get(idx);
                if (l>=room.level-10 && l<=room.level+10 && room.players.size()<m){
                    room.players.add(player);
                    find=true;
                    break;
                }
            }
            // 방이 생성되지 않은 경우
            if (!find){
                Room room=new Room();
                room.level=l;
                room.players=new ArrayList<Player>();
                room.players.add(player);
                rooms.add(room);
            }
        }
        // 모든 방 매칭을 한 후, 방 정보 출력
        for (int idx=0;idx<rooms.size();idx++){
            Room room=rooms.get(idx);
            if (room.players.size()==m){
                sb.append("Started!").append("\n");
            }
            else{
                sb.append("Waiting!").append("\n");
            }
            // 닉네임 사전 순대로 정렬한다.
            Collections.sort(room.players);
            for (Player player:room.players){
                sb.append(player.level).append(" ").append(player.nickname).append("\n");
            }
        }
        System.out.print(sb);
    }
}