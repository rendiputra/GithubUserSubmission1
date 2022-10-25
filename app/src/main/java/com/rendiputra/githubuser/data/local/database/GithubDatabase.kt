package com.rendiputra.githubuser.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rendiputra.githubuser.data.local.dao.UserDao
import com.rendiputra.githubuser.data.local.entity.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "usergithub_database"
        private var INSTANCE: GithubDatabase? = null

        fun getInstance(context: Context): GithubDatabase =
            INSTANCE ?: Room.databaseBuilder(
                context,
                GithubDatabase::class.java,
                DB_NAME
            ).build().apply { INSTANCE = this }
    }

}