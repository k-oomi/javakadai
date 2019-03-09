package shooting;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/******************************************************************************/
/*																			  */
/* キー入力　クラス															  */
/*																			  */
/* Programinged by Pinsoft with Pintendo and Pintaxerror 2018.				  */
/******************************************************************************/
public class KeyContainer implements KeyListener {
	/**************************************************************************/
    /* フィールド															  */
    /**************************************************************************/
	//移動方向
	boolean blUp;
	boolean blDown;
	boolean blRight;
	boolean blLeft;
	
	//弾発射
	boolean blSpace;
	
	/**************************************************************************/
	/* コンストラクタ														  */
	/**************************************************************************/
	KeyContainer()
	{
		//移動方向
		blUp	= false;
		blDown	= false;
		blRight = false;
		blLeft  = false;
		
		//弾発射
		blSpace = false;
	}
	/**************************************************************************/
	/* 左右移動																  */
	/**************************************************************************/
	public int getX()
	{
		return (blRight ? 1 : 0) - (blLeft ? 1 : 0);	// 右：1　左：-1
	}

	/**************************************************************************/
	/*上下移動																  */
	/**************************************************************************/
	public int getY()
	{
		return (blDown ? 1 : 0) - (blUp ? 1 : 0);	// 下：1　上：-1
	}
	
	/**************************************************************************/
	/*上下移動																  */
	/**************************************************************************/
	public boolean getSpace()
	{
		return blSpace;
	}

    /**************************************************************************/
    /* キーを押している時													  */
    /**************************************************************************/
    public void keyPressed(KeyEvent e){
    	
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT)
		{
			blLeft = true;
		}
		if (keycode == KeyEvent.VK_RIGHT)
		{
			blRight = true;
		}
		if (keycode == KeyEvent.VK_UP)
		{
			blUp = true;
		}
		if (keycode == KeyEvent.VK_DOWN)
		{
			blDown = true;
		}
		if (keycode == KeyEvent.VK_SPACE)
		{
			blSpace = true;
		}
		
		if (keycode == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
	}

    /**************************************************************************/
    /* キーが放された瞬間													  */
    /**************************************************************************/
    public void keyReleased(KeyEvent e){
    	
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT)
		{
			blLeft = false;
		}
		if (keycode == KeyEvent.VK_RIGHT)
		{
			blRight = false;
		}
		if (keycode == KeyEvent.VK_UP)
		{
			blUp = false;
		}
		if (keycode == KeyEvent.VK_DOWN)
		{
			blDown = false;
		}
		if (keycode == KeyEvent.VK_SPACE)
		{
			blSpace = false;
		}

    }

    /**************************************************************************/
    /* キーが押された瞬間													  */
    /**************************************************************************/
    public void keyTyped(KeyEvent e){
    }
}
