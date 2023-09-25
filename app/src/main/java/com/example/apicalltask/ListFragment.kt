package com.example.apicalltask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalltask.adapter.ParentItemAdapter
import com.example.apicalltask.data.ApiClient
import com.example.apicalltask.databinding.FragmentListBinding
import com.example.apicalltask.databinding.ParentItemBinding
import com.example.apicalltask.viewmodel.ListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException



class ListFragment : Fragment() {
    private val apiService = ApiClient.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.parent_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()
    }


    private fun fetchData() {
        val call = apiService.getHomePage()
        Log.i("oswin2233", "fetchData: 81 " + call)

        call.enqueue(object : Callback<com.example.apicalltask.data.dataclass> {
            override fun onResponse(call: Call<com.example.apicalltask.data.dataclass>, response: Response<com.example.apicalltask.data.dataclass>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    Log.i("oswin2233", "onResponse: 86 " + apiResponse)
                    val homeContent = apiResponse?.response?.home_content
                    Log.i("oswin2233", "onResponse: 89 " +homeContent)

                    if (homeContent != null) {
                        val recyclerView: RecyclerView = view?.findViewById(R.id.child_recyclerview) ?: return

                        val adapter = ParentItemAdapter(homeContent)
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }



                else {
                    handleApiError()
                }
            }

            override fun onFailure(call: Call<com.example.apicalltask.data.dataclass>, t: Throwable) {
                if (t is IOException) {
                    // Handle network errors
                    Toast.makeText(context, R.string.network_error, Toast.LENGTH_SHORT).show()
                } else {
                    // Handle other errors
                    Log.e(TAG, "Error: ${t.message}")
                }
            }
        })
    }


    private fun handleApiError() {
        // Implement error handling for API requests here
        // You can show an error message to the user or perform other actions
        // based on your app's requirements.
    }

    companion object {
        private const val TAG = "ListFragment"
    }
}


