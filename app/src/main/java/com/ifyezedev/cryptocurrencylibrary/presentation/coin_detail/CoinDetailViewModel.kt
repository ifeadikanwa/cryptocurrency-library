package com.ifyezedev.cryptocurrencylibrary.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifyezedev.cryptocurrencylibrary.common.Constants
import com.ifyezedev.cryptocurrencylibrary.common.Resource
import com.ifyezedev.cryptocurrencylibrary.domain.usecases.get_coin.GetCoinUseCase
import com.ifyezedev.cryptocurrencylibrary.domain.usecases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    //when we navigate to the coin detail screen we pass the coin id as a parameter using Navigation.
    //that parameter will be stored in our SavedStateHandle so we can retrieve it from there
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //we have a state variable that the viewmodel will provide to the composable.
    //this variable will use a data class we create and will keep track of if our call to get the coin is still loading
    //or if the list has been provided or if we got an error. so that we can use this information to act accordingly in our
    //composable without giving our composable the ability to modify this information.
    //the variable will persist configuration changes because it is a State.
    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        //on each flow emission we want to perform an action
        getCoinUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}