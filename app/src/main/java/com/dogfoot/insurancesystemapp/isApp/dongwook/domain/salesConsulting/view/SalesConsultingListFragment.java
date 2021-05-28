package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingList.CustomerConsultingRecyclerAdapter;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view.salesConsultingList.SalesConsultingRecyclerAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyUpdateFragment;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import java.util.List;
import java.util.Vector;

import retrofit2.Response;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.LIST_FRAGMENT_FAILURE_DIALOG_TITLE;


public class SalesConsultingListFragment extends DogFootViewModelFragment {

    // Associate
    int selectedItem;
    boolean valueInput = false;
    // View

    private RecyclerView salesConsultingRecyclerView;
    private Vector<SalesConsultingListResponse> items;

    // Component
    // View
    private LoadingDialog dialog;
    private SalesConsultingRecyclerAdapter salesConsultingRecyclerAdapter;
    TextView sumText;


    /**
     * System Life Cycle Callback
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sales_consulting_list;
    }

    @Override
    protected void associateView(View view) {
        this.salesConsultingRecyclerView = view.findViewById(R.id.customerConsultingRecyclerView);
        this.sumText = view.findViewById(R.id.sumOfCustomerConsultingItems);
    }

    @Override
    protected void initializeView() {
        View.OnClickListener listener = v -> {
            if(v.getId()==R.id.consulting_item){
                selectedItem=((Integer)v.getTag());
            }
            dataset.put(DogFootEntity.EDogFootData.ID,items.get(selectedItem).getId().toString());
            this.valueInput=true;
            this.save();
            if(valueInput) {Navigation.findNavController(getView()).navigate(R.id.action_salesConsultingList_to_salesConsultingView);}
        };
        this.salesConsultingRecyclerAdapter = new SalesConsultingRecyclerAdapter(this.getActivity(), listener);
        this.dialog = new LoadingDialog(getContext());
        this.salesConsultingRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.salesConsultingRecyclerView.setAdapter(this.salesConsultingRecyclerAdapter);
        this.loadSearchResult();

    }


    @Override
    protected void dogFootEntityUpdated() {

    }


    private void loadSearchResult() {


        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getSalesConsultingList()
                .enqueue(MainRetrofitTool.getCallback(new SalesConsultingListResponseCallback()));
    }

    /**
     * Callback
     */
    private class SalesConsultingListResponseCallback implements MainRetrofitCallback<Pagination<List<SalesConsultingListResponse>>> {
        @Override
        public void onSuccessResponse(Response<Pagination<List<SalesConsultingListResponse>>> response) {
            items = new Vector<>(response.body().getData());
            salesConsultingRecyclerAdapter.setItems(items);
            salesConsultingRecyclerView.setAdapter(salesConsultingRecyclerAdapter);
            sumText.setText(" "+items.size() + "개");
        }

        @Override
        public void onFailResponse(Response<Pagination<List<SalesConsultingListResponse>>> response) {
            Log.d("디버그", dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION));
            try {

                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(getContext(), jObjError.getString("error-message"), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onConnectionFail(Throwable t) {

            DogFootDialog.simplerAlertDialog(getActivity(),
                    LIST_FRAGMENT_FAILURE_DIALOG_TITLE, t.getMessage(),
                    null
            );
        }
    }


}