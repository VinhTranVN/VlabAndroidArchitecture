package vlab.android.architecture.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by Vinh.Tran on 10/25/18.
 **/
public interface IUseCase {
    /**
     * subscribes to observables
     */
    Disposable[] subscribes();
}
