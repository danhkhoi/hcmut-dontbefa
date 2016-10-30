package dontbefa.hcmut.dontbefa;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * Created by tts on 10/29/16.
 */

public class ImageSlider extends BaseSliderView {
    private String mDescription;
    private String mInfo;

    protected ImageSlider(Context context) {
        super(context);
    }

    public void setDescription(String description){
        mDescription = description;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getInfo() {
        return mInfo;
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.image_slider_layout,null);
        ImageView target = (ImageView) v.findViewById(R.id.slider_image);

        TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(getInfo());

        TextView description = (TextView)v.findViewById(R.id.txt_description);
        description.setText(getDescription());

        bindEventAndShow(v, target);
        return v;
    }
}
