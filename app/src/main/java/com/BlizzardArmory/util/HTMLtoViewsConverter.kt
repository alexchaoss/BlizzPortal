package com.BlizzardArmory.util

import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.regex.Matcher
import java.util.regex.Pattern


class HTMLtoViewsConverter(val context: Context) {

    val linearLayout = LinearLayout(context)

    init {
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.layoutParams = params
        linearLayout.orientation = LinearLayout.VERTICAL
    }

    fun parseHtml(source: String) {
        val pattern = Pattern.compile("<a href.+?</a>|<img.+?>|<iframe.+?>")
        /*val patternImg = Pattern.compile("<img.+?>")
        val patternVid = Pattern.compile("<iframe.+?>")
        val matcherLinks = patternLinks.matcher(source)
        val matcherImg = patternImg.matcher(source)*/
        val matcher = pattern.matcher(source)

        val splitSource = source.split("<a href.+?</a>|<img.+?>|<iframe.+?>".toRegex())
        val componentList = arrayListOf<Component>()
        if (splitSource.isEmpty() || splitSource.size == 1) {
            componentList.add(getComponent(1, source)!!)
        } else {
            var index = 0
            while (matcher.find()) {
                componentList.add(getComponent(1, splitSource[index])!!)
                val patternLinks = Pattern.compile("<a href.+?</a>")
                val patternImg = Pattern.compile("<img.+?>")
                val patternVid = Pattern.compile("<iframe.+?>")
                val matcherLinks = patternLinks.matcher(matcher.group())
                val matcherImg = patternImg.matcher(matcher.group())
                val matcherVid = patternVid.matcher(matcher.group())
                when {
                    matcherLinks.find() -> {
                        componentList.add(getComponent(2, matcherLinks.group())!!)
                    }
                    matcherImg.find() -> {
                        componentList.add(getComponent(3, matcherImg.group())!!)
                    }
                    matcherVid.find() -> {
                        componentList.add(getComponent(4, matcherVid.group())!!)
                    }
                }
                index++
            }
        }
        componentList.forEach { Log.i("TEST", it.type.toString()) }
        for (component in componentList) {
            when (component.type) {
                1 -> {
                    val textView = TextView(context)
                    textView.setTextColor(Color.parseColor("#888888"))
                    textView.textSize = 14F
                    textView.text = HtmlCompat.fromHtml(component.info!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    linearLayout.addView(textView)
                }
                2 -> {

                    val textView = TextView(context)
                    textView.text = HtmlCompat.fromHtml(component.info!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    textView.movementMethod = LinkMovementMethod.getInstance()
                    linearLayout.addView(textView)
                }
                3 -> {
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    val relativeLayout = RelativeLayout(context)
                    relativeLayout.layoutParams = params
                    val imageView = ImageView(context)
                    imageView.adjustViewBounds = true
                    relativeLayout.addView(imageView, params)
                    val p = Pattern.compile("https.+?\\.png|https.+?\\.jpg|https.+?\\.jpeg")
                    val m: Matcher = p.matcher(component.info!!)
                    if (m.find()) {
                        Glide.with(context).load(m.group()).into(imageView)
                    }
                    linearLayout.addView(relativeLayout)
                }
                4 -> {
                    val youtubePlayerView = YouTubePlayerView(context)
                    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            var videoId = ""
                            val p = Pattern.compile("embed/(\\w+)")
                            val m: Matcher = p.matcher(component.info!!)
                            if (m.find()) {
                                videoId = m.group(1)!!
                            }
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                    linearLayout.addView(youtubePlayerView)
                }
            }
        }
    }

    private fun getComponent(type: Int, info: String): Component? {
        val component = Component()
        component.type = type
        component.info = info
        return component
    }
}

internal class Component {
    var type = 0
    var info: String? = null
}