package com.example.core.base.usecase

import com.example.core.response.BaseCommonResponse
import com.example.core.response.Resource
import com.example.core.response.ServerStatusCodes
import kotlinx.coroutines.*

/**
- Acts as a contract for all the use cases in our application:
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.

 * @param RequestType: a return type which is the result of the *Network Execution*.
 * @param Params: a parameters class which will be consumed inside the [invoke] function
in case we need extra data for our use case.
 * @param ResultType: the result returned after mapping the response  to the *View*
 */
abstract class BaseRemoteUseCase<RequestType : BaseCommonResponse, ResultType : Any, in Params> :
    BaseCommonUseCase<RequestType, ResultType, Params>() {
    operator fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (Resource<ResultType>) -> Unit = {}
    ) {
        scope.launch(handler(onResult) + Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            runFlow(executeRemote(params), onResult).collect {
                try {
                    if (it.status == ServerStatusCodes.SUCCESS.code) {
                        val res = mapper(it)
                        onResult.invoke(Resource.success(res))
                    } else {
                        showFailureMessage(onResult, it.message)
                    }
                }catch (e: Exception) {
                    showFailureMessage(
                        onResult,
                        it.message
                    )
                }
                onResult.invoke(Resource.loading(false))
            }
        }
    }
}