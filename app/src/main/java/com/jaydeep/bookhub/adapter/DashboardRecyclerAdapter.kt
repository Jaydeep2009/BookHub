package com.jaydeep.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiContext
import androidx.recyclerview.widget.RecyclerView
import com.jaydeep.bookhub.R
import com.jaydeep.bookhub.model.Book
import org.w3c.dom.Text


class DashboardRecyclerAdapter(val context: Context , val itemList: ArrayList<Book>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int)
    {
        val book = itemList[position]
        holder.txtBookName.text=book.bookName
        holder.txtBookAuthorName.text=book.bookAuthor
        holder.txtBookPrice.text=book.bookCost
        holder.txtBookRating.text=book.bookrating
        //holder.imgBookImage.setBackgroundResource(book.bookImage)

        holder.content.setOnClickListener{
            Toast.makeText(context,"Clicked on ${holder.txtBookName.text}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtBookName: TextView = view.findViewById(R.id.bookName)
        val txtBookAuthorName: TextView= view.findViewById(R.id.authorName)
        val txtBookPrice: TextView= view.findViewById(R.id.price)
        val txtBookRating: TextView= view.findViewById(R.id.rating)
        val imgBookImage: ImageView= view.findViewById(R.id.book_img)
        val content: RelativeLayout = view.findViewById(R.id.content)

    }
}