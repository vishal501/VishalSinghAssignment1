package com.vishal.vishalsinghassignment.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal.vishalsinghassignment.MainApplication
import com.vishal.vishalsinghassignment.R
import com.vishal.vishalsinghassignment.database.entity.User
import com.vishal.vishalsinghassignment.databinding.FragmentUserListBinding
import com.vishal.vishalsinghassignment.ui.adapter.RecyclerViewAdapter
import com.vishal.vishalsinghassignment.viewmodel.MainViewModelFactory
import com.vishal.vishalsinghassignment.viewmodel.UserViewModel

class UserListFragment : Fragment() {
    private lateinit var mViewModel: UserViewModel
    private var binding: FragmentUserListBinding? = null
    private var adapter: RecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel() // initializing viewModel
        setUpRecyclerView() // setup recyclerView for listing
        observeUsers() // observe and show the users
        setupSearchView() // filter users
    }

    private fun initViewModel() {
        val repo = (requireActivity().application as MainApplication).repo
        val viewModelFactory = MainViewModelFactory(repo)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = binding!!.viewDeveloper
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = RecyclerViewAdapter(object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(user: User) {
                findNavController(binding!!.root)
                    .navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user))
            }
        })
        recyclerView.adapter = adapter
    }

    private fun observeUsers() {
        mViewModel.getAllUsers()?.observe(viewLifecycleOwner) { userList ->
            userList?.let { adapter?.setUserList(it) }
        }
    }

    private fun setupSearchView() {
        binding?.searchView?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                adapter?.filter(s.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}