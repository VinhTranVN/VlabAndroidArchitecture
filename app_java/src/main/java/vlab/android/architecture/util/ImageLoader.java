package vlab.android.architecture.util;

import android.widget.ImageView;

import vlab.android.architecture.MyApplication;
import vlab.android.architecture.di.module.GlideApp;

/**
 * Created by Vinh Tran on 10/30/18.
 */
public class ImageLoader {

    public static void loadCircleImage(String imgUrl, ImageView imageView) {
        GlideApp.with(MyApplication.getInstance().getApplicationContext())
                .load(imgUrl)
                .circleCrop()
                .into(imageView);

    }
}
