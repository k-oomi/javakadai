package shooting;

import java.awt.Graphics;

/*****************************************************************************/
/*																			  */
/*キャラクター　クラス														  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public abstract class Character {

    /**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	protected int x;						//X座標
	protected int y;						//X座標
	protected double speed;					//移動量
	protected double angle;					//角度

	protected int hp;						//HP
	protected int time;						//経過時間

	protected int imgNo;					//イメージNo

	protected int point;					//点数

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	//移動
	abstract void move();

	//描画
	abstract void draw(Graphics g);

    /**************************************************************************/
    /* アクセッサ															  */
    /**************************************************************************/
/*
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
*/
}
