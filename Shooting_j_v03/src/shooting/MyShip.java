package shooting;

import java.awt.Font;
import java.awt.Graphics;

/******************************************************************************/
/*																			  */
/*自機　クラス																  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class MyShip extends Character {
    /**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	int startX;									//自機初期位置X
	int startY;									//自機初期位置Y

	int expTime;								//自機無敵時間

	boolean moveFlg;							//移動フラグ

	Font leftFont;

	/**************************************************************************/
	/* コンストラクタ														  */
	/**************************************************************************/
	MyShip() {
		this.x = Shooting_Canvas.BOARD_W / 2 - 16;			//自機X座標
		this.y = Shooting_Canvas.BOARD_H * 3 / 4 - 16;		//自機Y座標
		this.speed = 4;										//自機移動量
		this.startX = this.x;								//初期位置x
		this.startY = this.y;								//初期位置y
		this.imgNo = 2;										//画像

		this.hp = 3;										//自機残り

		this.moveFlg = true;								//移動フラグ

		leftFont = new Font("sansserif", Font.BOLD, 15);
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	//移動
	public void move(int x, int y) {
		if (this.expTime != 0)
		{
			this.expTime--;

			if (this.expTime > 200)
			{
				return;
			}
		}

		if (this.moveFlg == false)
		{
			this.y -= 2;

			if (this.y == this.startY)
			{
				this.moveFlg = true;
			}

			return;
		}

		if (y == 1)						//下移動
		{
			this.y += this.speed;
			if (this.y + 32 + this.speed > Shooting_Canvas.BOARD_H)
			{
				this.y = Shooting_Canvas.BOARD_H - 32;
			}

		}
		else if (y == -1)				//上移動
		{
			this.y -= this.speed;
			if (this.y - this.speed < 0)
			{
				this.y = 0;
			}
		}

		if (x == -1)					//左移動
		{
			this.x -= this.speed;
			if (this.x - this.speed < 0)
			{
				this.x = 0;
			}
		}
		else if (x == 1)				//右移動
		{
			this.x += this.speed;
			if (this.x + 32 + this.speed > Shooting_Canvas.BOARD_W)
			{
				this.x = Shooting_Canvas.BOARD_W - 32;
			}
		}
	}

	//描画処理
	@Override
	public void draw(Graphics g) {
		if (this.expTime == 0)
		{
			g.drawImage(Shooting_Main.charImg[this.imgNo], this.x, this.y, null);

			return;
		}

		if (this.expTime % 4 == 0)
		{
			g.drawImage(Shooting_Main.charImg[this.imgNo], this.x, this.y, null);
		}
	}

	@Override
	void move() {
		// TODO 自動生成されたメソッド・スタブ
	}
}