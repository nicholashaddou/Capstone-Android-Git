package com.c22ps175.playlab.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.c22ps175.playlab.data.CourseData
import com.c22ps175.playlab.databinding.ItemRowCourseListBinding

//Kalau kita mau pakai course dari API kita pakai ini, kalau coursenya mau kita hardcode secara manual berarti ini kita adaptasikan untuk show course dari device saja

class CourseListAdapter : RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {

    private val list = ArrayList<CourseData>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ViewHolder(private val binding: ItemRowCourseListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(courseData: CourseData) {
            binding.apply {
                Glide.with(itemView)
                    .load(courseData.courseImageURL)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(ivItemPhoto)
                tvUsername.text = courseData.courseName
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(courseData: CourseData) {
        list.clear()
        list.addAll(listOf(courseData))
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowCourseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(list[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: CourseData)
    }


}


