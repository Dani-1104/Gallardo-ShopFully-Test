package com.example.gallardoshopfullytest.ui.flyerlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.gallardoshopfullytest.R
import com.example.gallardoshopfullytest.data.model.Flyer
import com.example.gallardoshopfullytest.ui.core.adapters.RootAdapter
import com.example.gallardoshopfullytest.ui.core.extension.loadFlyerImage

class FlyerListAdapter(onItemCLick: (Flyer) -> Unit) :
    RootAdapter<Flyer>(onItemClickListener = onItemCLick) {

    override val itemLayoutId: Int = R.layout.list_item_flyer

    override fun viewHolder(view: View): RootViewHolder<Flyer> = FlyerViewHolder(view)

    class FlyerViewHolder(view: View) : RootViewHolder<Flyer>(view) {
        override fun bind(model: Flyer) {
            val text = itemView.findViewById<TextView>(R.id.title)
            val image = itemView.findViewById<ImageView>(R.id.imageBackground)
            val readText = itemView.findViewById<TextView>(R.id.readText)

            text.text = model.title
            image.loadFlyerImage(model.id)
            readText.visibility = if (model.timesRead >= 1) View.VISIBLE else View.GONE
        }
    }

}
