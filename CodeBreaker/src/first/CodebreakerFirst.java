package first;

public class CodebreakerFirst {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		String title = "Codebreaker";
		String rule = "隠された３つのすうじをあてます。";

		int[] answer = new int[3];
		int[] input = new int[3];

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
	}

}
