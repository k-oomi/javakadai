import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * ■■Java課題（２３）■■　難易度★★★★
		20個の整数値を入力させた後、昇順に並べ替えをして空白で一行表示する。
		並べ替えは隣接交換法でプログラミングをすること。
		その後、探索したい整数値を一つ入力させ、一致したかしなかったかを表示する。
		一致した場合は、小さい数から何番目かを表示させる。
		また、探索は線形探索ではなく、二分探索でプログラミングすること。
		*/
		Scanner scanner = new Scanner(System.in);

		int lenght = 8;
		int t = 0;
		int low = 0;
		int mid;


		int[] score = new int[lenght];
		int hi = score.length-1;

		for (int i = 0; i < lenght; i++) {

			System.out.println("整数値を入力");
			int num = new java.util.Scanner(System.in).nextInt();
			score[i] = num;

		}

		for (int value : score) {
			System.out.print(value + "　");
		}

		System.out.println("");
//隣接交換法
		for (int j = score.length; j > 0; j--) {
			for (int i = 0; i < score.length-1; i++) {
				if (score[i] > score[i + 1]) {
					t = score[i + 1];
					score[i + 1] = score[i];
					score[i] = t;
				}
			}
		}


		for (int value : score) {
			System.out.print(value + "　");

		}
		System.out.println("");
		System.out.println("探索値を入力");
		int k = new java.util.Scanner(System.in).nextInt();





		System.out.println("");



		while (low <= hi) {
			mid = (low + hi)/2;

			if(score[mid] == k) {
				t = mid;
				break;
			}
			if (score[mid] > k){
				hi = mid-1;

			}else {
				low = mid+1;
			}
			if(score[mid] != k) {
				t = -1;
			}

		}


		if(t < 0) {
			System.out.println("一致するものはありません");
		}else {
			System.out.println(t+1 + "番目の数字と一致");
		}



		System.out.println("");
		System.out.println("end");

	}

}
