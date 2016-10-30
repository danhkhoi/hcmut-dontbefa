package dontbefa.hcmut.dontbefa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

/**
 * Created by tts on 10/30/16.
 */

public class ShowPopUpWindow extends Activity {
    boolean isClicked = true;
    PopupWindow popUpWindow;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout mainLayout;
    Button btnClickHere;
    LinearLayout containerLayout;
    TextView tvMsg;
    View.OnClickListener imageSliderView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        containerLayout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);

        imageSliderView = new View.OnClickListener() {
            public void onClick(View v) {
                if (isClicked) {
                    isClicked = false;
                    popUpWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
                    popUpWindow.update(50, 50, 320, 90);
                } else {
                    isClicked = true;
                    popUpWindow.dismiss();
                }
            }
        };

        tvMsg = new TextView(this);
        tvMsg.setText("Hi this is pop up window...");

        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.addView(tvMsg, layoutParams);
        popUpWindow.setContentView(containerLayout);
        mainLayout.addView(btnClickHere, layoutParams);
        setContentView(mainLayout);
    }
}
