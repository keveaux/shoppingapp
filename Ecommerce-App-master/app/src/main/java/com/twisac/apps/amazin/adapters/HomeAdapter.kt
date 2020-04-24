package com.twisac.apps.amazin.adapters;

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.twisac.apps.amazin.ProductActivity
import com.twisac.apps.amazin.R
import com.twisac.apps.amazin.models.Product
import kotlinx.android.synthetic.main.item_product.view.*


class HomeAdapter(internal var context: Context, private val mHeaders: MutableList<Product>)//super(context, resource, objects);
    : androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var lastPosition = -1
    fun clear() {
        mHeaders.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var iv_thumbnail: ImageView = v.iv_thumbnail
        var tv_name: TextView = v.tv_name
        var tv_price : TextView = v.tv_price
        var ll_layout: LinearLayout = v.ll_layout
       // var rb_rating: RatingBar = v.rb_rating


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val header = mHeaders[position]

        /**
         * With Reduce duplication*/
        with (holder) {
            tv_name.text = header.name
            tv_price.text = "Ksh ${header.price.toString()}"

            val res = context.resources.getIdentifier(header.image, "drawable", context.packageName)
            iv_thumbnail.setImageResource(res)
            ll_layout.setOnClickListener {
                val intent = Intent(context, ProductActivity::class.java)
                intent.putExtra("name",header.name)
                intent.putExtra("id",header.id)
                intent.putExtra("price",header.price)
                intent.putExtra("image",header.image)
                intent.putExtra("description",header.description)
                intent .flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_product, parent, false)
       // parent.inflate(R.layout.headercard, parent, false)
        return ViewHolder(view)

    }

   override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
    /**extension function
     * */
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, parent: ViewGroup, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, parent, attachToRoot)
    }

    override fun getItemCount(): Int = mHeaders.size

}