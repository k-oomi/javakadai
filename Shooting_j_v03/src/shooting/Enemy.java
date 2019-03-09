package shooting;

import java.awt.Graphics;
import java.util.Random;

/*****************************************************************************/
/*																			  */
/*敵　クラス																  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class Enemy extends Character{

    /**************************************************************************/
    /*フィールド															  */
    /**************************************************************************/
	MyShip myShip;				//自機インスタンスコピー用

	Random r;

    /**************************************************************************/
    /*定数																	  */
    /**************************************************************************/
	final int ENEMY_BULLET_MAX = 35;	//敵弾最大数

    /**************************************************************************/
    /*コンストラクタ 														  */
    /**************************************************************************/
	Enemy(MyShip myShip) {
		this.hp = 0;
		this.imgNo = 3;

		this.myShip = myShip;

		this.point = 20;

		r = new Random();
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	@Override
	void move() {
		// TODO 自動生成されたメソッド・スタブ
		this.time += 1;

		//ジグザグ移動処理
		this.x += (this.time % 200) / 100 * 2 - 1;
		this.y += 2;

		//弾発射
		if (r.nextInt(100) == 0 && this.y < Shooting_Canvas.BOARD_H - 32)
		{
			shotEnemyBullet();
		}

		//敵範囲外
		if (this.y > Shooting_Canvas.BOARD_H)
		{
			this.hp = 0;			//敵消去
		}
	}

	@Override
	void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(Shooting_Main.charImg[this.imgNo], this.x, this.y, null);
	}

	//敵弾発射処理
	public void shotEnemyBullet() {
		double dx, dy;

		for (int i = 0; i < ENEMY_BULLET_MAX; i++)
		{
			if (ObjectManagement.enemyBullet[i].hp == 0)
			{
				dx = (myShip.x + 16) - (this.x + 16);
				dy = (myShip.y + 16) - (this.y + 16);
				ObjectManagement.enemyBullet[i].angle = Math.atan2(dy, dx);
				ObjectManagement.enemyBullet[i].x  = this.x + 12;
				ObjectManagement.enemyBullet[i].y  = this.y + 16;
				ObjectManagement.enemyBullet[i].imgNo = 6;
				ObjectManagement.enemyBullet[i].hp = 1;

				break;
			}
		}
	}

}
