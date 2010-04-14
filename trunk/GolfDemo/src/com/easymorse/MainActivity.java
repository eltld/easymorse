package com.easymorse;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener {

	static final int FLING_MIN_DISTANCE = 120, FLING_MIN_VELOCITY = 200;

	private String[][] subTitles = { { "���������Ϣ", "getEngineInfo" },{"�������","getControlInfo"},
			{ "���ٺ��ͺ�", "getSpeedInfo" } ,{"���κ�����","getShapeInfo"}};

	private ViewFlipper flipper;

	private GestureDetector detector;

	private List<Car> cars;

	{
		cars = new ArrayList<Car>();

		Car car = new Car();
		car.setType("�߶��� 6 1.6L \n �ֶ�ʱ����");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.6L ֱ���ĸ�4��������ȼ������");
		car.getEngineInfo().add("���������� 77KW/5600RPM");
		car.getEngineInfo().add("������Ť�� 155ţ.��/3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("��������5���ֶ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.setSpeedInfo(new ArrayList<String>());
		car.getSpeedInfo().add("���ʱ�٣�185KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬11.9S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.6L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.9L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1275KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);

		car = new Car();
		car.setType("�߶��� 6 1.6L \n �Զ�ʱ����");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.6L ֱ���ĸ�4��������ȼ������");
		car.getEngineInfo().add("���������� 77KW/5600RPM");
		car.getEngineInfo().add("������Ť�� 155ţ.��/3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("�����������˶�ģʽ��7��λ/�Զ�һ��DSG˫����Զ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.setSpeedInfo(new ArrayList<String>());
		car.getSpeedInfo().add("���ʱ�٣�180KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬11.8S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.9L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.6L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1295KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);
		
		car = new Car();
		car.setType("�߶��� 6 1.6L \n �ֶ�������");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.6L ֱ���ĸ�4��������ȼ������");
		car.getEngineInfo().add("���������� 77KW/5600RPM");
		car.getEngineInfo().add("������Ť�� 155ţ.��/3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setSpeedInfo(new ArrayList<String>());
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("��������5���ֶ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.getSpeedInfo().add("���ʱ�٣�185KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬11.9S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.6L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.9L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1275KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);
		
		car = new Car();
		car.setType("�߶��� 6 1.6L \n �Զ�������");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.6L ֱ���ĸ�4��������ȼ������");
		car.getEngineInfo().add("���������� 77KW/5600RPM");
		car.getEngineInfo().add("������Ť�� 155ţ.��/3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("�����������˶�ģʽ��7��λ/�Զ�һ��DSG˫����Զ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.setSpeedInfo(new ArrayList<String>());
		car.getSpeedInfo().add("���ʱ�٣�180KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬11.8S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.9L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.6L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1295KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);
		
		car = new Car();
		car.setType("�߶��� 6 1.4TSI \n �ֶ�������");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.4L ֱ���ĸ�4���� TSI����͸���ֱ������������ѹ");
		car.getEngineInfo().add("���������� 96KW/5000RPM");
		car.getEngineInfo().add("������Ť�� 220ţ.��/1750-3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("��������5���ֶ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.setSpeedInfo(new ArrayList<String>());
		car.getSpeedInfo().add("���ʱ�٣�200KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬9.6S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.5L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.3L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1295KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);
		
		car = new Car();
		car.setType("�߶��� 6 1.4TSI \n �Զ�������");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.4L ֱ���ĸ�4���� TSI����͸���ֱ������������ѹ");
		car.getEngineInfo().add("���������� 96KW/5000RPM");
		car.getEngineInfo().add("������Ť�� 220ţ.��/1750-3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("�����������˶�ģʽ��7��λ/�Զ�һ��DSG˫����Զ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.setSpeedInfo(new ArrayList<String>());
		car.getSpeedInfo().add("���ʱ�٣�200KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬9.5S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.8L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.0L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1295KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);
		
		car = new Car();
		car.setType("�߶��� 6 1.4TSI \n �Զ�������");
		car.setEngineInfo(new ArrayList<String>());
		car.getEngineInfo().add("1.4L ֱ���ĸ�4���� TSI����͸���ֱ������������ѹ");
		car.getEngineInfo().add("���������� 96KW/5000RPM");
		car.getEngineInfo().add("������Ť�� 220ţ.��/1750-3500RPM");
		car.getEngineInfo().add("�ŷ� �й��ĺű�׼����OBD");
		car.setControlInfo(new ArrayList<String>());
		car.getControlInfo().add("�����������˶�ģʽ��7��λ/�Զ�һ��DSG˫����Զ�������");
		car.getControlInfo().add("����ϵͳ����ѷʽ��������/������ʽ��������");
		car.getControlInfo().add("�ƶ�ϵͳ����ߴ�ǰͨ���ƶ���/��ʵ���ƶ���");
		car.getControlInfo().add("ת��ϵͳ��EPS�綯��������ת����");
		car.setSpeedInfo(new ArrayList<String>());
		car.getSpeedInfo().add("���ʱ�٣�200KM/H");
		car.getSpeedInfo().add("0-100KM/Hʱ�䣬9.5S");
		car.getSpeedInfo().add("90KM/H�����ͺģ�5.8L/100KM");
		car.getSpeedInfo().add("�ۺ��ͺģ�6.0L/100KM");
		car.setShapeInfo(new ArrayList<String>());
		car.getShapeInfo().add("��x��x�ߣ�4199x1786x1479");
		car.getShapeInfo().add("��࣬2578mm");
		car.getShapeInfo().add("ǰ���־࣬1540mm/1513mm");
		car.getShapeInfo().add("����������1295KG");
		car.getShapeInfo().add("�������ݻ���350L/1513L");
		car.getShapeInfo().add("�����ݻ���55L");
		cars.add(car);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		detector = new GestureDetector(this);
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);

		for (Car car : cars) {
			LinearLayout frame = (LinearLayout) this.getLayoutInflater()
					.inflate(R.layout.frame, null);
			TextView textView = (TextView) frame.findViewById(R.id.TextView01);
			textView.setText(car.getType());

			ViewFlipper flipper2 = (ViewFlipper) this.getLayoutInflater().inflate(R.layout.flipper2, null);
			flipper2.setTag("flipper2");

			for (String[] s : this.subTitles) {
				LinearLayout layout = (LinearLayout) this.getLayoutInflater()
						.inflate(R.layout.content, null);
				TextView view = (TextView) layout
						.findViewById(R.id.SubtitleLabel);
				view.setText(s[0]);
				flipper2.addView(layout);

				List<String> items = null;

				try {
					items = (List<String>) car.getClass().getMethod(s[1], null)
							.invoke(car, null);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				for (String i : items) {
					LinearLayout layout2 = (LinearLayout) this
							.getLayoutInflater().inflate(R.layout.item, null);
					layout.addView(layout2);
					TextView textView2 = (TextView) layout2
							.findViewById(R.id.ContentItemText);
					textView2.setText(i);
				}
			}

			frame.addView(flipper2);
			flipper.addView(frame);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		ViewFlipper flipper2 = (ViewFlipper) this.flipper.getCurrentView()
				.findViewWithTag("flipper2");
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_out));
			this.flipper.showNext();
			return true;
		} else if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_out));
			this.flipper.showPrevious();
			return true;
		} else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE) {
			flipper2.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_up_in));
			flipper2.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_up_out));
			flipper2.showNext();
			return true;
		} else if (e1.getY() - e2.getY() < -FLING_MIN_DISTANCE) {
			flipper2.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_down_in));
			flipper2.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_down_out));
			flipper2.showPrevious();
			return true;
		}
		return false;

	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
}
