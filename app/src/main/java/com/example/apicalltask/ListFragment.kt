package com.example.apicalltask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalltask.adapter.ParentItemAdapter
import com.example.apicalltask.dao.MovieLists
import com.example.apicalltask.data.ApiClient
import com.example.apicalltask.databinding.FragmentListBinding
import com.example.apicalltask.databinding.ParentItemBinding
import com.example.apicalltask.repository.ListRepository
import com.example.apicalltask.viewmodel.DownloadViewModel
import com.example.apicalltask.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException



class ListFragment : Fragment(),OnItemClickListener {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val viewModel by viewModels<ListViewModel>()
    var downloadViewModel: DownloadViewModel? = null
    private lateinit var binding : FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        downloadViewModel = ViewModelProvider(this).get(DownloadViewModel::class.java)
        showProgressBar()
        setUpViewModels()
        observeLiveData()
        fetchData()


    }


    private fun fetchData() {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.listOfItem()
        }
    }

    private fun observeLiveData() {
        viewModel.usersList.observe(this) {
            Log.i("oswin2233", "observeLiveData: 57")
            val recyclerView: RecyclerView =
                view?.findViewById(R.id.child_recyclerview) ?: return@observe

            val adapter = context?.let { it1 -> ParentItemAdapter(it.response.home_content, it1,this) }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            hideProgressBar()
        }
    }
    private fun setUpViewModels() {
        val service = ApiClient.create()
        viewModel.listRepo = ListRepository(service)
    }
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onItemClick(title: String, thumbnail: String) {
        Log.i("oswin2222", "onItemClick: 87")
        downloadViewModel?.insertTask(MovieLists(title,thumbnail))

    }

}


