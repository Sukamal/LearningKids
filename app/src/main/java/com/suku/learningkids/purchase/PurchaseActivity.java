package com.suku.learningkids.purchase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.features.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseActivity extends BaseActivity {

    @BindView(R.id.tv_heading)
    TextView tvHeading;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.btn_buy)
    Button btnBuy;


    private InAppPurchaseManager inAppPurchaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ButterKnife.bind(this);
        inAppPurchaseManager= new InAppPurchaseManager(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/kid1.ttf");
        tvHeading.setTypeface(custom_font);
        getPurchaseItem();
    }


    private void getPurchaseItem(){
        inAppPurchaseManager.setListner(new InAppPurchaseManager.PurchaseListListner() {
            @Override
            public void onPurchaseList(Object items) {
                List<SkuDetailData> skuRowDatas = (List<SkuDetailData>) items;

                final SkuDetailData skuRowData = skuRowDatas.get(0);
                tvTitle.setText(skuRowData.getTitle());
                tvPrice.setText(skuRowData.getPrice());
                tvDescription.setText(skuRowData.getDescription());

                btnBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inAppPurchaseManager.initiatePurchaseFlow(skuRowData.getSku(),skuRowData.getBillingType());
                    }
                });

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
