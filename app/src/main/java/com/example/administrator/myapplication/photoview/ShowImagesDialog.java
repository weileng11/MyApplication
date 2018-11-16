package com.example.administrator.myapplication.photoview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.myapplication.R;
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
 * @date: 2018/11/15 0015  
 * @time: 下午 6:06
 */
public class ShowImagesDialog extends Dialog
{
	
	private View mView ;
	private Context mContext;
	private ShowImagesViewPager mViewPager;
	private List<String> mImgUrls;
	private List<View> mViews;
	private ShowImagesAdapter mAdapter;
	private int position;
	
	public ShowImagesDialog(@NonNull Context context, List<String> imgUrls,int position) {
		super(context, R.style.transparentBgDialog);
		this.mContext = context;//传入上下文
		this.mImgUrls = imgUrls;//传入图片String数组
		this.position=position;
		initView();
		initData();
	}
	
	private void initView() {
		mView = View.inflate(mContext, R.layout.dialog_images_brower, null);//通过inflate()方法找到我们写好的包含ViewPager的布局文件
		mViewPager = (ShowImagesViewPager) mView.findViewById(R.id.vp_images);//找到ViewPager控件并且实例化
		mViews = new ArrayList<>();//创建一个控件的数组，我们可以在ViewPager中加入很多图片，滑动改变图片
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mView);
		Window window = getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = 0;
		wl.height = Config.EXACT_SCREEN_HEIGHT;
		wl.width = Config.EXACT_SCREEN_WIDTH;
		wl.gravity = Gravity.CENTER;
		window.setAttributes(wl);
		//EXACT_SCREEN_HEIGHT,EXACT_SCREEN_WIDTH为自定义Config类中的静态变量
		//在MainActivity中会给这两个变量赋值，分别对应手机屏幕高度和宽度
		//在这里我们通过WindowManager.LayoutParams把当前dialog的大小设置为全屏
	}
	
	private void initData() {
		//当PhotoView被点击时，添加相应的点击事件
		for (int i = 0; i < mImgUrls.size(); i++) {
			final PhotoView photoView=new PhotoView(mContext);
			ViewGroup.LayoutParams layoutParams=
					new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
							ViewGroup.LayoutParams.MATCH_PARENT);
			photoView.setLayoutParams(layoutParams);
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
			Glide.with(mContext)
					.load(mImgUrls.get(i))//导入图片
					.placeholder(R.mipmap.ic_launcher)//加载图片过程中显示的替代图片
					.error(R.mipmap.ic_launcher)//图片加载失败时显示的图片
					.into(new SimpleTarget<GlideDrawable>() {
						//这是Glide的一个回调方法
						//我们首先定义了一个SimpleTarget，然后把它通过into方法传入。
						// 这样当Glide去服务器请求图片成功之后，
						// 它会把请求到的图片资源作为GlideDrawable传递回来，
						// 你可以使用这个GlideDrawable进行自己想要的操作,
						@Override
						public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
							photoView.setImageDrawable(resource);
							//我们把回调过来的图片资源加载到PhotoView中
							//大家有没有发现一个细节，我在布局文件中并没有创建PhotoView控件，而是创建的ViewPager
							//因为我们需要在ViewPager中加入多个PhotoView以达到图片翻页的功能
							//因此我们在加载图片时，想要把图片加载到任意的图片控件中，就需要Glide回调方法
						}
					});
			mViews.add(photoView);//最后把我们加载的所有PhotoView传给View数组
		}
		
		mAdapter = new ShowImagesAdapter(mViews);//给适配器传入图片控件数组
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			//给ViewPager添加监听事件
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	}
	
}
