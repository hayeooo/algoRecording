package algo_2023_10_11;

/*
 * 소수를 판별하는 알고리즘
 * 소수들을 대량으로 빠르고 정확하게 구하는 방법
 * 가장 먼저 소수를 판별할 범위만큼 배열을 할당하여, 해당하는 값을 넣어주고 이후에 하나씩 지워나가는 방법
 * 
 */
public class SieveOfEratosthenes_김하연 {
	
	static int range=100000;		// 소수를 판별할 범위
	static int[] isPrime=new int[range+1];	// 소수라면 0이 아닌 값이 저장되어있다.
	
	public static void main(String[] args) {
		primeNumberSieve();
	}
	
	public static void primeNumberSieve() {
		
		// 배열을 초기화한다.
		for (int num=2;num<=range;num++) {
			isPrime[num]=num;
		}
		
		// 2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.
		for (int num=2;num<=range;num++) {
			if(isPrime[num]==0) continue;
			
			// 배수인 경우 소수가 아니므로 0을 대입한다.
			for (int multiple=2*num;multiple<=range;multiple+=num) {
				isPrime[multiple]=0;
			}
		}
		
		// 소수인 숫자를 출력한다.
		for (int num=2;num<=range;num++) {
			if (isPrime[num]!=0) {
				System.out.println(isPrime[num]);
			}
		}
	}

}
