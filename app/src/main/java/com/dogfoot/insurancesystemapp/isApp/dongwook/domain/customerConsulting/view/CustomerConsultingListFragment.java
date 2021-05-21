package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingList.CustomerConsultingRecyclerAdapter;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;


import org.json.JSONObject;

import java.util.List;
import java.util.Vector;

import retrofit2.Response;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.LIST_FRAGMENT_FAILURE_DIALOG_TITLE;


public class CustomerConsultingListFragment extends DogFootViewModelFragment {

    // Associate
    // View

    private RecyclerView customerConsultingRecyclerView;

    // Component
    // View
    private LoadingDialog dialog;
    private CustomerConsultingRecyclerAdapter customerConsultingRecyclerAdapter;
    TextView sumText;


    /**
     * System Life Cycle Callback
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_consulting_list;
    }

    @Override
    protected void associateView(View view) {
        this.customerConsultingRecyclerView = view.findViewById(R.id.customerConsultingRecyclerView);
        this.sumText = view.findViewById(R.id.sumOfCustomerConsultingItems);
    }

    @Override
    protected void initializeView() {
        this.customerConsultingRecyclerAdapter = new CustomerConsultingRecyclerAdapter(this.getActivity());
        this.dialog = new LoadingDialog(getContext());
        this.customerConsultingRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.customerConsultingRecyclerView.setAdapter(this.customerConsultingRecyclerAdapter);
        this.loadSearchResult();

    }

    @Override
    protected void dogFootEntityUpdated() {

    }


    private void loadSearchResult() {


        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getCustomerConsultingList()
                .enqueue(MainRetrofitTool.getCallback(new CustomerConsultingListResponseCallback()));
    }

    /**
     * Callback
     */
    private class CustomerConsultingListResponseCallback implements MainRetrofitCallback<Pagination<List<CustomerConsultingListResponse>>> {
        @Override
        public void onSuccessResponse(Response<Pagination<List<CustomerConsultingListResponse>>> response) {
            Vector<CustomerConsultingListResponse> items = new Vector<>(response.body().getData());
            customerConsultingRecyclerAdapter.setItems(items);
            customerConsultingRecyclerView.setAdapter(customerConsultingRecyclerAdapter);
            sumText.setText(" "+items.size() + "개");
        }

        @Override
        public void onFailResponse(Response<Pagination<List<CustomerConsultingListResponse>>> response) {
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