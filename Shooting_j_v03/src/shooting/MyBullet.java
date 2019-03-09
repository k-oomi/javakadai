package shooting;

import java.awt.Graphics;

/*****************************************************************************/
/*																			  */
/*自弾　クラス																  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class MyBullet extends Character {
    /**************************************************************************/
    /*フィールド															  */
    /**************************************************************************/

    /**************************************************************************/
    /*コンストラクタ 														  */
    /**************************************************************************/
	MyBullet() {
		this.speed = 8;			//速度
		this.imgNo = 5;			//画像
		this.hp = 0;			//非表示
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	//移動
	@Override
	public void move() {
		this.y -= this.speed;
		if (this.y < 0){
			this.hp = 0;		//非表示
		}
	}

	//描画処理
	@Override
	public void draw(Graphics g) {
		g.drawImage(Shooting_Main.charImg[this.imgNo], this.x, this.y, null);
	}

}
