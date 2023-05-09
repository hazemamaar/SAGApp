package com.example.core.usecase

import com.example.core.response.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class LocalUseCase<Domain, in Body> : UseCase<Domain, Body>() {

    protected abstract fun executeLocalDS(body: Body? = null): Flow<Domain>

    override operator fun invoke(
        scope: CoroutineScope, body: Body?, multipleInvoke: Boolean,
        onResult: (Resource<Domain>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            if (multipleInvoke.not())
                onResult.invoke(Resource.loading())

            runFlow(executeLocalDS(body), onResult).collect {
                onResult.invoke(invokeSuccessState(it))

                if (multipleInvoke.not())
                    onResult.invoke(Resource.loading(false))
            }
        }
    }

    override operator fun invoke(body: Body?, multipleInvoke: Boolean): Flow<Resource<Domain>> =
        channelFlow {
            if (multipleInvoke.not())
                send(Resource.loading())

            runFlow(executeLocalDS(body)) {
                send(it)
            }.collect {
                send(invokeSuccessState(it))

                if (multipleInvoke.not())
                    send(Resource.loading(false))
            }
        }

    override fun validateResponseModel(domain: Domain): Resource<Domain>? = null

    override suspend fun <M> runFlow(
        requestExecution: Flow<M>, onResult: suspend (Resource<Domain>) -> Unit
    ): Flow<M> = requestExecution.catch { e ->
        onResult.invoke(Resource.failure(e.localizedMessage))
        onResult.invoke(Resource.loading(false))
    }.flowOn(Dispatchers.IO)

//    private fun catchFlowException(throwable: Throwable): CareException = when (throwable) {
//        is CareException.Unexpected -> CareException.Unexpected(throwable.messageRes)
//        else -> {
//            throwable.toString().toLog("catchFlowException")
//            CareException.IOLocal(R.string.error_io_unexpected_message)
//        }
//    }
}