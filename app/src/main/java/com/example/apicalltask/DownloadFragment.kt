package com.example.apicalltask

import DownloadAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalltask.dao.ListDatabase
import com.example.apicalltask.dao.MovieLists
import com.example.apicalltask.databinding.FragmentDownloadBinding
import com.example.apicalltask.databinding.ParentItemBinding
import com.example.apicalltask.viewmodel.DownloadViewModel

class DownloadFragment : Fragment() {
    lateinit var db: ListDatabase
    var recyclerView: RecyclerView? = null
    var Manager: GridLayoutManager? = null
    var adapter: DownloadAdapter? = null
    var viewModel:DownloadViewModel? = null
    private lateinit var binding : FragmentDownloadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_download, container, false)
        binding = FragmentDownloadBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DownloadViewModel::class.java]
        // Initialize your views and set up the RecyclerView here
        recyclerView = view.findViewById(R.id.rv_design)
        Manager = GridLayoutManager(requireContext(), 2)
        recyclerView!!.layoutManager = Manager
        adapter = DownloadAdapter(requireContext())
        recyclerView!!.adapter = adapter

        observerSetup()
    }

    private fun observerSetup() {
        viewModel?.getAllTask()?.observe(viewLifecycleOwner) { downloadedItems ->
            // Update the adapter with the new data received from the ViewModel
            if (downloadedItems.isEmpty()) {
                binding.tvMessage.visibility = View.VISIBLE
                binding.emptyImage.visibility = View.VISIBLE
            } else {
                binding.tvMessage.visibility = View.GONE
                binding.emptyImage.visibility = View.GONE
            }

            adapter?.setData(downloadedItems)
        }
    }



}