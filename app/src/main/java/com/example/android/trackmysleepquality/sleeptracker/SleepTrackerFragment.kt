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

package com.example.android.trackmysleepquality.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class SleepTrackerFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_tracker, container, false)

        // we need a reference to the application that this fragment is attached to
        // to pass into the ViewModelFactory provider
        val application = requireNotNull(this.activity).application

        // we need a reference to a data source via a reference to the DAO
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        // create an instance of the SleepTrackerViewModelFactory
        // pass it the data source as well as the application
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        // ask the ViewModelProvider for a SleepTrackerViewModel
        // we pass in our ViewModelFactory and request an instance of the SleepTrackerViewModel
        val sleepTrackerViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        // we specify the current activity as the lifecycle owner of the binding
        binding.setLifecycleOwner(this)

        // assign the sleepTrackerViewModel to the binding
        binding.sleepTrackerViewModel = sleepTrackerViewModel

        return binding.root
    }
}
