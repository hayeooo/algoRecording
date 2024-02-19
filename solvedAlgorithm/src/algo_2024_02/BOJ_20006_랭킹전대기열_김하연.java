import java.io.*;
import java.util.*;

public class BOJ_20006_랭킹전대기열_김하연{

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int p;           // 플레이어의 수
    static int m;           // 방의 정원

    class Room{
        int level;
        List<String> members;
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
                if (l>=room.level-10 && l<=room.level+10){
                    room.members.add(player);
                    find=true;

                    // 정원을 채운 경우 출력한다.
                    if (room.members.size()==m){
                        // 닉네임은 사전 순으로
                        Collections.sort(room.members);
                        for (Player participant:room.members){
                            sb.append(participant.level).append(" ").append(participant.nickname).append("\n");
                        }
                        // 해당 방을 삭제한다.
                        rooms.remove(idx);
                    }
                    break;
                }
            }
            // 방이 생성되지 않은 경우
            if (!find){
                Room room=new Room();
                room.level=l;
                room.members=new ArrayList<String>();
                room.members.add(player);
                sb.append("Started!").append("\n");
            }
        }
        // 모든 방 매칭을 한 후, 대기 중인 방 출력
        for (int idx=0;idx<rooms.size();idx++){
            sb.append("Waiting!").append("\n");
        }
        System.out.print(sb);
    }
}