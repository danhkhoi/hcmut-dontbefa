package dontbefa.hcmut.dontbefa;

import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import at.markushi.ui.CircleButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SliderLayout mImageSlider;

    private SecondFragment.OnFragmentInteractionListener mListener;
    private LinearLayout mainLayout;
    private PopupWindow popUpWindow;
    private boolean isClicked = true;
    private TextView tvMsg;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout containerLayout;
    private EditText editTxt;
    private Button btnDummy;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mainLayout = (LinearLayout) view.findViewById(R.id.main_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;

        containerLayout = new LinearLayout(getActivity().getApplicationContext());
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        popUpWindow = new PopupWindow(getActivity().getApplicationContext());
        popUpWindow.setOutsideTouchable(true);
        popUpWindow.setFocusable(true);
        tvMsg = new TextView(getActivity().getApplicationContext());
        tvMsg.setText("Con mèo sợ gì nhất...");
        tvMsg.setTextSize(20);
        tvMsg.setLayoutParams(params);
        editTxt = new EditText(getActivity().getApplicationContext());
        editTxt.setTextSize(20);
        editTxt.setHint("Nhập đáp án tại đây");
        editTxt.setFocusable(true);
        editTxt.setLayoutParams(params);
        btnDummy = new Button(getActivity().getApplicationContext());
        btnDummy.setText("Trả lời");
        btnDummy.setLayoutParams(params);
        btnDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.dismiss();
            }
        });

        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        containerLayout.addView(tvMsg, layoutParams);
        containerLayout.addView(editTxt, layoutParams);
        containerLayout.addView(btnDummy, layoutParams);
        popUpWindow.setContentView(containerLayout);

        mImageSlider = (SliderLayout) view.findViewById(R.id.image_browser_slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();

        // TODO: add list image
        file_maps.put("Lê, 22@Graphics designer",R.drawable.selfie1);
        file_maps.put("Trang, 25@HUFLIT",R.drawable.selfie2);
        file_maps.put("Hương, 23@Happy Alma",R.drawable.selfie3);
        file_maps.put("Hoàng, 22@Eastern International",R.drawable.selfie4);
        file_maps.put("Naomi, 22@Sacombank",R.drawable.selfie5);


        for(String name : file_maps.keySet()){
            ImageSlider imageSlider = new ImageSlider(getActivity().getApplicationContext());
            // initialize a SliderLayout
            //TODO set name age and description
            String[] split = name.split("@");
            imageSlider.setDescription(split[1]);
            imageSlider.setInfo(split[0]);
            imageSlider.image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            imageSlider.bundle(new Bundle());
            imageSlider.getBundle()
                    .putString("extra",name);

            mImageSlider.addSlider(imageSlider);
        }
        mImageSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mImageSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mImageSlider.stopAutoCycle();
        mImageSlider.addOnPageChangeListener(this);
        CircleButton btnYes = (CircleButton) view.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                popUpWindow.update(940, 400);
                //popUpWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);

                    /*int Measuredheight = getScreenHeight();
                    int Measuredwidth = getScreenWidth();
                    popUpWindow.update(Measuredheight/2-160, Measuredwidth/2-45, 320, 90);*/
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
