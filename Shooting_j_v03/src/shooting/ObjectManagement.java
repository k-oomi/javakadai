package shooting;

import java.awt.Graphics;
import java.util.Random;

/*****************************************************************************/
/*																			  */
/* オブジェクト管理　クラス													  */
/*																			  */
/* Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class ObjectManagement {
    /**************************************************************************/
    /*フィールド															  */
    /**************************************************************************/
	MyShip	 myShip;	//自機インスタンス
	Boss	 boss;		//ボスインスタンス
	Score	 score;		//スコアインスタンス
	Left	 left;		//残りインスタンス
	GameOver gameOver;	//ゲームオーバーインスタンス

	static Star[]		 star;			//星インスタンス配列
	static MyBullet[]	 myBullet;		//自弾インスタンス配列
	static Enemy[]		 enemy;			//敵インスタンス配列
	static EnemyBullet[] enemyBullet;	//敵弾インスタンス配列
	static Explosion[]	 explosion;		//爆発インスタンス配列

	private Random r;

	int gameMainTimer;

	boolean gamePlayFlg;

    /**************************************************************************/
    /*定数																	  */
    /**************************************************************************/
	final int MY_SHOT_TIMING   = 15;	//自弾発射タイミング
	final int MY_BULLET_MAX	   = 3;		//自弾最大数
	final int STAR_MAX		   = 10; 	//星最大数
	final int ENEMY_MAX		   = 5;		//敵最大数
	final int ENEMY_BULLET_MAX = 35;	//敵弾最大数
	final int SET_BOSS_TIME	   = 30;	//ボス出現時間
	final int BOSS_EXPLOSION   = 5;		//ボス爆発回数
	final int EXPLOSION_MAX    = 6;		//爆発数
	final int MY_EXP_TIME	   = 250;	//自機被弾時間

    /**************************************************************************/
    /*コンストラクタ														  */
    /**************************************************************************/
	ObjectManagement() {
		myShip   = new MyShip();		//自機作成
		score	 = new Score();			//スコア作成
		left	 = new Left(myShip);	//残り作成
		gameOver = new GameOver();		//ゲームオーバー作成

		//星配列作成
		star = new Star[STAR_MAX];
		for (int i = 0; i < STAR_MAX; i++)
		{
			star[i] = new Star(i);
		}

		init();						//初期設定　ボス　敵　敵弾　自弾　爆発

		r = new Random();
	}

    /**************************************************************************/
    /*メソッド																  */
    /**************************************************************************/
	//描画。すべてのゲームオブジェクトを描画する。
	public void drawAll(Graphics g) {
        doGameObjects(g, star);
	    doGameObjects(g, myBullet);
	    doBossObject(g, boss);
	    doGameObjects(g, enemyBullet);
	    doGameObjects(g, enemy);

		myShip.draw(g);
	
		doGameObjects(g, explosion);
	}

	//HP有りのオブジェクト移動・描画
    public void doGameObjects(Graphics g, Character[] objAry) {
        for (int i = 0; i < objAry.length; i++) {

            if (objAry[i].hp > 0)
			{
                objAry[i].move();
                objAry[i].draw(g);				//委譲
            }
        }
    }

	//ボス移動・描画
    public void doBossObject(Graphics g, Boss boss) {
    	if (boss.onBossFlg == false)
    	{
    		return;
    	}

    	if (boss.hp > 0)
		{
    		//通常時
    		boss.move();
    		boss.draw(g);				//委譲
        }
    	else if (boss.hp < 0)
    	{
    		//爆発時
    		boss.bossExplosion();
    		boss.draw(g);				//委譲

    		if (boss.bossExplosionCount == 10)
    		{
    			init();					//初期化
    		}
    	}
    }

	//自機移動処理
	public void moveMyShip(KeyContainer inKey) {
		if (gameOver.time > 0)
		{
			return;
		}
		
		myShip.move(inKey.getX(), inKey.getY());
		myShip.time++;
	}

	//自機弾発射処理
	public void shotMyShip(KeyContainer inKey) {
		//自機有
		if (myShip.hp > 0					&&
			inKey.getSpace()				&&
			boss.hp  > 0					&&
			myShip.moveFlg == true			&&
			(myShip.time % MY_SHOT_TIMING == 0))
		{
			shotMyBullet();		//自弾発射処理
		}
	}

	//自弾発射処理
	public void shotMyBullet() {
		for (int i = 0; i < MY_BULLET_MAX; i++)
		{
			if (myBullet[i].hp == 0)
			{
				myBullet[i].hp = 1;
				myBullet[i].x  = myShip.x + 12;
				myBullet[i].y  = myShip.y + 8;

				break;
			}
		}
	}

	//ボス敵出現処理
	public void onBossEnemy() {
		if (gameOver.time > 0)
		{
			return;
		}
		
		gameMainTimer--;

		if (gameMainTimer > 0)
		{
			if (r.nextInt(100) == 0)
			{
				onEnemy();
			}
		}
		else if (gameMainTimer == 0)				//ボス出現初期処理
		{
			boss.onBossFlg = true;
		}
	}

	//敵出現処理
	public void onEnemy() {
		for (int i = 0; i < ENEMY_MAX; i++)
		{
			if (enemy[i].hp ==0)
			{
				enemy[i].hp = 1;
				enemy[i].x = (r.nextInt(200) -100 + Shooting_Canvas.BOARD_W / 2) - 16;	//X座標
				enemy[i].y = -16;										//Y座標

				break;
			}
		}
	}

	//全当り判定
	public void allHitCheck() {
		//ゲームオーバーなら
		if (gameOver.time > 0)
		{
			return;
		}
		
		hitCheckMyBullet();		//自弾当り判定
		hitEnemyBullet();		//敵弾当り判定
		hitMyEnemyBoss();		//自機敵ボス当り判定
	}

	//自弾当り判定
	public void hitCheckMyBullet() {
		for (int i = 0; i < MY_BULLET_MAX; i++)
		{
			//敵当り判定
			for (int j = 0; j < ENEMY_MAX; j++)
			{
				if (myBullet[i].hp  == 1				  &&
					enemy[j].hp		== 1				  &&
					enemy[j].x	    <= myBullet[i].x + 4  &&
					enemy[j].x + 32 >= myBullet[i].x + 4  &&
					enemy[j].y	    <= myBullet[i].y + 4  &&
					enemy[j].y + 32 >= myBullet[i].y + 4)
				{
					enemy[j].hp = 0;							//敵消去
					myBullet[i].hp = 0;							//自弾消去

					setExplosion(enemy[j].x, enemy[j].y, 5);	//爆発設定

					score.score += 20;							//スコア加算

					break;
				}
			}

			//ボス当り判定
			if (boss.onBossFlg == true				&&
				myBullet[i].hp == 1			  	    &&
				boss.x		   <= myBullet[i].x + 4 &&
				boss.x + 128   >= myBullet[i].x + 4 &&
				boss.y		   <= myBullet[i].y + 4 &&
				boss.y + 64    >= myBullet[i].y + 4)
			{
				boss.hp--;				//ボスHP減産

				myBullet[i].hp = 0;		//自弾消去

				score.score += 10;		//スコア加算

				//ボスHPが0なら
				if (boss.hp == 0)
				{
					boss.time = 0;		//ボス爆発時間設定
					boss.hp = -1;

					//敵弾クリア
					for (int j = 0; j < ENEMY_BULLET_MAX; j++)
					{
						enemyBullet[j].hp = 0;
					}

					//爆発クリア
					for (int j = 0; j < EXPLOSION_MAX; j++)
					{
						explosion[j].hp = 0;
					}

					score.score += 100;		//スコア加算
				}
			}
		}
	}

	//敵弾当り判定
	public void hitEnemyBullet() {
		int offset_XY;
		
		//自機被弾中なら
		if (myShip.expTime != 0)
		{
			return;
		}

		for (int i = 0; i < ENEMY_BULLET_MAX; i++)
		{
			if (enemyBullet[i].hp == 0)
			{
				continue;
			}

			if (enemyBullet[i].imgNo == 6)
			{
				offset_XY = 4;		//敵弾
			}
			else
			{
				offset_XY = 8;		//ボス弾
			}

			if (myShip.x	  <= enemyBullet[i].x + offset_XY &&
				myShip.x + 32 >= enemyBullet[i].x + offset_XY &&
				myShip.y	  <= enemyBullet[i].y + offset_XY &&
				myShip.y + 32 >= enemyBullet[i].y + offset_XY)
			{
				myShip.hp -= 1;				//自機HP減算

				//ゲームオーバーなら
				if (myShip.hp == 0)
				{
					myShip.time = 0;
					gameOver.time = 1;
				}
				else
				{
					myShip.expTime = MY_EXP_TIME;
				}

				enemyBullet[i].hp = 0;		//敵弾消去

				setExplosion(myShip.x, myShip.y, 25);//自機爆発

				//自機出現位置
				myShip.x = myShip.startX;
				myShip.y = Shooting_Canvas.BOARD_H;
				myShip.moveFlg = false;
			}
		}
	}

	//自機敵ボス当り判定
	public void hitMyEnemyBoss() {
		//自機被弾中なら
		if (myShip.expTime != 0)
		{
			return;
		}

		//自機敵当り判定
		for (int i = 0; i < ENEMY_MAX; i++)
		{
			if (myShip.hp > 0							&&
				enemy[i].hp == 1						&&
				enemy[i].x + 8      < myShip.x + 32 - 8	&&
				enemy[i].x + 32 - 8 > myShip.x + 8		&&
				enemy[i].y + 8      < myShip.y + 32 - 8	&&
				enemy[i].y + 32 - 8 > myShip.y + 8 )
			{
				enemy[i].hp = 0;
				setExplosion(enemy[i].x, enemy[i].y, 5);		//敵爆発設定

				myShip.hp -= 1;
				setExplosion(myShip.x, myShip.y, 25);//自機爆発

				//自機出現位置
				myShip.x = myShip.startX;
				myShip.y = Shooting_Canvas.BOARD_H;
				myShip.moveFlg = false;

				//ゲームオーバーなら
				if (myShip.hp == 0)
				{
					myShip.time = 0;
					gameOver.time = 1;

					return;
				}
				else
				{
					myShip.expTime = MY_EXP_TIME;
				}
			}
		}

		//自機ボス当り判定
		if (myShip.hp   	 > 0				  &&
			boss.x + 8		 < myShip.x + 32  - 8 &&
			boss.x + 128 - 8 > myShip.x + 8		  &&
			boss.y + 8		 < myShip.y + 32  - 8 &&
			boss.y + 64  - 8 > myShip.y + 8)
		{
			myShip.hp = 0;		//ゲームオーバー
			myShip.time = 0;
			gameOver.time = 1;
		}
	}


	//爆発初期設定
	public void setExplosion(int x, int y, int t) {
		for (int i = 0; i < EXPLOSION_MAX; i++)
		{
			if (explosion[i].hp == 0)
			{
				explosion[i].x = x - 16;	//爆発位置設定
				explosion[i].y = y - 16;
				explosion[i].time = t;
				explosion[i].hp = 1;				//爆発表示

				break;
			}
		}
	}

	//初期設定
	void init() {
		boss   = new Boss();		//ボス作成

		//自弾配列作成
		myBullet = new MyBullet[MY_BULLET_MAX];
		for (int i = 0; i < MY_BULLET_MAX; i++)
		{
			myBullet[i] = new MyBullet();
		}

		//敵配列作成
		enemy = new Enemy[ENEMY_MAX];
		for (int i = 0; i < ENEMY_MAX; i++)
		{
			enemy[i] = new Enemy(myShip);
		}

		//敵弾配列作成
		enemyBullet = new EnemyBullet[ENEMY_BULLET_MAX];
		for (int i = 0; i < ENEMY_BULLET_MAX; i++)
		{
			enemyBullet[i] = new EnemyBullet();
		}

		//爆発配列作成
		explosion = new Explosion[EXPLOSION_MAX];
		for (int i = 0; i < EXPLOSION_MAX; i++)
		{
			explosion[i] = new Explosion();
		}

		gameMainTimer = 60 * SET_BOSS_TIME;

		if (myShip.hp == 0)
		{
			myShip.hp = 3;
			score.score = 0;
		}
	}

	//スコア表示
	public void drawScore(Graphics g) {
		score.drawScore(g);
	}

	//残り表示
	public void drawLeft(Graphics g) {
		left.drawLeft(g);
	}
	
	//残り表示
	public void drawGameOver(Graphics g) {
		if (gameOver.time == 0)
		{
			return;
		}
		
		gameOver.drawGameOver(g);
		
		gameOver.time++;
		
		if (gameOver.time == 400)
		{
			gameOver.time = 0;
			
			init();				//初期設定
		}
	}



}
