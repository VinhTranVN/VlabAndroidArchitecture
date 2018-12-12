package vlab.android.architecture.feature.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseFragment;
import vlab.android.architecture.feature.home.viewmodel.HomeViewModel;
import vlab.android.architecture.feature.user_repository.UserRepositoryFragment;

public class HomeFragment extends BaseFragment {

    public static final String ARG_USER_NAME = "ARG_USER_NAME";
    private OnHomeFragmentListener mListener;

    private HomeViewModel mViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentListener) {
            mListener = (OnHomeFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void initViewModel() {
        mViewModel = provideViewModel(HomeViewModel.class);

        if (getArguments() != null) {
            // TODO map argument to ViewModel
            String userName = getArguments().getString(ARG_USER_NAME);
            mViewModel.setUserName(userName);
        }
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.tv_welcome_msg)).setText(mViewModel.getUserName());
        if(mViewModel.isUserAuthenticated()){
            replaceFragment(R.id.content_container, new UserRepositoryFragment(), false);
        }

    }

    public void setOnHomeFragmentListener(OnHomeFragmentListener listener) {
        mListener = listener;
    }

    public interface OnHomeFragmentListener {
        // TODO: Update argument type and name
        void onHomeFragmentCallback();
    }
}
