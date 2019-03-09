package shooting;

import java.awt.Graphics;
import java.util.Random;

/*****************************************************************************/
/*																			  */
/*ボス　クラス																  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class Boss extends Character{


	/**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	private final int BOSS_BULLET_MAX = 35;
	private final int BOSS_EXPLOSION_MAX = 5;
	private final int BOSS_HP = 20;

	boolean onBossFlg;										//ボス出現フラグ

	int bossExplosionCount;									//ボス爆発回数


	private Random r;

	/**************************************************************************/
	/* コンストラクタ														  */
	/**************************************************************************/
	Boss() {
		this.x = Shooting_Canvas.BOARD_W / 2 - 64;			//ボスX座標
		this.y = 0;											//ボスY座標
		this.speed = 4;										//ボス移動量
		this.imgNo = 1;										//ボス画像

		this.hp = BOSS_HP;									//ボスヒットポイント

		this.time = -1;

		this.onBossFlg = false;

		this.bossExplosionCount = 0;					//ボス爆発カウント

		this.point = 20;

		r = new Random();
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	//移動
	@Override
	void move() {
		// TODO 自動生成されたメソッド・スタブ
		double radX, radY;

		this.time++;

		if (this.time < 100)
		{
			//下移動
			this.y += 1;
		}
		else
		{
			//縦移動
			radY = (this.time - 50) * Math.PI / 180;
			this.y = (int)(70 + (Math.sin(radY) * 40));

			//横移動
			radX = (this.time - 100) * Math.PI / 180;
			this.x = (int)((Shooting_Canvas.BOARD_W / 2) + (Math.sin(radX) * 150) - 60);


			//弾発射
			//ボス弾発射タイミング合わせ
			if (this.time % 3 == 0)
			{
				initShotBossBullet();			//ボス弾発射初期処理
			}
		}
	}

	//ボス弾発射初期処理
	void initShotBossBullet() {
		for (int i = 0; i < BOSS_BULLET_MAX; i++)
		{
			if (ObjectManagement.enemyBullet[i].hp == 0)
			{
				ObjectManagement.enemyBullet[i].angle = (this.time * 4) % 360;	//弾角度

				ObjectManagement.enemyBullet[i].x = this.x + 48;				//X座標
				ObjectManagement.enemyBullet[i].y = this.y + 16;				//Y座標

				ObjectManagement.enemyBullet[i].imgNo = 4;						//ボス弾イメージ
				ObjectManagement.enemyBullet[i].hp = 1;							//ボス弾表示

				break;
			}
		}
	}

	//ボス爆発処理
	void bossExplosion() {
		this.time++;

		//ボス爆発タイミング
		if (this.time % 20 == 0)
		{
			this.bossExplosionCount++;

			//ボスwait
			if (this.bossExplosionCount == 10)
			{
				this.hp = 0;
				
				return;
			}
			else if (this.bossExplosionCount >= 6)
			{
				return;
			}

			//爆発設定
			for (int i = 0; i < BOSS_EXPLOSION_MAX; i++)
			{
				if (ObjectManagement.explosion[i].hp == 0)
				{
					ObjectManagement.explosion[i].hp = 1;
					ObjectManagement.explosion[i].x = this.x + r.nextInt(64) + 32; //ボス爆発X座標
					ObjectManagement.explosion[i].y = this.y + r.nextInt(32) + 16;  //ボス爆発X座標

					break;
				}
			}
		}

	}

	@Override
	void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(Shooting_Main.charImg[this.imgNo], this.x, this.y, null);
	}

}
