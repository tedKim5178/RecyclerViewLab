package com.mk.recyclerviewlab

import android.content.Context
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mk.recyclerviewlab.databinding.ItemCarBinding

class MainActivity : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var adapter: CarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = findViewById<RecyclerView>(R.id.list)

        adapter = CarAdapter()
        val layoutManager = CarLayoutManager(this)
        list.layoutManager = layoutManager
        list.adapter = adapter


        val fuck = mutableListOf<Car>()
        for (i in 0..50) {
            fuck.add(Car(i.toString(), i.toString()))
        }
        adapter.submitList(fuck)


        val btn = findViewById<Button>(R.id.btn_log)
        btn.setOnClickListener {
            Log.d("[MK]", "${layoutManager.height}")
        }
    }
}

class CarLayoutManager(context: Context): RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        TODO("Not yet implemented")
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }
}

class CarAdapter: ListAdapter<Car, RecyclerView.ViewHolder>(CarDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarViewHolder(
            ItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val car = (getItem(position) as Car)
        (holder as CarViewHolder).bind(car)
    }

    class CarDiffCallback : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }
    }

    class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.tvCar.text = car.name
        }
    }
}

data class Car(
    val id: String,
    val name: String
)