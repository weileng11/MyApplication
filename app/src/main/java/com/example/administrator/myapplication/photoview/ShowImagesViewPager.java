package com.example.administrator.myapplication.photoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author: ${bruce}
 * @project: MyApplication
 * @package: com.example.administrator.myapplication
 * @description:
 * @date: 2018/11/15 0015  
 * @time: 下午 6:05
 */
public class ShowImagesViewPager extends ViewPager
{
	public ShowImagesViewPager(Context context) {
		super(context);
	}
	
	public ShowImagesViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		try {
			return super.onTouchEvent(ev);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return false;
		
		
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}
}
