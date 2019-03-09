package shooting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/******************************************************************************/
/*																			  */
/*ゲームオーバー　クラス													  */
/*																			  */
/*Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class GameOver {
	/**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	Font goFont;
	
	int time;

	/**************************************************************************/
	/* コンストラクタ														  */
	/**************************************************************************/
	GameOver() {
		goFont = new Font("sansserif", Font.BOLD, 50);

		this.time = 0;
	}

    /**************************************************************************/
    /* メソッド																  */
    /**************************************************************************/
	public void drawGameOver(Graphics g)
	{
		g.setColor(Color.white);
		g.setFont(goFont);
		g.drawString("Game Over !" , 35, 250);
	}
}
