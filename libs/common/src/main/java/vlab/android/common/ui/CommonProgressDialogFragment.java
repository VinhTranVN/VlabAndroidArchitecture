package vlab.android.common.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vlab.android.common.R;
import vlab.android.common.util.LogUtils;

public class CommonProgressDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "ARG_TITLE";

    public static CommonProgressDialogFragment newInstance() {
        return new CommonProgressDialogFragment();
    }

    public static <T extends Fragment & DialogFragmentListener> CommonProgressDialogFragment newInstance(T listener, int dialogId) {
        return newInstance(null, listener, dialogId);
    }

    public static <T extends Fragment & DialogFragmentListener> CommonProgressDialogFragment newInstance(String title, T listener, int dialogId) {
        CommonProgressDialogFragment fragment = new CommonProgressDialogFragment();
        fragment.setTargetFragment(listener, dialogId);

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Progress_dialog);
        setCancelable(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading_dialog, container, false);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        if (getTargetFragment() instanceof DialogFragmentListener) {
            ((DialogFragmentListener) getTargetFragment()).onCancel(getTargetRequestCode());
        }
    }

    @Override
    public void onDestroyView() {
        LogUtils.d(getClass().getSimpleName(), ">>> onDestroyView isAdded() " + isAdded());
        if(isAdded() || isVisible()){
            dismissAllowingStateLoss();
        }
        super.onDestroyView();
    }

    public interface DialogFragmentListener {
        void onPositiveButtonClicked(int dialogId);

        void onCancel(int dialogId);
    }
}
