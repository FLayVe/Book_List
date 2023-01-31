package com.example.book_list.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_list.R
import com.example.book_list.data.BookViewModel
import com.example.book_list.databinding.FragmentListBinding

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        //Navigation
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //AppBar
        setHasOptionsMenu(true)

        //List
        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = BookListAdapter()
        }

        viewModel.readAllBooks.observe(viewLifecycleOwner, Observer { books ->
            (binding.recyclerView.adapter as BookListAdapter).setData(books)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            val searchQuery ="%$query%"

            viewModel.searchBook(searchQuery).observe(viewLifecycleOwner, Observer{ books ->
                (binding.recyclerView.adapter as BookListAdapter).setData(books)
            })
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }
}