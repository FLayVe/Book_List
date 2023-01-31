package com.example.book_list.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.book_list.R
import com.example.book_list.data.BookViewModel
import com.example.book_list.databinding.FragmentBookInfoBinding


class BookInfoFragment : Fragment() {

    private lateinit var binding: FragmentBookInfoBinding
    private lateinit var viewModel: BookViewModel

    private val args by navArgs<BookInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookInfoBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        //AppBar
        setHasOptionsMenu(true)

        //Set info
        binding.book = args.book

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_info, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> {
                viewModel.deleteBook(args.book)
                Toast.makeText(requireContext(), "Book was deleted!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_bookInfoFragment_to_listFragment)
            }
        }
        return true
    }

}