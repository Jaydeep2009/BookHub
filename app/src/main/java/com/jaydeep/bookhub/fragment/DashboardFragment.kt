package com.jaydeep.bookhub.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaydeep.bookhub.R
import com.jaydeep.bookhub.adapter.DashboardRecyclerAdapter
import com.jaydeep.bookhub.model.Book


class DashboardFragment : Fragment() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val bookInfoList = arrayListOf<Book>(

        Book("P.S. I Love You", "Cecelia Ahern", "Rs. 299", "4.5", R.drawable.book_drawer),
        Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.book_drawer),
        Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.book_drawer),
        Book("Madame Bovary", "Gustave Flaubert", "Rs. 500", "4.0", R.drawable.book_drawer),
        Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.book_drawer),
        Book("Lolita", "Vladimir Nabokov", "Rs. 349", "3.9", R.drawable.book_drawer),
        Book("Middlemarch", "George Eliot", "Rs. 599", "4.2", R.drawable.book_drawer),
        Book("The Adventures of Huckleberry Finn", "Mark Twain", "Rs. 699", "4.5", R.drawable.book_drawer),
        Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.4", R.drawable.book_drawer),
        Book("The Lord of the Rings", "J.R.R. Tolkien", "Rs. 749", "5.0", R.drawable.book_drawer)

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard= view.findViewById(R.id.recyclerDashboard)
        layoutManager= LinearLayoutManager(activity)
        recyclerAdapter= DashboardRecyclerAdapter(activity as Context, bookInfoList)
        recyclerDashboard.adapter = recyclerAdapter
        recyclerDashboard.layoutManager=layoutManager

        return view
    }


}