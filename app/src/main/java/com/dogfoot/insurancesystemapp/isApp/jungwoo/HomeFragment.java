package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentHomeBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.advertisement.AdvertisementViewPagerAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.advertisement.ViewPagerTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.user.model.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends DogFootViewModelFragment {

    private static HomeFragment homeFragment = null;
    private FragmentHomeBinding mBinding;
    private Context context;
    private CardView cardView_car, cardView_driver, cardView_fire, cardView_travel;
    private ViewPager2 viewPager2;

    @AllArgsConstructor
    @Getter
    public enum EAdImage{
        first(R.drawable.totalinsurance),second(R.drawable.fireinsurance),third(R.drawable.totalinsurance),fourth(R.drawable.travelinsurance);
        private int ImageId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

//        this.viewPager2 = mBinding.homeFragmentViewPager;
//        this.viewPager2.setAdapter(new AdvertisementViewPagerAdapter(EAdImage.values(), context));
//        ViewPagerTool.setAutoSlide(this.viewPager2, 3000);
//        ViewPagerTool.setEffect(this.viewPager2);

        cardView_car = mBinding.cardViewCar;
        cardView_driver = mBinding.cardViewDriver;
        cardView_fire = mBinding.cardViewFire;
        cardView_travel = mBinding.cardViewTravel;
//        iv_menu = mBinding.ivMenu;
//        iv_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDrawerLayout();
//            }
//        });

        cardView_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(context,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        " <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>자동차 가격: 책정된 가격의 + 1천만, + 3천만, +5천만원, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>주행 거리: 책정된 거리의 + 10000km, + 50000km, +100000km, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>출고 일자: 책정된 일자의 + 365일, + 730일, +1095일, 나머지의 단계로 나뉩니다. </p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p>\n" +
                                "            <p>ex) 기본금 * (1.0 + 0.4 + 0.2 + 0.3)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("자동차 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        cardView_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(context,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        "  <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>운전 면허: 운전 면허의 종류를 확인합니다. 1종 보통, 2종 보통의 단계로 나뉩니다.</p>\n" +
                                "            <p>운전 면허 취득 일자: 책정된 일자의 + 365일, + 730일, +1095일, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p>\n" +
                                "            <p>ex) 기본금 * (1.0 + 0.2 + 0.1)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("운전자 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        cardView_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(context,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        "   <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>빌딩 가격: 책정된 가격의 + 3억, + 6억, +9억, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>건축 일자: 책정된 일자의 + 365일, + 1095일, +2190일, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>층수: 책정된 층수의 + 5층, + 10층, +15층, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>부지 면적: 책정된 면적의 + 30m2, + 60m2, + 90m2, 나머지의 단계로 나뉩니다.</p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p> \n" +
                                "            <p>ex) 기본금 * (1.0 + 0.2 + 0.1 +0.4 +0.3)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("화재 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        cardView_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(context,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                String strHtml =
                        " <p>기준 가격: 관리자가 책정할 기준을 명시합니다. 비율은 순서대로 0.1 ~ 0.4씩 올라갑니다.</p>\n" +
                                "            <p>안전 등급: 안전 등급의 기준을 확인합니다. GREEN, BLUE, RED의 단계로 나뉩니다.</p>\n" +
                                "            <p>기본 요율 1.0에 책정된 비율을 합산한 후 기본금을 곱하여 계산합니다. </p>\n" +
                                "            <p>ex) 기본금 * (1.0 + 0.2)</p>";
                Spanned oHtml;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    // noinspection deprecation
                    oHtml = Html.fromHtml(strHtml);
                }
                else
                {
                    oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                }
                oDialog.setTitle("여행 보험 요율 기준")
                        .setMessage(oHtml)
                        .setIcon(R.drawable.dogfoot_icon)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .show();

            }
        });

        return view;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void associateView(View view) {

    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void dogFootEntityUpdated() {

    }

    public static HomeFragment newInstance() {
        if(homeFragment == null)
        {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }
}
