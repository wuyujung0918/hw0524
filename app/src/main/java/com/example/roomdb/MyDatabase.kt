package com.example.roomdb


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext


@Database( entities = [ Product::class ], version = 1)
abstract class MyDatabase : RoomDatabase() {


   abstract fun getDao(): ProductDao

   companion object {

      var INST: MyDatabase? = null

      fun getInst( context: Context): MyDatabase {

         synchronized(this) {

            var instance = INST
            if (instance == null) {

               try{
               instance = Room.databaseBuilder(context,
                  MyDatabase::class.java, "mydatabase").build()}
               catch (e:Exception) {
                  print(e.message)
               }
               INST = instance

            }
            return instance!!
         }

      }

   }
}