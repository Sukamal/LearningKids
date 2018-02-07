package com.suku.learningkids.features.bodyparts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ButtomImageAdapter;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 07-02-2018.
 */

public class BodyPartsFragment extends BaseFragment {

    @BindView(R.id.iv_body)
    ImageView ivBody;
    @BindView(R.id.iv_parts_image)
    ImageView ivPpartsImage;
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;
    @BindView(R.id.tv_text)
    TextView tvText;




    private BodyImageAdapter imageAdapter;
    private List<BodyPartsModel> bodyPartsList;

    private Bitmap bitmap;
    private Bitmap tempBitmap;
    private Paint paint;
    private float displayScale;
    private int backImage = R.drawable.body1;

    private TextToSpeech textToSpeech;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bodyparts_layout,container,false);
        ButterKnife.bind(this,view);
        displayScale = getResources().getDisplayMetrics().density;
        initCommonItems();
        return view;
    }

    private void initCommonItems() {
        initTextToSpeach();
        initBitmap(R.drawable.body1);
        initPaint();
        setBodyParts();
        initBottomImageRecyclerView();
    }



    private void setBodyParts(){
        bodyPartsList = new ArrayList<BodyPartsModel>();

        BodyPartsModel partsModel = new BodyPartsModel("Forehead",R.drawable.forehead,new Point(204,144),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Ear",R.drawable.ear,new Point(302,193),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Cheek",R.drawable.cheek,new Point(266,220),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Tooth",R.drawable.teeth,new Point(202,227),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Tongue",R.drawable.tongue,new Point(207,246),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Lip",R.drawable.lips,new Point(233,219),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Stomach",R.drawable.stomach,new Point(202,393),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Knee",R.drawable.knee,new Point(250,540),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Foot",R.drawable.foot,new Point(250,640),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Eyebrow",R.drawable.eyebrow,new Point(252,152),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Eye",R.drawable.eye,new Point(250,183),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Nose",R.drawable.nose,new Point(197,203),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Mouth",R.drawable.mouth,new Point(219,236),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Chin",R.drawable.chin,new Point(207,268),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Chest",R.drawable.chest,new Point(206,327),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Arm",R.drawable.arm,new Point(302,354),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Hand",R.drawable.hand,new Point(341,432),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Leg",R.drawable.leg,new Point(242,568),R.drawable.body1);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Toe",R.drawable.toe,new Point(242,665),R.drawable.body1);
        bodyPartsList.add(partsModel);



        partsModel = new BodyPartsModel("Neck",R.drawable.neck,new Point(206,260),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Finger",R.drawable.finger,new Point(330,431),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Ankle",R.drawable.ankle,new Point(222,619),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Hair",R.drawable.hair,new Point(250,100),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Head",R.drawable.head,new Point(200,100),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Shoulder",R.drawable.shoulders,new Point(244,262),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Back",R.drawable.back,new Point(200,313),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Elbow",R.drawable.elbow,new Point(303,343),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Waist",R.drawable.weist,new Point(217,371),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Bottom",R.drawable.bottom_part,new Point(200,425),R.drawable.body2);
        bodyPartsList.add(partsModel);

        partsModel = new BodyPartsModel("Heel",R.drawable.heel,new Point(221,636),R.drawable.body2);
        bodyPartsList.add(partsModel);


    }

    private void initBottomImageRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvImageList.setLayoutManager(layoutManager);
        rvImageList.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        imageAdapter = new BodyImageAdapter(bodyPartsList, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                BodyPartsModel partsModel = bodyPartsList.get(position);
                if(backImage != partsModel.getBackImage()){
                    initBitmap(partsModel.getBackImage());
                }
                tvText.setText(partsModel.getText());
                displayBodyParts(partsModel);
                backImage = partsModel.getBackImage();

                if(partsModel.getIsLocked()){
                    speakOut("Please Subscribe");
                }else{
                    speakOut(partsModel.getText());
                }

            }
        });

        rvImageList.setAdapter(imageAdapter);
    }

    private void displayBodyParts(BodyPartsModel partsModel){
        drawOnCanvas(partsModel.getPoint(),partsModel.getText());
        ivPpartsImage.setImageResource(partsModel.getPartsImage());
    }

    private void initBitmap(int image){
        if(bitmap != null){
            bitmap.recycle();
        }
        bitmap =  BitmapFactory.decodeResource(getResources(), image);
    }

    private void initPaint(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(30*displayScale);
        paint.setStrokeWidth(3*displayScale);
    }

    private void drawOnCanvas(Point point,String text){
        if(tempBitmap != null){
            tempBitmap.recycle();
            tempBitmap = null;
        }
        tempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(bitmap, 0, 0, null);
        tempCanvas.drawLine(point.x*displayScale,point.y*displayScale,(point.x + 150)*displayScale,point.y*displayScale,paint);
        tempCanvas.drawText(text, (point.x + 155)*displayScale,point.y*displayScale, paint);
        ivBody.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
    }

    private void initTextToSpeach() {
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                }
            }
        });
    }

    private void speakOut(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    private void createBitmap(){

        //Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(tempBitmap);

        //Draw the image bitmap into the cavas
        tempCanvas.drawBitmap(bitmap, 0, 0, null);
        paint = new Paint();
        float scale = getResources().getDisplayMetrics().density;
        paint.setColor(Color.RED);
        paint.setTextSize(50*scale);
        paint.setStrokeWidth(5*scale);
        bitmap.recycle();
        bitmap = null;
        //Draw everything else you want into the canvas, in this example a rectangle with rounded edges


        tempCanvas.drawLine(350*scale,150*scale,500*scale,150*scale,paint);
        tempCanvas.drawText("Hair", 505*scale,150*scale, paint);

        tempCanvas.drawLine(383*scale,225*scale,500*scale,225*scale,paint);
        tempCanvas.drawText("Eyebrow", 505*scale,225*scale, paint);

        tempCanvas.drawLine(334*scale,308*scale,500*scale,308*scale,paint);
        tempCanvas.drawText("Mouth", 505*scale,308*scale, paint);


        //Attach the canvas to the ImageView
        ivBody.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
    }




}
