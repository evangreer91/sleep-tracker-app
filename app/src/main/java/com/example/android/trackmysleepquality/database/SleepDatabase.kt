/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// here we tell the database what entity to use, version number, and export schema
@Database(entities = [SleepNight::class], version = 1,  exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {
    //here we tell the database what dao to use
    abstract val sleepDatabaseDao: SleepDatabaseDao

    // companion object allows clients to access the methods without instantiating the class
    companion object {
        // declare a private nullable variable for the database
        // initialize it to null
        // this will help us avoid repeatedly opening connections to the database
        // always up to date and the same to all execution threads
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        // returns a reference to the sleep database
        fun getInstance(context: Context): SleepDatabase {
            // multiple threads can ask for a database instance
            // makes sure the database only gets initialized once
            synchronized(this) {
                var instance = INSTANCE

                // we invoke room database builder and supply the context that we passed in,
                // the database class, and the name of the database
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        // we just wipe and rebuild the database
                        // alternatively, a smart migration strategy can be defined here
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}