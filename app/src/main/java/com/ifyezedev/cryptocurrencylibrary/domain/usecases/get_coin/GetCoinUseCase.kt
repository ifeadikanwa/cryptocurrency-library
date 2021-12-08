package com.ifyezedev.cryptocurrencylibrary.domain.usecases.get_coin

import com.ifyezedev.cryptocurrencylibrary.common.Resource
import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.toCoin
import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.toCoinDetail
import com.ifyezedev.cryptocurrencylibrary.domain.model.Coin
import com.ifyezedev.cryptocurrencylibrary.domain.model.CoinDetail
import com.ifyezedev.cryptocurrencylibrary.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())

            val coin = repository.getCoinById(coinId).toCoinDetail()

            emit(Resource.Success(coin))
        }
        catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }
}