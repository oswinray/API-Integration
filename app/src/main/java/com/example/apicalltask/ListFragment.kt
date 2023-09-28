package com.example.apicalltask

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.registerReceiver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
    private var isNetworkAvailable = true
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
        checkInternetAndFetchData()


    }


    private fun checkInternetAndFetchData() {
        if (isInternetAvailable()) {

                // Network is available again, fetch the data
                isNetworkAvailable = true // Set the flag to true
                viewModel.fetchDataIfNetworkAvailable()

        } /*else {
            // No internet connection, start NoInternetActivity
            isNetworkAvailable = false // Set the flag to false
            val intent = Intent(requireContext(), NoInternetActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Optional: Finish the current activity
        }*/
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



    private fun observeLiveData() {
        viewModel.usersList.observe(this) {
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
        lifecycleScope.launch {
            val isItemAlreadyDownloaded = downloadViewModel?.isItemDownloaded(title)

            if (isItemAlreadyDownloaded == true) {
                showItemAlreadyDownloadedDialog(title)
                // Item is already downloaded, handle accordingly
            } else {
                // Item is not downloaded, proceed with the download and insertion
                val builder = AlertDialog.Builder(requireContext())
                val inflater = LayoutInflater.from(context)
                val dialogView = inflater.inflate(R.layout.dialog_item_downloaded, null)

                builder.setView(dialogView)

                val alertDialog = builder.create()

// Set a click listener for the "OK" button (optional)
                val buttonOK = dialogView.findViewById<Button>(R.id.buttonOK)
                buttonOK.setOnClickListener {
                    alertDialog.dismiss() // Close the dialog when "OK" is clicked
                }

                alertDialog.show()

                downloadViewModel?.insertTask(MovieLists(title, thumbnail))
            }
        }
    }
    private fun showItemAlreadyDownloadedDialog(title: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(R.string.item_dowmloaded)
        alertDialogBuilder.setMessage(Constants.itemAlreadyDownloaded)
        alertDialogBuilder.setPositiveButton(R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}


