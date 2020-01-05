package com.amrdeveloper.askme.net

import com.amrdeveloper.askme.models.ReactionData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReactionService {

    @POST("reacts/react")
    suspend fun createAnswerReaction(
        @Header("authorization") token : String,
        @Body reactionData: ReactionData
    ) : Response<String>

    @POST("reacts/unreact")
    suspend fun deleteAnswerReaction(
        @Header("authorization") token : String,
        @Body reactionData: ReactionData
    ) : Response<String>
}