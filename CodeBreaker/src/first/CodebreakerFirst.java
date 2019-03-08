package first;

public class CodebreakerFirst {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		String title = "Codebreaker";
		String rule = "隠された３つのすうじをあてます。";

		int[] answer = new int[3];	//答えが入る
		int[] input = new int[3];//入力した答えが入る

		int hit = 0, blow = 0, count = 0;

		//タイトルとルールの表示
		System.out.println(title);
		System.out.println(rule);

		//ランダムの数字を生成
		for (int i = 0; i < answer.length; i++) {
			boolean flag = false;
			answer[i] = new java.util.Random().nextInt(7);
			do {
				flag = false;
				for (int j = i - 1; j >= 0; j--) {
					if (answer[i] == answer[j]) {
						flag = true;
						answer[i] = new java.util.Random().nextInt(7);
					}
				}
			} while (flag == true);
		}
		while (true) {
			count++;
			System.out.println("****　" + count + "回目　***");
			//インプット
			for(int i = 0; i < answer.length; i++) {
				System.out.print((i + 1) + "個目:");
				input[i] = new java.util.Scanner(System.in).nextInt();
			}
			//答え判断
			hit = 0;
			blow = 0;
			for(int i = 0; i < answer.length; i++) {
				for(int j = 0; j < answer.length; j++) {
					if(i == j && input[i] == answer[j]) {
						hit++;
					}else if(input[i] == answer[j]) {
						blow++;
					}
				}
			}
			//終了判断ヒットが三つになったら終了
			System.out.println("ヒット" + hit + "ブロー" + blow);
			if(hit == 3) {
				System.out.println("おめでとうー");
				break;
			}else {
				System.out.println("");
			}




		}

	}

}
