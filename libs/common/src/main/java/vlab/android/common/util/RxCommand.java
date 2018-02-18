package vlab.android.common.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import vlab.android.common.model.Response;

/**
 *  This is a wrapper class use RxJava to execute command, response a LiveData for consumers
 *
 *  Created by Vinh Tran on 2/18/18.
 */
public class RxCommand<InputType, DataResponse> {
    private PublishSubject<Object> mRxCommand = PublishSubject.create();
    private BehaviorSubject<Boolean> mOnExecuting = BehaviorSubject.createDefault(false);
    private Observable<DataResponse> mOnExecuted;

    /* live data for data change and loading */

    /**
     * Response<<DataResponse>> show the result of request is success or fail
     */
    MutableLiveData<Response<DataResponse>> mOnDataChanged = new MutableLiveData<>();
    MutableLiveData<Boolean> mOnLoading = new MutableLiveData<>();

    public RxCommand(InputType inputType, Function<InputType, Observable<DataResponse>> func) {
        mOnExecuted = mRxCommand.withLatestFrom(mOnExecuting, (v, executing) -> executing)
                .filter(executing -> !executing)
                .flatMap(v -> {

                    setExecuting(true);

                    return func.apply(inputType)
                            .doOnError(v1 -> {
                                LogUtils.d(getClass().getSimpleName(), ">>> RxCommand error " + v1.getMessage());
                                setExecuting(false);
                                mOnDataChanged.postValue(new Response<>(null, v1));
                            })
                            .onErrorResumeNext(throwable -> {
                                return Observable.empty();
                            })
                            .doOnNext(dataResponse -> {
                                setExecuting(false);
                                mOnDataChanged.postValue(new Response<>(dataResponse, null));
                            })
                            .doOnDispose(() -> mOnExecuting.onNext(false));
                });
    }

    private void setExecuting(boolean isExecuting) {
        mOnExecuting.onNext(isExecuting);
        // emit live data
        mOnLoading.postValue(isExecuting);
    }

    public void execute() {
        mRxCommand.onNext("");
    }

    public Disposable subscribe(){
        return mOnExecuted.subscribe();
    }

    public LiveData<Response<DataResponse>> onDataChanged() {
        return mOnDataChanged;
    }

    public LiveData<Boolean> onLoading() {
        return mOnLoading;
    }
}
