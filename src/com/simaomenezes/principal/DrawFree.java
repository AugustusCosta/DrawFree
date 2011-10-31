package com.simaomenezes.principal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
/*
 * Autor Simão Neto
 * Data de Inicio: 27-02-2011
 * Prototipo de assinatura
 * 
 * Essa app recebe dados do touch e desenha os no canvas
 * 
 * */

public class DrawFree extends Activity implements OnTouchListener {
//Atribtos usados
	ImageView imageView;
	Bitmap bitmap;
	Paint paint;
	Canvas canvas;
	
	float downx = 0;
	float downy = 0;
	float upx = 0;
	float upy = 0;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Objeto usado para salvar a img
        imageView = (ImageView) findViewById(R.id.ImageView);
		
		Display curretDisplay = getWindowManager().getDefaultDisplay();
		float dw = curretDisplay.getWidth();
		float dh = curretDisplay.getHeight();
		
		bitmap = Bitmap.createBitmap((int)dw, (int)dh, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);//canvas recebe a imagem que será desenhada
		paint = new Paint();
		paint.setColor(Color.WHITE);
		
		imageView.setImageBitmap(bitmap);//o wigdet revebe a imagem que foi desenhada
		imageView.setOnTouchListener(this);
    }
		
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		int action = event.getAction();
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			break;
			
		case MotionEvent.ACTION_MOVE:
			upx = event.getX();
			upy = event.getY();
			canvas.drawLine(downx, downy, upx, upy, paint);
			imageView.invalidate();
			downx = upx;
			downy = upy;
			break;
			
		case MotionEvent.ACTION_UP:
			upx = event.getX();
			upy = event.getY();
			canvas.drawLine(downy, downy, upx, upy, paint);
			break;
			
		case MotionEvent.ACTION_CANCEL:
			break;			
			
		default:
			break;
		}
		return true;
	}
}