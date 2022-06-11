package tahadeta.example.ftcover.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * This is the job for all coroutines started by this ViewModel.
 * Cancelling this job will cancel all coroutines started by this ViewModel.
 */
open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main
    val empty = MutableLiveData<Boolean>().apply { value = false }

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

    val toastMessage = MutableLiveData<String>()
    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    protected fun uiCoroutine(blockToRun: suspend () -> Unit) =
        CoroutineScope(Dispatchers.Main).launch() {
            blockToRun()
        }
}