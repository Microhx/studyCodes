package com.zhongwu.mydome;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhongwu.mydome.view.TextLocation;
import com.zhongwu.mydome.view.WaterMarkImage;

public class MainActivity extends AppCompatActivity {

    ImageView image1 ,image2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = (ImageView) findViewById(R.id.id_image1);
        image2 = (ImageView) findViewById(R.id.id_image2);

        image1.setImageResource(R.mipmap.jintian);
    }

    public void addWaterMark(View view) {

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.jintian);

        WaterMarkImage waterMarkImage = new WaterMarkImage(mBitmap);
        Bitmap resBit = waterMarkImage
                //设置水印遮罩图片
                .setWaterMarkBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.watermark))
                //设置水印文字
                .setWaterMarkString("2017-11-20")
                //设置水印文字位置
                .setWaterMarkTextLocation(TextLocation.BOTTOM_RIGHT)
                //设置水印文字旋转角度
                .setWaterMarkTextRotationAngle(0)
                //设置水印文字颜色
                .setWaterMarkTextColor(Color.GREEN)
                //设置水印文字大小
                .setWaterMarkTextSize(getResources().getDimension(R.dimen.water_mark_text_size))
                //设置水印文字字体
                .setWaterMarkTextTypeface(Typeface.create("宋体", Typeface.BOLD))
                //此方法必须调用，得到水印图片
                .getBitmap();


        image2.setImageBitmap(resBit);
    }
}
