package com.amrdeveloper.askme.viewmodels

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.amrdeveloper.askme.models.Notification
import com.amrdeveloper.askme.net.AskmeClient
import com.amrdeveloper.askme.net.DEFAULT_QUERY_COUNT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationDataSource(private var userId: String,
                             private val token: String,
                             private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Notification>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Notification>
    ) {
        scope.launch(Dispatchers.IO){
            try {
                val notifications = AskmeClient.getNotificationService()
                    .getUserNotifications(userId = userId, token = "auth $token")
                if (notifications.size == DEFAULT_QUERY_COUNT) {
                    callback.onResult(notifications, null, 1)
                } else {
                    callback.onResult(notifications, null, 0)
                }
            }catch (exception : Exception) {
                Log.d("Notification", "Invalid Request")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Notification>) {
        scope.launch(Dispatchers.IO){
            try {
                val notifications = AskmeClient.getNotificationService()
                    .getUserNotifications(userId = userId, token = "auth $token")
                if (params.key > 1) {
                    callback.onResult(notifications, params.key - 1)
                } else {
                    callback.onResult(notifications, null)
                }
            }catch (exception : Exception) {
                Log.d("Notification", "Invalid Request")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Notification>) {
        scope.launch(Dispatchers.IO){
            try{
                val notifications = AskmeClient.getNotificationService()
                    .getUserNotifications(userId = userId, token = "auth $token")
                if (notifications.size == DEFAULT_QUERY_COUNT) {
                    callback.onResult(notifications, params.key + 1)
                } else {
                    callback.onResult(notifications, null)
                }
            }catch (exception : Exception) {
                Log.d("Notification", "Invalid Request")
            }
        }
    }
}