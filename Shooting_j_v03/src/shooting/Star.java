package shooting;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/*****************************************************************************/
/*																			  */
/* 星　クラス																  */
/*																			  */
/* Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class Star extends Character {
    /**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	static final int STAR_MAX = 10; 	//最大星数

	/**************************************************************************/
	/* コンストラクタ														  */
	/**************************************************************************/
	Star(int i) {
		Random rand = new Random();

		this.x = rand.nextInt(Shooting_Canvas.BOARD_W);	//星X座標
		this.y = rand.nextInt(Shooting_Canvas.BOARD_H);	//星Y座標;

		this.speed = (double)(i % 10 + 2) + 2;		//星移動量

		this.hp = 1;								//星描画
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	//移動処理
	@Override
	public void move() {
		this.y += this.speed;

		if (this.y > Shooting_Canvas.BOARD_H)
		{
			this.y = 0;
		}
	}

	//描画処理
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);			//白設定
		g.fillRect(this.x, this.y, 2, 2);	//ドット描画
	}
}
