package com.rendiputra.githubuser.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rendiputra.githubuser.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getFavoriteUser(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("DELETE FROM user_table WHERE login = :login")
    suspend fun deleteUser(login: String)

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE login = :login)")
    fun isFavorite(login: String): Flow<Boolean>

}