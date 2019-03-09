package shooting;

import java.awt.Graphics;

/*****************************************************************************/
/*																			  */
/*敵弾　クラス																  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class EnemyBullet extends Character{
    /**************************************************************************/
    /*フィールド															  */
    /**************************************************************************/

    /**************************************************************************/
    /*コンストラクタ 														  */
    /**************************************************************************/
	EnemyBullet() {
		this.speed = 4;			//速度
		this.imgNo = 6;			//画像
		this.hp = 0;			//非表示
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	//移動
	@Override
	public void move() {
		double rad;

		rad = this.angle;

		this.x += (int)(Math.cos(rad) * this.speed);	//X座標
		this.y += (int)(Math.sin(rad) * this.speed);	//Y座標

		//画面外
		if (this.x < -16					 ||
			this.x > Shooting_Canvas.BOARD_W ||		
			this.y < -16					 ||
			this.y > Shooting_Canvas.BOARD_H)
		{
			this.hp = 0;				//敵弾消去
		}
	}

	//描画処理
	@Override
	public void draw(Graphics g) {
		g.drawImage(Shooting_Main.charImg[this.imgNo],
				this.x, this.y, null);
	}
}
