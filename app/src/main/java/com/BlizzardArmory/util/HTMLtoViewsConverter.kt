package com.BlizzardArmory.util

import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.stfalcon.imageviewer.StfalconImageViewer
import java.util.regex.Matcher
import java.util.regex.Pattern

enum class ViewType {
    LINK,
    TEXT,
    IMAGE,
    TABLE,
    VIDEO,
}

class HTMLtoViewsConverter(val context: Context) {

    val linearLayout = LinearLayout(context)

    init {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            0,
            MetricConversion.getDPMetric(10, context),
            0,
            MetricConversion.getDPMetric(5, context)
        )
        linearLayout.layoutParams = params
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.tag = "content_layout"
    }

    fun parseHtml(source: String) {
        val pattern = Pattern.compile("<img.+?>|<iframe.+?>|<table(.|\\s)+?table>")
        val matcher = pattern.matcher(source)
        val splitSource = source.split("<img.+?>|<iframe.+?>|<table(.|\\s)+?table>".toRegex())
        val componentList = arrayListOf<Component>()

        if (splitSource.isEmpty() || splitSource.size == 1) {
            componentList.add(getComponent(ViewType.LINK, source))
        } else {
            var index = 0

            while (matcher.find()) {
                componentList.add(getComponent(ViewType.LINK, splitSource[index]))
                val patternImg = Pattern.compile("<img.+?>")
                val patternVid = Pattern.compile("<iframe.+?>")
                val patternTable = Pattern.compile("<table(.|\\s)+?table>")
                val matcherImg = patternImg.matcher(matcher.group())
                val matcherVid = patternVid.matcher(matcher.group())
                val matcherTable = patternTable.matcher(matcher.group())
                when {
                    matcherImg.find() -> {
                        componentList.add(getComponent(ViewType.IMAGE, matcherImg.group()))
                    }

                    matcherVid.find() -> {
                        componentList.add(getComponent(ViewType.VIDEO, matcherVid.group()))
                    }

                    matcherTable.find() -> {
                        componentList.add(getComponent(ViewType.TABLE, matcherTable.group()))
                    }
                }
                index++
            }
            componentList.add(getComponent(ViewType.LINK, splitSource[index]))
        }
        parseComponents(componentList)
    }

    private fun parseComponents(componentList: ArrayList<Component>) {
        for ((i, component) in componentList.withIndex()) {
            when (component.type) {
                ViewType.LINK -> {
                    val textView = TextView(context)
                    textView.setTextColor(Color.parseColor("#888888"))
                    component.info = component.info.replace("<a href", "</font><font color=#2788b5><a href")
                    component.info = component.info.replace("</a>", "</a></font>")
                    textView.text = HtmlCompat.fromHtml(component.info, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    textView.movementMethod = LinkMovementMethod.getInstance()
                    linearLayout.addView(textView)
                }

                ViewType.TEXT -> {
                    val textView = TextView(context)
                    textView.text = HtmlCompat.fromHtml("<font color=#2788b5>" + component.info + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                    textView.movementMethod = LinkMovementMethod.getInstance()
                    linearLayout.addView(textView)
                }

                ViewType.IMAGE -> {
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    val relativeLayout = RelativeLayout(context)
                    relativeLayout.layoutParams = params
                    val newImageView = ImageView(context)
                    newImageView.adjustViewBounds = true
                    relativeLayout.addView(newImageView, params)
                    val p =
                        Pattern.compile("https?.+?\\.png|https?.+?\\.jpg|https?.+?\\.jpeg|https?.+?\\.gif|https?.+?\\.svg|https?.+?\\.webp|https?.+?\\.ico|https?.+?\\.bmp")
                    val m: Matcher = p.matcher(component.info)
                    if (m.find()) {
                        Glide.with(context).load(m.group()).into(newImageView)
                        newImageView.setOnClickListener {
                            StfalconImageViewer.Builder(
                                context,
                                listOf(m.group())
                            ) { imageView, _ ->
                                Glide.with(context).load(m.group()).into(imageView)
                            }.withTransitionFrom(newImageView).show()
                        }
                    }
                    linearLayout.addView(relativeLayout)
                }

                ViewType.VIDEO -> {
                    val youtubePlayerView = YouTubePlayerView(context)
                    youtubePlayerView.tag = "youtube_view${i}"
                    youtubePlayerView.getPlayerUiController().showFullscreenButton(true)
                    youtubePlayerView.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            var videoId = ""
                            val p = Pattern.compile("embed/(\\w+)")
                            val m: Matcher = p.matcher(component.info)
                            if (m.find()) {
                                videoId = m.group(1)!!
                            }
                            youTubePlayer.cueVideo(videoId, 0f)
                        }
                    })
                    linearLayout.addView(youtubePlayerView)
                }

                ViewType.TABLE -> {
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    val webView = WebView(context)
                    webView.setBackgroundColor(0)
                    webView.loadData(component.info.replace("<table", "<table style=\"color: rgb(136, 136, 136)\""), "text/html", "UTF-8")
                    linearLayout.addView(webView, params)
                }
            }
        }
    }

    private fun getComponent(type: ViewType, info: String): Component {
        val component = Component()
        component.type = type
        component.info = info
        return component
    }
}

internal class Component {
    var type: ViewType = ViewType.TEXT
    var info: String = ""
}