package vlab.android.architecture.feature.home.viewmodel

import vlab.android.architecture.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Vinh.Tran on 11/14/18.
 */
class HomeViewModel : BaseViewModel {

    @Inject constructor() : super()

    var userName: String? = null
        get() = if (field == null) "Welcome Guest" else field
}
