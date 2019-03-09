package shooting;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

/*****************************************************************************/
/*																			  */
/*キャンバス　クラス														  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
class Shooting_Canvas extends Canvas implements Runnable {

    /**************************************************************************/
    /*フィールド															  */
    /**************************************************************************/
	final static int BOARD_W = 360;			//ウィンドウの初期値
	final static int BOARD_H = 480;

	final int TIMERINTERVAL = 20;		//タイマー用変数

	KeyContainer 	inKey;				//キー入力クラスインスタンス

	Image		offscreenImage;			// オフスクリーンイメージ
	Graphics	offscreenG;				// オフスクリーンオブジェクト

	Thread th;							//スレッド

	ObjectManagement om;				//オブジェクト管理オブジェクト

	Score score;						//スコア

	Random r;

	/**************************************************************************/
	/*コンストラクタ														  */
	/**************************************************************************/
	Shooting_Canvas() {

		inKey = new KeyContainer();				//キー入力インスタンス作成

        this.addKeyListener(inKey);				//キー入力リスナー実装
        this.requestFocus();					//フォーカス取得

		offscreenImage = null;					//オフスクリーン
		offscreenG	   = null;

		om = new ObjectManagement();

		score = new Score();

		r = new Random();

		//スレッドを開始する
		th = new Thread(this);
		th.start();
	}

	/**************************************************************************/
	/*スレッド																  */
	/**************************************************************************/
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(TIMERINTERVAL);
			}
			catch (InterruptedException e)
			{
			}

			repaint();		// 再描画
		}
	}

    /**************************************************************************/
    /*描画処理																  */
    /**************************************************************************/
	public void paint(Graphics g) {

		// オフスクリーン
		if (offscreenImage == null)
		{
			offscreenImage = createImage(BOARD_W + 16, BOARD_H + 38);
			offscreenG = offscreenImage.getGraphics();
		}

		/**********************************************************************/
		/* 背景塗りつぶし													  */
		/**********************************************************************/
		offscreenG.setColor(Color.black);
		offscreenG.fillRect(0, 0, BOARD_W + 16, BOARD_H + 38);

		/**********************************************************************/
		/*全オブジェクト描画												  */
		/**********************************************************************/
		om.drawAll(offscreenG);

		/**********************************************************************/
		/*自機移動															  */
		/**********************************************************************/
		om.moveMyShip(inKey);
		om.shotMyShip(inKey);

		/**********************************************************************/
		/*ボス敵出現														  */
		/**********************************************************************/
		om.onBossEnemy();

		/**********************************************************************/
		/*当り判定															  */
		/**********************************************************************/
		om.allHitCheck();

		/**********************************************************************/
		/*スコア描画														  */
		/**********************************************************************/
		om.drawScore(offscreenG);

		/**********************************************************************/
		/*残り描画															  */
		/**********************************************************************/
		om.drawLeft(offscreenG);

		/**********************************************************************/
		/*ゲームオーバー描画												  */
		/**********************************************************************/
		om.drawGameOver(offscreenG);
		
		/**********************************************************************/
		/*オフスクリーン描画												  */
		/**********************************************************************/
		g.drawImage(offscreenImage, 0, 0, this);

	}

    /**************************************************************************/
    /*アップデート															  */
    /**************************************************************************/
    public void update(Graphics g)
    {
    	paint(g);
    }

}
