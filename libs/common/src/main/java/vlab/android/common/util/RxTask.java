package vlab.android.common.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 *  This is a wrapper class use RxJava to execute task, response a LiveData for consumers
 *
 *  Created by Vinh Tran on 2/18/18.
 */
public class RxTask<DataRequest, DataResponse> {

    // execute command
    PublishSubject<DataRequest> mTaskSubject = PublishSubject.create();

    Observable<DataResponse> mOnExecuted;

    /* live data for data change and loading */
    MutableLiveData<DataResponse> mOnDataChanged = new MutableLiveData<>();
    MutableLiveData<Throwable> mOnError = new MutableLiveData<>();
    MutableLiveData<Boolean> mOnLoading = new MutableLiveData<>();

    private boolean mIsExecuting = false;

    public RxTask(Function<DataRequest, Observable<DataResponse>> func) {
        mOnExecuted =
                mTaskSubject
                        .filter(requestData -> !mIsExecuting)
                        .flatMap(requestData -> {

                            setExecuting(true);

                            return func.apply(requestData)
                                    .doOnError(error -> {
                                        LogUtils.d(getClass().getSimpleName(), ">>> RxCommand error " + error.getMessage());
                                        setExecuting(false);
                                        mOnError.postValue(error);
                                    })
                                    .onErrorResumeNext(throwable -> {
                                        return Observable.empty();
                                    })
                                    .doOnNext(dataResponse -> {
                                        LogUtils.d(getClass().getSimpleName(), ">>> RxCommand: doOnNext");
                                        setExecuting(false);
                                        mOnDataChanged.postValue(dataResponse);
                                    });
                        })
                        .doOnDispose(() -> setExecuting(false));
    }

    public Disposable subscribe(){
        return mOnExecuted.subscribe();
    }

    private void setExecuting(boolean isExecuting) {
        mIsExecuting = isExecuting;
        // emit live data
        mOnLoading.postValue(isExecuting);
    }

    public void execute(DataRequest dataRequest) {
        mTaskSubject.onNext(dataRequest);
    }

    public LiveData<DataResponse> onDataChanged() {
        return mOnDataChanged;
    }

    public LiveData<Boolean> onLoading() {
        return mOnLoading;
    }

    public LiveData<Throwable> onError() {
        return mOnError;
    }
}
