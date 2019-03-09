package monoris;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/******************************************************************************/
/*																			  */
/* モノリス　Monoris														  */
/*																			  */
/* Programinged by Pinsoft with Pintendo and Pintax_error 2019.				  */
/******************************************************************************/
public class Main extends JFrame{

    /**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	//ブロックイメージ
	private BufferedImage blockAllImg;
	static  BufferedImage blockImg[] = new BufferedImage[5];

	//ゲームフィールド配列
	static int filedData[][] = {
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 1, 1}
	};

	 /**************************************************************************/
    /* メイン																  */
    /**************************************************************************/
	public static void main(String[] args) {
		Main frame = new Main();
	    frame.setVisible(true);
	}

	/**************************************************************************/
    /* コンストラクタ														  */
    /**************************************************************************/
	Main() {
		setTitle("モノリス　Monoris");
	    setBounds(400, 100, 320, 518);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setBackground(Color.BLACK);

	    MyCanvas mc = new MyCanvas();
		getContentPane().add(mc);

	    loadBlockImage();				// イメージ読込
	}

	/**************************************************************************/
	/* 画像読込処理															  */
	/**************************************************************************/
	public void  loadBlockImage() {
		// イメージ取得
        try {
    		File openFile = new File ("img/block.jpg");
    		this.blockAllImg = ImageIO.read(openFile);
    	} catch (IOException ex) {
    			System.out.println("Image Miss");
    	}

        // イメージ切分
        for (int i = 0; i < 5; i++) {
        	Main.blockImg[i] = new BufferedImage(32, 32, this.blockAllImg.getType());
            Main.blockImg[i] = this.blockAllImg.getSubimage(i * 32, 0, 32, 32);
        }
	}

}
