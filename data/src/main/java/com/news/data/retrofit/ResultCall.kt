package com.news.data.retrofit

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ResultCall<T>(private val delegate: Call<T>) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(ResultCallback(this, callback))
    }

    override fun isExecuted() = delegate.isExecuted

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.success(delegate.execute().body()!!))
    }

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun clone() = ResultCall(delegate.clone())

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private class ResultCallback<T>(
        private val resultCall: ResultCall<T>,
        private val callback: Callback<Result<T>>
    ) : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result = if (response.isSuccessful) Result.success(response.body()!!) else
                Result.failure(HttpException(response))
            callback.onResponse(resultCall, Response.success(result))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val errorMessage = when (t) {
                is IOException -> "No internet connection"
                is HttpException -> "Something went wrong!"
                else -> t.localizedMessage
            }
            callback.onResponse(
                resultCall,
                Response.success(Result.failure(RuntimeException(errorMessage, t)))
            )
        }
    }
}
