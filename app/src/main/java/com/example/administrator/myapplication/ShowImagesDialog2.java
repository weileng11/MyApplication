package com.example.administrator.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ${bruce}
 * @project: MyApplication
 * @package: com.example.administrator.myapplication
 * @description:
 * @date: 2018/11/16 0016  
 * @time: 上午 10:35
 */
public class ShowImagesDialog2 extends Dialog
{
	private Context context;
	private List<String> imageInfos;
	private ShowImagesViewPager vp;
	private List<View> views=new ArrayList<View>();
	private LinearLayout ll_point;
	private ViewPagerAdapter pageAdapter;
	private TextView txvPicNum;
	private int position;
	private LinearLayout.LayoutParams paramsL=new LinearLayout.LayoutParams(10, 10);
	
	public ShowImagesDialog2(Context context, int themeResId){
		super(context, themeResId);
		this.context=context;
	}
	
	public ShowImagesDialog2(Context context, List<String> imageInfos, int position){
		this(context, R.style.transparentBgDialog);
		this.position=position;
		this.imageInfos=imageInfos;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_dialog_pic);
		getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		vp=(ShowImagesViewPager)findViewById(R.id.vp);
		ll_point=(LinearLayout)findViewById(R.id.ll_point);
		txvPicNum=(TextView)findViewById(R.id.txv_pic_num);
		initMyPageAdapter();
		//        vp.setAdapter(new ViewPagerAdapter());
		txvPicNum.setText(position+1+"/"+imageInfos.size());
		vp.setCurrentItem(position);
		vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int i, float v, int i1){
			}
			
			@Override
			public void onPageSelected(int position){
				if(views.size()!=0 && views.get(position)!=null){
					for(int i=0; i<views.size(); i++){
						if(i==position){
							views.get(i).setBackgroundResource(R.drawable.point_focus2);
						}else{
							views.get(i).setBackgroundResource(R.drawable.point_normal2);
						}
					}
				}
				txvPicNum.setText(position+1+"/"+imageInfos.size());
			}
			
			@Override
			public void onPageScrollStateChanged(int i){
			}
		});
	}
	
	/***
	 * 初始化viewpager适配器
	 */
	private void initMyPageAdapter(){
		initPoint();
		if(pageAdapter==null){
			pageAdapter=new ViewPagerAdapter();
			if(vp!=null){
				vp.setAdapter(pageAdapter);
			}
		}else{
			pageAdapter.notifyDataSetChanged();
		}
	}
	
	private void initPoint(){
		views.clear();
		ll_point.removeAllViews();
		if(imageInfos.size()==1){
			ll_point.setVisibility(View.GONE);
		}else{
			for(int i=0; i<imageInfos.size(); i++){
				View view=new View(context);
				paramsL.setMargins(dip2px(context, 5), dip2px(context, 2), 0, dip2px(context, 5));
				view.setLayoutParams(paramsL);
				if(i==position){
					view.setBackgroundResource(R.drawable.point_focus2);
				}else{
					view.setBackgroundResource(R.drawable.point_normal2);
				}
				views.add(view);
				ll_point.addView(view);
			}
		}
	}
	
	private class ViewPagerAdapter extends PagerAdapter
	{
		@Override
		public int getCount(){
			return imageInfos.size();
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object){
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position){
			View view=View.inflate(context, R.layout.item_pic_show, null);
			final PhotoView photoView=(PhotoView)view.findViewById(R.id.pic_pv);
			photoView.setOnPhotoTapListener(new OnPhotoTapListener()
			{
				@Override
				public void onPhotoTap(ImageView view, float x, float y){
					dismiss();
				}
			});
			photoView.setOnViewTapListener(new OnViewTapListener()
			{
				@Override
				public void onViewTap(View view, float x, float y){
					dismiss();
				}
			});
			//Glide.with(context)
			//		.load(imageInfos.get(position))
			//		.placeholder(R.mipmap.zanwutupian)//图片加载出来前，显示的图片
			//		.dontAnimate()
			//		.error(R.mipmap.zanwutupian)//图片加载失败后，显示的图片
			//		.into(photoView);
			
			Glide.with(context)
					.load(imageInfos.get(position))
					.placeholder(R.mipmap.zanwutupian)
					.error(R.mipmap.zanwutupian)
					.into(new SimpleTarget<GlideDrawable>()
					{
						@Override
						public void onResourceReady(GlideDrawable resource,
								GlideAnimation<? super GlideDrawable> glideAnimation){
							photoView.setImageDrawable(resource);
						}
					});
			
			(container).addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object){
			((ViewPager)container).removeView((View)object);
		}
	}
	
	private int dip2px(Context context, float dpValue){
		final float scale=context.getResources().getDisplayMetrics().density;
		return (int)(dpValue*scale+0.5f);
	}
}
