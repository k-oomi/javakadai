
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		/*
		 * ■■Java課題（１７）■■　難易度★★★★★
数字を入力させ、符号とカンマを含む6桁の表示に変換する。
数字入力後、表示形式を、以下の３つを選択させ、表示する。
また、表示が6桁を超える場合はすべてに*を表示する。

1:（符号）00000
2:（符号）#,##0
3:（符号）#,###
符号はマイナスの数ならマイナス表示、プラスなら空白
		 */

		String[] str;
		str = new String[6];






		for(int i = 0; i < str.length; i++ ) {
		System.out.println("数値を入力してください");
		String input = new java.util.Scanner(System.in).nextLine();
		str[i] = input;

		}
		System.out.println("表示形式を選択");
		System.out.println("1:（符号）00000");
		System.out.println("2:（符号）#,##0");
		System.out.println("3:（符号）#,###");
		System.out.println("番号を入力してください");
		int t = new java.util.Scanner(System.in).nextInt();

		switch(t){

		case 1:
		for(String value : str) {

		}


		case 2:



		case 3:


		}

	}

}
