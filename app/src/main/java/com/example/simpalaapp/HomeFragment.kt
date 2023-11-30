package com.example.simpalaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simpalaapp.model.Reports
import com.example.simpalaapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitInstance.api.getReportsFromApi().enqueue(object: Callback<Reports>{
            override fun onResponse(call: Call<Reports>, response: Response<Reports>) {
                if(response.body() != null){
                    Log.d("REPORTS DATA", "${response.body()!!.reports[0]}")
                }
            }

            override fun onFailure(call: Call<Reports>, t: Throwable) {
                Log.e("ERROR FETCH", t.message.toString())
            }

        })
    }

}