package monoris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/*****************************************************************************/
/*																			  */
/* キャンバス　クラス														  */
/*																			  */
/* Programinged by Pinsoft with Pintendo and Pintax_error 2019.				  */
/******************************************************************************/
public class MyCanvas extends JComponent implements Runnable, KeyListener {
	/**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	private final int OFFSET_Y = 48;
	private final int OFFSET_X = 98;

	private int score;

	private int nextBlockColor;
	private int nextBlockType;
	private int dropBlockColor;
	private int dropBlockType;
	private int dropPos;
	private int dropSpeed;
	private int blinkCount;

	private boolean blockOnFlg;
	private boolean dropEndFlg;

	private Font nextFont;
	private Font scoreFont;

	Thread th;
	
	int sp;

	/**************************************************************************/
    /* コンストラジュタ														  */
    /**************************************************************************/
	MyCanvas() {
        setFocusable(true);
        addKeyListener(this);
        
		// フォント設定
		nextFont = new Font("ＭＳ Ｐゴシック", Font.BOLD, 25);
		scoreFont = new Font("ＭＳ Ｐゴシック", Font.BOLD, 17);

		// Nextブロック設定
		setBlocks();
		blockOnFlg = false;

		// 落下スピード
		dropSpeed  = 2;
		
		sp = 36;

		//スレッドを開始する
		th = new Thread(this);
		th.start();
	}

	/**************************************************************************/
    /* スレッド																  */
    /**************************************************************************/
	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		while (true)
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gameMain();
				}
			});

			try {
				Thread.sleep(sp);
			} catch (InterruptedException e) {
			}

			repaint();
		}
	}

	/**************************************************************************/
    /* ゲームメイン															  */
    /**************************************************************************/
	public void gameMain() {
		if (!blockOnFlg) {
			setBlocks();

			blockOnFlg = true;
		} else {
			moveBlocks();
		}
	}

	/**************************************************************************/
    /* Nextブロック設定														  */
    /**************************************************************************/
	public void setBlocks() {
		dropBlockColor = nextBlockColor;
		dropBlockType = nextBlockType;

		nextBlockColor = (int)(Math.random() * 4);
		nextBlockType =  (int)(Math.random() * 4) + 1;
	}

	/**************************************************************************/
    /* ブロック落下															  */
    /**************************************************************************/
	public void moveBlocks() {
		if (!dropEndFlg) {	
			int checkWall = ((dropBlockType - 1) * 32 + dropPos) / 32;

			if (Main.filedData[checkWall + 1][1] != 1)
			{
				dropPos += dropSpeed;
			} else {
				dropEndFlg = true;
				
				blinkCount = 0;
				score += dropBlockType * 10;
				
				sp = 36;
			}
		} else {
			blinkCount++;

			if (blinkCount == 13) {
				blockOnFlg = false;
				dropEndFlg = false;

				dropPos = 0;
			}
		}
	}

	/**************************************************************************/
    /* 描画処理																  */
    /**************************************************************************/
	public void paintComponent(Graphics g) {
		/**********************************************************************/
		/* ゲームフィールド表示												  */
		/**********************************************************************/
		for (int y = 0; y < 13; y++) {
			for (int x = 0; x < 3; x++) {
				if (Main.filedData[y][x] == 1) {		// 壁ブロック
					g.drawImage(Main.blockImg[4],
							x * 32 + OFFSET_X, y * 32 + OFFSET_Y, this);
				}
			}
		}

		/**********************************************************************/
		/* 落下ブロック表示													  */
		/**********************************************************************/
		if (blockOnFlg && !dropEndFlg) {
			for (int i = 0; i < dropBlockType; i++) {
				g.drawImage(Main.blockImg[dropBlockColor],
						32 + OFFSET_X, i * 32 + OFFSET_Y + dropPos, this);
			}
		} else if (blinkCount % 7 < 3){
			for (int i = 0; i < dropBlockType; i++) {
				g.drawImage(Main.blockImg[dropBlockColor],
						32 + OFFSET_X, i * 32 + OFFSET_Y + dropPos, this);
			}
		}

		/**********************************************************************/
		/* Next表示															  */
		/**********************************************************************/
		// 文言
		g.setColor(Color.white);
		g.setFont(nextFont);
		g.drawString("Next", 230, 100);

		// ブロック
		for (int i = 0; i < nextBlockType; i++) {
			g.drawImage(Main.blockImg[nextBlockColor], 243, 110 + i * 32,this);
		}

		/**********************************************************************/
		/* スコア表示														  */
		/**********************************************************************/
		g.setColor(Color.white);
		g.setFont(scoreFont);
		g.drawString("SCORE : " + score, 15, 20);
	}

	/**************************************************************************/
    /* キー入力																  */
    /**************************************************************************/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if ((e.getKeyCode() == KeyEvent.VK_DOWN) && blockOnFlg && !dropEndFlg) {
			//dropSpeed = 8;
			sp = 5;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if ((e.getKeyCode() == KeyEvent.VK_DOWN) && blockOnFlg && !dropEndFlg) {
			//dropSpeed = 2;
			sp = 36;
		}
	}


}
