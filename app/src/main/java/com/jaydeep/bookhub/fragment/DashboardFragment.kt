package com.jaydeep.bookhub.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jaydeep.bookhub.R
import com.jaydeep.bookhub.adapter.DashboardRecyclerAdapter
import com.jaydeep.bookhub.model.Book
import com.jaydeep.bookhub.util.ConnectionManager


class DashboardFragment : Fragment() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: DashboardRecyclerAdapter
    lateinit var btnCheckInternet: Button

    val bookInfoList = arrayListOf<Book>(

//        Book("P.S. I Love You", "Cecelia Ahern", "Rs. 299", "4.5", R.drawable.book_drawer),
//        Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.book_drawer),
//        Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.book_drawer),
//        Book("Madame Bovary", "Gustave Flaubert", "Rs. 500", "4.0", R.drawable.book_drawer),
//        Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.book_drawer),
//        Book("Lolita", "Vladimir Nabokov", "Rs. 349", "3.9", R.drawable.book_drawer),
//        Book("Middlemarch", "George Eliot", "Rs. 599", "4.2", R.drawable.book_drawer),
//        Book("The Adventures of Huckleberry Finn", "Mark Twain", "Rs. 699", "4.5", R.drawable.book_drawer),
//        Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.4", R.drawable.book_drawer),
//        Book("The Lord of the Rings", "J.R.R. Tolkien", "Rs. 749", "5.0", R.drawable.book_drawer)

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard= view.findViewById(R.id.recyclerDashboard)
        layoutManager= LinearLayoutManager(activity)


        btnCheckInternet=view.findViewById(R.id.btnCheckInternet)
        btnCheckInternet.setOnClickListener {
            val dialog = AlertDialog.Builder(activity as Context)
            if(ConnectionManager().checkConnectivity(activity as Context)){

                dialog.setTitle("Success")
                dialog.setMessage("Internet connection found")
                dialog.setPositiveButton("Ok"){text, listener->
                    //do nothing
                }
                dialog.setNegativeButton("Cancel"){text,listener->
                    //do nothing
                }

            }else{
                dialog.setTitle("Failure")
                dialog.setMessage("Internet connection not found")
                dialog.setPositiveButton("Open Settings"){text, listener->
                    val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(settingsIntent)
                    activity?.finish()
                }
                dialog.setNegativeButton("Exit"){text,listener->
                    ActivityCompat.finishAffinity(activity as Activity)
                }
            }

            dialog.create()
            dialog.show()
        }

        val queue = Volley.newRequestQueue(activity as Context)

        val url = "https://dummyjson.com/products/"

        val jsonObjectRequest= object:JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener{

                try{
                    val data = it.getJSONArray("products")

                    for (i in 0 until data.length()) {
                        val bookJsonObject = data.getJSONObject(i)
                        val bookObject = Book(
                            bookJsonObject.getString("title"),
                            bookJsonObject.getString("title"),
                            bookJsonObject.getString("cost"),
                            bookJsonObject.getString("rating"),
                            bookJsonObject.getString("images")
                        )

                        bookInfoList.add(bookObject)

                        recyclerAdapter =
                            DashboardRecyclerAdapter(activity as Context, bookInfoList)
                        recyclerDashboard.adapter = recyclerAdapter
                        recyclerDashboard.layoutManager = layoutManager
                        recyclerDashboard.addItemDecoration(
                            DividerItemDecoration(
                                recyclerDashboard.context,
                                (layoutManager as LinearLayoutManager).orientation
                            )
                        )
                    }
                }catch(e:Exception){
                   Toast.makeText(activity as Context,"Error occured",Toast.LENGTH_SHORT)
                }

        }, Response.ErrorListener{
            println("Error is $it")
        }){
            override fun getHeaders(): MutableMap<String,String>{
                val headers= HashMap<String,String>()
                headers["Content-type"]= "application/json"
                headers["token"]="9bf534118365f1"
                return headers
            }
        }
        queue.add(jsonObjectRequest)
        return view
    }


}