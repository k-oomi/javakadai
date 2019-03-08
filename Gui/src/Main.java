import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		JFrame frame = new JFrame("テスト");
		frame.setSize(300,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		Container contentPane = frame.getContentPane();
		JLabel label = new JLabel("1億円ほしい");
		label.setHorizontalAlignment(JLabel.CENTER);
		JButton button = new JButton("ok");
		ImageIcon icon = new ImageIcon("C:\\Users\\usada\\OneDrive\\デスクトップ");
		JLabel picture = new JLabel(icon);



		contentPane.add(picture,BorderLayout.CENTER);
		contentPane.add(label,BorderLayout.NORTH);
		contentPane.add(button,BorderLayout.SOUTH);








		frame.setVisible(true);
	}

}
