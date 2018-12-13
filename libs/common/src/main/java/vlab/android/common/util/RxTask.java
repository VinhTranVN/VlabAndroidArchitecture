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
    private PublishSubject<DataRequest> mRequestTaskSubject = PublishSubject.create();
    private PublishSubject<DataResponse> mResponseDataSubject = PublishSubject.create();

    private Observable<DataResponse> mOnExecuted;

    /* live data for data change and loading */
    private SingleLiveData<DataResponse> mOnSingleLiveDataChanged = new SingleLiveData<>();
    private MutableLiveData<DataResponse> mOnLiveDataChanged = new MutableLiveData<>();
    private SingleLiveData<Throwable> mOnError = new SingleLiveData<>();
    private SingleLiveData<Boolean> mOnLoading = new SingleLiveData<>();

    private boolean mIsExecuting = false;
    private Disposable mDisposable;

    public RxTask(Function<DataRequest, Observable<DataResponse>> func) {
        mOnExecuted = mRequestTaskSubject
                        .filter(requestData -> !mIsExecuting)
                        .flatMap(requestData -> {

                            setExecuting(true);

                            return func.apply(requestData)
                                    .doOnError(error -> {
                                        LogUtils.e(getClass().getSimpleName(), ">>> RxTask error " + error.getMessage());
                                        setExecuting(false);
                                        mOnError.postValue(error);
                                    })
                                    .onErrorResumeNext(throwable -> {
                                        return Observable.empty();
                                    })
                                    .doOnNext(dataResponse -> {
                                        LogUtils.d(getClass().getSimpleName(), ">>> RxTask: doOnNext");
                                        setExecuting(false);
                                        mResponseDataSubject.onNext(dataResponse);
                                        mOnSingleLiveDataChanged.postValue(dataResponse);
                                        mOnLiveDataChanged.postValue(dataResponse);
                                    });
                        })
                        .doOnDispose(() -> setExecuting(false));

        mDisposable = mOnExecuted.subscribe();
    }

    private void setExecuting(boolean isExecuting) {
        mIsExecuting = isExecuting;
        // emit live data
        mOnLoading.postValue(isExecuting);
    }

    /**
     * execute request task, called in Background Thread, respond data in Main Thread
     * @param dataRequest
     */
    public void execute(DataRequest dataRequest) {
        mRequestTaskSubject.onNext(dataRequest);
    }

    /**
     * cancel current request
     */
    public void cancel(){
        cancel(true);
    }

    /**
     *
     * @param isResubscribe true for re-subscribe, false for other
     */
    private void cancel(boolean isResubscribe) {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            LogUtils.w(getClass().getSimpleName(), ">>> RxTask canceled mDisposable.isDisposed() " + mDisposable.isDisposed());
            // resubscribe
            if(mDisposable.isDisposed() && isResubscribe){
                // resubscribe observable
                mDisposable = mOnExecuted.subscribe();
                LogUtils.i(getClass().getSimpleName(), ">>> RxTask resubscribe : mDisposable@" + mDisposable.hashCode());
            }
        }
    }

    public void destroy(){
        cancel(false);
        mDisposable = null;
    }

    /**
     * @return Observable
     */
    public Observable<DataResponse> onSuccessObservable() {
        return mResponseDataSubject;
    }

    /**
     * A lifecycle-aware observable that sends only new updates after subscription, used for events like
     * navigation and Snackbar messages.
     * <p>
     * This avoids a common problem with events: on configuration change (like rotation) an update
     * can be emitted if the observer is active. This LiveData only calls the observable if there's an
     * explicit call to setValue() or call().
     * <p>
     * Note that only one observer is going to be notified of changes.
     */
    public LiveData<DataResponse> onSingleLiveDataChanged() {
        return mOnSingleLiveDataChanged;
    }

    /**
     * @return LiveData
     */
    public LiveData<DataResponse> onLiveDataChanged() {
        return mOnLiveDataChanged;
    }

    public LiveData<Boolean> onLoading() {
        return mOnLoading;
    }

    public LiveData<Throwable> onError() {
        return mOnError;
    }
}
