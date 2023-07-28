package com.example.market.domain.usecase

import com.example.market.data.models.MonitoringResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow


interface GetAllBuyUseCase {
    fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
}