package com.vimal.kinzooandroidchallenge.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    fun readListType(): Flow<String>
}