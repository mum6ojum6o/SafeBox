
package com.mumbojumbo.safebox.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mumbojumbo.safebox.room.daos.CategoryDao
import com.mumbojumbo.safebox.room.daos.CredetialDao
import com.mumbojumbo.safebox.room.entities.Category
import com.mumbojumbo.safebox.room.entities.Credential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Category::class,Credential::class),version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun credentialDao(): CredetialDao


    private class AppDatabaseCallback(private val scope:CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db:SupportSQLiteDatabase){
            super.onOpen(db)
            INSTANCE?.let {
                    database: AppDatabase -> scope.launch{
                populateCategories(database.categoryDao())
                populateDummyCredentials(database.credentialDao())
                }
            }
        }
        fun populateCategories(categoryDao: CategoryDao){
            Thread(Runnable() {
                categoryDao.deleteAll()
                categoryDao.add(Category(0,"Shopping"))
                categoryDao.add(Category(0,"Streaming"))
                categoryDao.add(Category(0,"Govt"))
                categoryDao.add(Category(0,"Random"))

            }).start()

        }

        fun populateDummyCredentials(credentialDao:CredetialDao){
            Thread(Runnable {
                credentialDao.add(Credential(0,"test","test_PWd","test",52))
            }).start()
        }
    }



    companion object {
        @Volatile
        private var INSTANCE:AppDatabase?=null
        fun getDatabase(context: Context,scope: CoroutineScope): AppDatabase {
            val tempInstance = this.INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "safebox_database"
                )/*.addCallback(AppDatabaseCallback(scope))*/
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}