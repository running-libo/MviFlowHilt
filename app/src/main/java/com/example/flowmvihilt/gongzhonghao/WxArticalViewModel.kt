package com.example.flowmvihilt.gongzhonghao

import com.example.basemodule.basemvi.BaseViewModel
import com.example.basemodule.basemvi.IUiIntent
import com.example.flowmvihilt.domain.repository.WxArticalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WxArticalViewModel @Inject constructor(val respository: WxArticalRepository) : BaseViewModel<WxArticalUiState, WxArticalIntent>() {
    override fun initUiState(): WxArticalUiState {
        return WxArticalUiState(DetailUiState.INIT)
    }

    override fun handleIntent(intent: IUiIntent) {
        when(intent) {
            is WxArticalIntent.gettitles -> {
                requestDataWithFlow(true,
                    request = { respository.getData()},
                    successCallBack = { data ->
                        sendUiState {
                            copy(
                                detailUiState = DetailUiState.SUCCESS(data)
                            )
                        }
                    })
            }
            is WxArticalIntent.getDataList -> {
                requestDataWithFlow(true,
                    request = { respository.getDataList(intent.id, intent.page)},
                    successCallBack = { data ->
                        sendUiState {
                            copy(
                                detailUiState = DetailUiState.LIST_SUCCESS(data.datas)
                            )
                        }
                    })
            }
        }
    }

}