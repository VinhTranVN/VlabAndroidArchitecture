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
    private PublishSubject<DataRequest> mTaskSubject = PublishSubject.create();

    private Observable<DataResponse> mOnExecuted;

    /* live data for data change and loading */
    private MutableLiveData<DataResponse> mOnDataChanged = new MutableLiveData<>();
    private MutableLiveData<Throwable> mOnError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mOnLoading = new MutableLiveData<>();

    private boolean mIsExecuting = false;
    private Disposable mDisposable;

    public RxTask(Function<DataRequest, Observable<DataResponse>> func) {
        mOnExecuted =
                mTaskSubject
                        .doOnNext(dataRequest -> System.out.println(">>> RxTask doOnNext 1 " + dataRequest))
                        .filter(requestData -> !mIsExecuting)
                        .doOnNext(dataRequest -> System.out.println(">>> RxTask doOnNext 2 " + dataRequest))
                        .flatMap(requestData -> {

                            setExecuting(true);

                            return func.apply(requestData)
                                    .doOnError(error -> {
                                        LogUtils.d(getClass().getSimpleName(), ">>> RxTask error " + error.getMessage());
                                        setExecuting(false);
                                        mOnError.postValue(error);
                                    })
                                    .onErrorResumeNext(throwable -> {
                                        return Observable.empty();
                                    })
                                    .doOnNext(dataResponse -> {
                                        LogUtils.d(getClass().getSimpleName(), ">>> RxTask: doOnNext 3");
                                        setExecuting(false);
                                        mOnDataChanged.postValue(dataResponse);
                                    });
                        })
                        .doOnDispose(() -> setExecuting(false));
    }

    public Disposable subscribe(){
        mDisposable = mOnExecuted.subscribe();
        return mDisposable;
    }

    private void setExecuting(boolean isExecuting) {
        mIsExecuting = isExecuting;
        // emit live data
        mOnLoading.postValue(isExecuting);
    }

    public void execute(DataRequest dataRequest) {
        mTaskSubject.onNext(dataRequest);
    }

    public void cancel(){
        mDisposable.dispose();
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
