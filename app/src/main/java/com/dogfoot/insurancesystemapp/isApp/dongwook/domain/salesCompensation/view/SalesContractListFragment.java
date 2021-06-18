package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view;

import android.content.Intent;
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
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationContractListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view.adapter.SalesContractRecyclerAdapter;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import java.util.List;
import java.util.Vector;

import retrofit2.Response;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.LIST_FRAGMENT_FAILURE_DIALOG_TITLE;


public class SalesContractListFragment extends DogFootViewModelFragment {

    // Associate
    int selectedItem;
    boolean valueInput = false;
    // View

    private RecyclerView salesContractRecyclerView;
    private Vector<CompensationContractListResponse> items;

    // Component
    // View
    private LoadingDialog dialog;
    private SalesContractRecyclerAdapter salesContractRecyclerAdapter;
    TextView sumText;


    /**
     * System Life Cycle Callback
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sales_contract_list;
    }

    @Override
    protected void associateView(View view) {
        this.salesContractRecyclerView = view.findViewById(R.id.salesContractRecyclerView);
        this.sumText = view.findViewById(R.id.sumOfSalesContractItems);
    }

    @Override
    protected void initializeView() {
        View.OnClickListener listener = v -> {
            if(v.getId()==R.id.sales_contract_item){
                selectedItem=((Integer)v.getTag());
            }
            dataset.put(DogFootEntity.EDogFootData.ID,items.get(selectedItem).getAccident_id().toString());
            this.valueInput=true;
            this.save();
            if(valueInput) {
                Intent intent = new Intent(this.getContext(), SalesCompensationViewActivity.class);
                this.startActivity(intent);
            }
        };
        this.salesContractRecyclerAdapter = new SalesContractRecyclerAdapter(this.getActivity(),listener);
        this.dialog = new LoadingDialog(getContext());
        this.salesContractRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.salesContractRecyclerView.setAdapter(this.salesContractRecyclerAdapter);
        this.getCustomerContract();

    }

    @Override
    protected void dogFootEntityUpdated() {

    }


    private void getCustomerContract() {


        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getCompensationContract()
                .enqueue(MainRetrofitTool.getCallback(new CompensationContractListResponseCallback()));
    }

    /**
     * Callback
     */
    private class CompensationContractListResponseCallback implements MainRetrofitCallback<List<CompensationContractListResponse>> {
        @Override
        public void onSuccessResponse(Response<List<CompensationContractListResponse>> response) {
            items = new Vector<>(response.body());
            salesContractRecyclerAdapter.setItems(items);
            salesContractRecyclerView.setAdapter(salesContractRecyclerAdapter);
            sumText.setText(" "+items.size() + "개");
        }

        @Override
        public void onFailResponse(Response<List<CompensationContractListResponse>> response) {
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