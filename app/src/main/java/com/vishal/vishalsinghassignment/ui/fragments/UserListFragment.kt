package com.vishal.vishalsinghassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal.vishalsinghassignment.MainApplication
import com.vishal.vishalsinghassignment.R
import com.vishal.vishalsinghassignment.database.entity.User
import com.vishal.vishalsinghassignment.databinding.FragmentUserListBinding
import com.vishal.vishalsinghassignment.ui.adapter.RecylerViewAdapter
import com.vishal.vishalsinghassignment.viewmodel.MainViewModelFactory
import com.vishal.vishalsinghassignment.viewmodel.UserViewModel

class UserListFragment : Fragment() {
    private lateinit var mViewModel: UserViewModel
    private var binding: FragmentUserListBinding? = null
    private var adapter: RecylerViewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        setUpRecylerView()
        allUsers()         // fetching all users from local database

    }

    private fun initViewModel() {

        val repo = (activity?.application as MainApplication).repo
        val viewModelFactory = MainViewModelFactory(repo)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

    }

    private fun setUpRecylerView() {
        val recyclerView: RecyclerView = binding!!.viewDeveloper
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)

        // getting selected user data on item click & showing user details activity
        adapter = RecylerViewAdapter(object : RecylerViewAdapter.OnItemClickListener {
            override fun onItemClick(user: User) {
                findNavController(binding!!.root)
                    .navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user as User))
            }
        })

        recyclerView.adapter = adapter
    }


    private fun allUsers() {
            mViewModel.getAllUsers()?.observe(requireActivity()
            ) { userList: List<User?>? ->
                adapter?.setUserList(userList as ArrayList<User>)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
