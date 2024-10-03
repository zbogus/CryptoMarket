package com.zahitbogus.cryptocrazyapp.viewmodel

import androidx.lifecycle.ViewModel
import com.zahitbogus.cryptocrazyapp.model.Crypto
import com.zahitbogus.cryptocrazyapp.repository.CryptoRepository
import com.zahitbogus.cryptocrazyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
): ViewModel(){
    suspend fun getCrypto(id: String) : Resource<Crypto>{
        return repository.getCrypto(id)
    }

}