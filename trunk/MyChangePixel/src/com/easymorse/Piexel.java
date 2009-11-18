package com.easymorse;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.Toast;
@SuppressWarnings("deprecation")
public class Piexel extends Activity {
	
	private float mX, mY;
	// ���� �� �� x y
	private int intWidth, intHeight, intDefaultX, intDefaultY;
	// ��ȡ��Ļ������
	private int intScreenX, intScreenY;
	private ImageView imageView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ʾͼƬ
		imageView = (ImageView) findViewById(R.id.image);

		// �õ���Ļ�Ķ���
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// ȡ����Ļ������
		intScreenX = dm.widthPixels;
		intScreenY = dm.heightPixels;
		/* ����ͼƬ�Ŀ�� */
		intWidth = 50;
		intHeight = 50;
		//��ʼ��ͼƬ��λ��
		RestoreButton();
		Log.e("pixel", "ping mu de xiang su +" + intScreenX + ";" + intScreenY);
	}

	/* ���Ǵ����¼� */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//ȡ����ָ������Ļ������
		float x = event.getX();
		float y = event.getY();
		Log.i("pixel", "ni chu muo wo le==" + x + ";" + y);
		try {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				picMove(x, y);
				break;
			case MotionEvent.ACTION_MOVE:
				picMove(x, y);
				break;
			case MotionEvent.ACTION_UP:
				picMove(x, y);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void RestoreButton() {
		intDefaultX = ((intScreenX - intWidth) / 2);
		intDefaultY = ((intScreenY - intHeight) / 2);
		//��ʾͼƬ��λ��
		imageView.setLayoutParams(new AbsoluteLayout.LayoutParams(intWidth,
				intHeight, intDefaultX, intDefaultY));
		MakeTextToast("��������ͼƬ��",true);
	}
	private void picMove(float x, float y) {
		// Ĭ��΢��ͼƬ��ָ������λ�� 
		mX = x - (intWidth / 2);
		mY = y - (intHeight / 2);
		if ((mX + intWidth) > intScreenX) {
			mX = intScreenX - intWidth;
		}
		else if (mX < 0) {
			mX = 0;
		}
		else if ((mY + intHeight) > intScreenY) {
			mY = intScreenY - intHeight;
		}
		else if (mY < 0) {
			mY = 0;
		}
		imageView.setLayoutParams(new AbsoluteLayout.LayoutParams(intWidth,
				intHeight, (int) mX, (int) mY));
	}
	  //��ʾ��Ϣ
	  public void MakeTextToast(String str, boolean isLong)
	  {
	    if(isLong==true)
	    {
	      Toast.makeText(Piexel.this, str, Toast.LENGTH_LONG).show();
	    }
	    else
	    {
	      Toast.makeText(Piexel.this, str, Toast.LENGTH_SHORT).show();
	    }
	  }
}