package com.ifyezedev.cryptocurrencylibrary.domain.usecases.get_coins

import com.ifyezedev.cryptocurrencylibrary.common.Resource
import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.toCoin
import com.ifyezedev.cryptocurrencylibrary.domain.model.Coin
import com.ifyezedev.cryptocurrencylibrary.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())

            val coins = repository.getCoins().map { coinDto ->
                coinDto.toCoin()
            }

            emit(Resource.Success<List<Coin>>(coins))
        }
        catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server check your internet connection"))
        }
    }
}