import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	static int[] arr = new int[(int) Math.pow(10, 6) + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int N = Integer.parseInt(br.readLine());
		arr[1] = 0;
		arr[2] = 1;
		arr[3] = 1;
		for (int i = 4; i <= (int) Math.pow(10, 6); i++) {
			if (i % 3 == 0 && i % 2 == 0)
				arr[i] = Math.min(arr[i / 3] + 1, arr[i / 2] + 1);
			else if (i % 3 == 0)
				arr[i] = Math.min(arr[i / 3] + 1, arr[i-1] + 1);
			else if (i % 2 == 0)
				arr[i] = Math.min(arr[i / 2] + 1, arr[i-1] + 1);
			else
				arr[i] = arr[i-1]+1;
		}
		br.close();
		System.out.print(arr[N]);
	}
}
