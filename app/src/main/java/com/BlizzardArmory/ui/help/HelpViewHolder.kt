package com.BlizzardArmory.ui.help

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.help.HelpItem
import com.BlizzardArmory.util.ResourceNameToId
import com.BlizzardArmory.util.expendablecardview.ExpandableCardview
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer

class HelpViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.help_card, parent, false)) {

    private var card: ExpandableCardview? = null
    private var title: TextView? = null
    private var contentText: TextView? = null
    private var image: ImageView? = null

    init {
        card = itemView.findViewById(R.id.card)
        title = itemView.findViewById(R.id.title)
        contentText = itemView.findViewById(R.id.contentText)
        image = itemView.findViewById(R.id.image)
    }

    fun bind(helpItem: HelpItem) {

        title?.text = itemView.resources.getString(
            ResourceNameToId.getStringIdFromString(
                helpItem.title,
                itemView.context
            )
        )
        contentText?.text = itemView.resources.getString(
            ResourceNameToId.getStringIdFromString(
                helpItem.content,
                itemView.context
            )
        )
        val imageId = image?.resources?.getIdentifier(
            "@drawable/${helpItem.imageSrc}",
            "drawable",
            itemView.context.packageName
        )
        Glide.with(itemView).load(imageId).into(image!!)
        image?.setOnClickListener {
            StfalconImageViewer.Builder(
                itemView.context,
                listOf(helpItem.imageSrc)
            ) { imageView, _ ->
                Glide.with(itemView).load(imageId).into(imageView)
            }.withTransitionFrom(image).show()
        }
    }
}