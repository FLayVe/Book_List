package com.example.book_list.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.book_list.R
import com.example.book_list.data.BookViewModel
import com.example.book_list.databinding.FragmentAddBinding
import com.example.book_list.model.Book


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        binding.addButton.setOnClickListener {
            val title = binding.title.text.toString()
            val author = binding.author.text.toString()
            val directions = binding.description.text.toString()

            if (!(TextUtils.isEmpty(title) && (TextUtils.isEmpty(author) && (TextUtils.isEmpty(directions))))){
                val book = Book(title, author, directions)
                viewModel.addBook(book)
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }

        return binding.root
    }

}