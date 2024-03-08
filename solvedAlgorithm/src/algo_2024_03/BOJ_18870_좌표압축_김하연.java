package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18870_좌표압축_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;
    static int[] numbers;

    static List<Integer> numberList;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
    
        // N개의 좌표
        N=Integer.parseInt(br.readLine().trim());

        numbers=new int[N];
        HashSet<Integer> numberSet=new HashSet<>();

        st=new StringTokenizer(br.readLine().trim());
        for (int idx=0;idx<N;idx++){
            numbers[idx]=Integer.parseInt(st.nextToken());
            numberSet.add(numbers[idx]);
        }
        numberList=new ArrayList<>(numberSet);
        // 정렬한 후 이분 탐색
        Collections.sort(numberList);

        sb=new StringBuilder();
        for (int idx=0;idx<N;idx++){
            int result=binarySearch(0,numberList.size()-1,numbers[idx]);
            sb.append(result).append(" ");
        }
        System.out.print(sb);
    }

    public static int binarySearch(int start,int end,int target){
        int result=0;
        while (start<=end){
            int mid=(start+end)/2;
            if (numberList.get(mid)>target){
                end=mid-1;
            }
            else if(numberList.get(mid)<target){
                start=mid+1;
            }
            else if(numberList.get(mid)==target){
                result=mid;
                break;
            }
        }
        return result;
    }
}
