package vlab.android.common.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 *  This is a wrapper class use RxJava to execute command, response a LiveData for consumers
 *
 *  Created by Vinh Tran on 2/18/18.
 */
public class RxCommand<DataRequest, DataResponse> {

    // execute command
    PublishSubject<Object> mRxCommand = PublishSubject.create();

    BehaviorSubject<Boolean> mOnExecuting = BehaviorSubject.createDefault(false);
    Observable<DataResponse> mOnExecuted;

    /* live data for data change and loading */
    MutableLiveData<DataResponse> mOnDataChanged = new MutableLiveData<>();
    MutableLiveData<Throwable> mOnError = new MutableLiveData<>();
    MutableLiveData<Boolean> mOnLoading = new MutableLiveData<>();

    public RxCommand(DataRequest dataRequest, Function<DataRequest, Observable<DataResponse>> func) {
        mOnExecuted = mRxCommand.withLatestFrom(mOnExecuting, (v, executing) -> executing)
                .filter(executing -> !executing)
                .flatMap(v -> {
                    setExecuting(true);
                    return func.apply(dataRequest)
                            .doOnError(error -> {
                                LogUtils.d(getClass().getSimpleName(), ">>> RxCommand error " + error.getMessage());
                                setExecuting(false);
                                mOnError.postValue(error);
                            })
                            .onErrorResumeNext(throwable -> {
                                return Observable.empty();
                            })
                            .doOnNext(dataResponse -> {
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
        mOnExecuting.onNext(isExecuting);
        // emit live data
        mOnLoading.postValue(isExecuting);
    }

    public void execute() {
        mRxCommand.onNext("");
    }

    public LiveData<DataResponse> onDataChanged() {
        return mOnDataChanged;
    }

    public LiveData<Throwable> onError() {
        return mOnError;
    }

    public LiveData<Boolean> onLoading() {
        return mOnLoading;
    }
}
