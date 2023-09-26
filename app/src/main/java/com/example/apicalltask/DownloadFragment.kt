package com.example.apicalltask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalltask.adapter.DownloadAdapter
import com.example.apicalltask.dao.ListDatabase
import com.example.apicalltask.viewmodel.DownloadViewModel

class DownloadFragment : Fragment() {
    lateinit var db: ListDatabase
    var recyclerView: RecyclerView? = null
    var Manager: GridLayoutManager? = null
    var adapter: DownloadAdapter? = null
    var viewModel:DownloadViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_download, container, false)
        viewModel = ViewModelProvider(this)[DownloadViewModel::class.java]
        // Initialize your views and set up the RecyclerView here
        recyclerView = view.findViewById(R.id.rv_design)
        Manager = GridLayoutManager(requireContext(), 2)
        recyclerView!!.layoutManager = Manager
        adapter = DownloadAdapter()
        recyclerView!!.adapter = adapter

//        db = ListDatabase.getDatabase(requireContext())!!

        Toast.makeText(requireContext(), "yes", Toast.LENGTH_SHORT).show()
        observerSetup()
        return view
    }

    private fun observerSetup(){
        viewModel?.getAllTask()?.observe(this,{

        })
    }


}