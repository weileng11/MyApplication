package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import com.example.administrator.myapplication.photoview.Config;
import com.example.administrator.myapplication.photoview.ConstantValue;
import com.example.administrator.myapplication.photoview.ShowImagesDialog;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ${bruce}
 * @project: MainActivity
 * @package: com.example.administrator.myapplication
 * @description:
 * @date: 2018/11/15 0015
 * @time: 下午 6:06
 */
public class MainActivity extends AppCompatActivity
{
	private List<String> list;
	private String[] urls={ConstantValue.PIC_URL1,ConstantValue.PIC_URL2,ConstantValue.PIC_URL3,ConstantValue.PIC_URL4};
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取当前设备的屏幕密度等基本参数 ShowImagesDialog
		getDeviceDensity();
		
		initData();
		//final ArrayList<String> urls=new ArrayList<>();
		//urls.add(
		//		"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511198824138&di=cec97b6363a1bce28b8499a31b78df83&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F3e282f8762696b0bbb3ed16a5dc193c718e5aff9.jpg");
		//urls.add(
		//		"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511793592&di=08b8de22336028a68c9a0bbbe7a9066d&imgtype=jpg&er=1&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2F7ea7878cfbddb27bb8a23e2407bfa7a48655f317.jpg");
		//urls.add(
		//		"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511198824136&di=f4dc71ffdbbb16d9e3d496cf8add5376&imgtype=0&src=http%3A%2F%2Foss.tan8.com%2Fresource%2Fattachment%2F2017%2F201707%2F962b7304d0adc7e2e1d374dc6786e302.jpg");
		//urls.add(
		//		"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511793592&di=08b8de22336028a68c9a0bbbe7a9066d&imgtype=jpg&er=1&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2F7ea7878cfbddb27bb8a23e2407bfa7a48655f317.jpg");
		//urls.add(
		//		"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511198824136&di=f4dc71ffdbbb16d9e3d496cf8add5376&imgtype=0&src=http%3A%2F%2Foss.tan8.com%2Fresource%2Fattachment%2F2017%2F201707%2F962b7304d0adc7e2e1d374dc6786e302.jpg");
		findViewById(R.id.txv_click).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v){
				new ShowImagesDialog(MainActivity.this, list,0).show();
			}
		});
	}
	
	private void initData() {
		list=new ArrayList<>();
		for (int i=0;i<urls.length;i++){
			list.add(urls[i]);
		}
	}
	
	/**
	 获取当前设备的屏幕密度等基本参数
	 */
	protected void getDeviceDensity(){
		DisplayMetrics metrics=new DisplayMetrics();//通过DisplayMetrics类可以得到手机屏幕的参数
		getWindowManager().getDefaultDisplay().getMetrics(metrics);//然后将DisplayMetrics对象传入
		Config.EXACT_SCREEN_HEIGHT=metrics.heightPixels;//得到手机屏幕的高的分辨率
		Config.EXACT_SCREEN_WIDTH=metrics.widthPixels;//得到手机屏幕宽的分辨率
	}
	
}
