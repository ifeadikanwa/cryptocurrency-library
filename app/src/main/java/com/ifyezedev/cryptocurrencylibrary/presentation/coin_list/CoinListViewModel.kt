package com.ifyezedev.cryptocurrencylibrary.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifyezedev.cryptocurrencylibrary.common.Resource
import com.ifyezedev.cryptocurrencylibrary.domain.usecases.get_coin.GetCoinUseCase
import com.ifyezedev.cryptocurrencylibrary.domain.usecases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    //we have a state variable that the viewmodel will provide to the composable.
    //this variable will use a data class we create and will keep track of if our call to get the list of coins is still loading
    //or if the list has been provided or if we got an error. so that we can use this information to act accordingly in our
    //composable without giving our composable the ability to modify this information.
    //the variable will persist configuration changes because it is a State.
    private val _state = mutableStateOf<CoinListState>(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        //on each flow emission we want to perform an action
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}