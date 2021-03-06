package shooting;

import java.awt.Graphics;

/*****************************************************************************/
/*																			  */
/*爆発　クラス																  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class Explosion extends Character {
    /**************************************************************************/
    /*フィールド															  */
    /**************************************************************************/
	
    /**************************************************************************/
    /*コンストラクタ 														  */
    /**************************************************************************/
	Explosion() {
		this.imgNo = 0;

		this.hp	   = 0;
	}
	
    /**************************************************************************/
    /*メソッド		 														  */
    /**************************************************************************/
	@Override
	void move() {
		// TODO 自動生成されたメソッド・スタブ
		this.time--;
		
		if (this.time == 0)
		{
			this.hp = 0;	//爆発消去
		}
	}

	@Override
	void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(Shooting_Main.charImg[this.imgNo], this.x, this.y, null);
	}
}
