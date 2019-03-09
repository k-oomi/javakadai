package shooting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/******************************************************************************/
/*																			  */
/*弾幕シューティングゲーム													  */
/*																			  */
/* Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class Shooting_Main extends Frame {
	
    /**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	final int BOARD_W		     = 360;		//ウィンドウの初期値
	final int BOARD_H		     = 480;

	//キャラクタ
	private BufferedImage charAllImg;
	static  BufferedImage charImg[] = new BufferedImage[7];

    /**************************************************************************/
    /* メイン処理															  */
    /**************************************************************************/
	public static void main(String[] args){
		new Shooting_Main();
	}

	/**************************************************************************/
    /* コンストラクタ														  */
    /**************************************************************************/
	Shooting_Main() {
		super("Shooting Game for Java");				//親クラスであるFrameにタイトルを表示
        this.setBounds(300, 200, BOARD_W + 16, BOARD_H + 38);	//フレームサイズ360 480
		setBackground(Color.BLACK);						//背景色黒

		setLayout(new BorderLayout());
		Shooting_Canvas sc1 = new Shooting_Canvas();
		add("Center", sc1);

        this.addWindowListener(new WinAdapter());		//クローズボタン

        setVisible(true);								//フレーム表示
        
        loadCharaData();								//画像読込
	}

	/**************************************************************************/
	/* 画像読込処理															  */
	/**************************************************************************/
	public void loadCharaData()
	{
        //マップイメージ取得
        try {
    		File openFile = new File ("img/shooting_all.png");
    		this.charAllImg = ImageIO.read(openFile);
    	} catch (IOException ex) {
    			System.out.println("Image Miss");
    	}

        //爆発
		Shooting_Main.charImg[0] = new BufferedImage(64, 64, this.charAllImg.getType());
		Shooting_Main.charImg[0] = this.charAllImg.getSubimage(0, 0, 64, 64);

		//ボス
		Shooting_Main.charImg[1] = new BufferedImage(128, 64, this.charAllImg.getType());
		Shooting_Main.charImg[1] = this.charAllImg.getSubimage(64, 0, 128, 64);

		//自機
		Shooting_Main.charImg[2] = new BufferedImage(32, 32, this.charAllImg.getType());
		Shooting_Main.charImg[2] = this.charAllImg.getSubimage(0, 64, 32, 32);

		//敵機
		Shooting_Main.charImg[3] = new BufferedImage(32, 32, this.charAllImg.getType());
		Shooting_Main.charImg[3] = this.charAllImg.getSubimage(32, 64, 32, 32);

		//ボス弾
		Shooting_Main.charImg[4] = new BufferedImage(16, 16, this.charAllImg.getType());
		Shooting_Main.charImg[4] = this.charAllImg.getSubimage(64, 64, 16, 16);

		//自弾
		Shooting_Main.charImg[5] = new BufferedImage(8, 16, this.charAllImg.getType());
		Shooting_Main.charImg[5] = this.charAllImg.getSubimage(80, 64, 8, 16);

		//敵弾
		Shooting_Main.charImg[6] = new BufferedImage(8, 8, this.charAllImg.getType());
		Shooting_Main.charImg[6] = this.charAllImg.getSubimage(88, 64, 8, 8);
	}
}

/*****************************************************************************/
/*																			 */
/* ウインドウアダプター　クラス												 */
/*																			 */
/*****************************************************************************/
class WinAdapter extends WindowAdapter {

	//ウィンドウクローズ
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }
}