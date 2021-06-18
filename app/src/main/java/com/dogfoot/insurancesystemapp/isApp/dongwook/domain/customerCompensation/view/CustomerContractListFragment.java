package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CustomerContractListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view.adapter.ContractRecyclerAdapter;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingList.CustomerConsultingRecyclerAdapter;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import java.util.List;
import java.util.Vector;

import retrofit2.Response;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.LIST_FRAGMENT_FAILURE_DIALOG_TITLE;


public class CustomerContractListFragment extends DogFootViewModelFragment {

    // Associate
    int selectedItem;
    boolean valueInput = false;
    // View

    private RecyclerView customerContractRecyclerView;
    private Vector<CustomerContractListResponse> items;

    // Component
    // View
    private LoadingDialog dialog;
    private ContractRecyclerAdapter customerContractRecyclerAdapter;
    TextView sumText;


    /**
     * System Life Cycle Callback
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_contract_list;
    }

    @Override
    protected void associateView(View view) {
        this.customerContractRecyclerView = view.findViewById(R.id.customerContractRecyclerView);
        this.sumText = view.findViewById(R.id.sumOfCustomerContractItems);
    }

    @Override
    protected void initializeView() {
        View.OnClickListener listener = v -> {
            if(v.getId()==R.id.contract_item){
                selectedItem=((Integer)v.getTag());
            }
            dataset.put(DogFootEntity.EDogFootData.ID,items.get(selectedItem).getContract_id().toString());
            this.valueInput=true;
            this.save();
            if(valueInput) {
                Intent intent = new Intent(this.getContext(), CustomerCompensationUploadActivity.class);
                this.startActivity(intent);
            }
        };
        this.customerContractRecyclerAdapter = new ContractRecyclerAdapter(this.getActivity(),listener);
        this.dialog = new LoadingDialog(getContext());
        this.customerContractRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.customerContractRecyclerView.setAdapter(this.customerContractRecyclerAdapter);
        this.getCustomerContract();

    }

    @Override
    protected void dogFootEntityUpdated() {

    }


    private void getCustomerContract() {


        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getCustomerContract()
                .enqueue(MainRetrofitTool.getCallback(new CustomerContractListResponseCallback()));
    }

    /**
     * Callback
     */
    private class CustomerContractListResponseCallback implements MainRetrofitCallback<List<CustomerContractListResponse>> {
        @Override
        public void onSuccessResponse(Response<List<CustomerContractListResponse>> response) {
            items = new Vector<>(response.body());
            customerContractRecyclerAdapter.setItems(items);
            customerContractRecyclerView.setAdapter(customerContractRecyclerAdapter);
            sumText.setText(" "+items.size() + "개");
        }

        @Override
        public void onFailResponse(Response<List<CustomerContractListResponse>> response) {
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