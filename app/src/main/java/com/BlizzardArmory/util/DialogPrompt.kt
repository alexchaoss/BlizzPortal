package com.BlizzardArmory.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.BlizzardArmory.R

class DialogPrompt(val context: Context) {

    private val builder = AlertDialog.Builder(context, R.style.DialogBlizzPortal)
    private var dialog: AlertDialog? = null
    private val layoutParams =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    private val buttonParams =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    private val container = LinearLayout(context)
    val tagMap = mutableMapOf<String, View>()

    inner class Button(val text: String, val size: Float, val onClick: () -> Unit, val tag: String = "", val color: Int = Color.WHITE, val bcontext: Context = context) {

        fun button(): android.widget.Button {
            val button = Button(context)
            button.text = text
            button.textSize = size
            button.setTextColor(color)
            button.gravity = Gravity.CENTER
            button.layoutParams = buttonParams
            button.setPadding(15, 0, 15, 0)
            button.background = ContextCompat.getDrawable(context, R.drawable.buttonstyle)
            button.setOnClickListener { onClick() }
            return button
        }
    }

    init {
        layoutParams.gravity = Gravity.CENTER
        dialog = builder.create()
        dialog!!.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog!!.setCancelable(true)
        container.orientation = LinearLayout.VERTICAL
        container.gravity = Gravity.CENTER
        container.setPadding(30, 30, 30, 30)
        buttonParams.setMargins(10, 20, 10, 20)
        buttonParams.weight = 1f
    }

    fun addCustomView(view: View, tag: String = "") {
        container.addView(view)
        if (tag != "") {
            tagMap[tag] = view
        }
    }

    fun addTitle(text: String, size: Float, tag: String = ""): DialogPrompt {
        val textView = TextView(context)
        textView.textSize = size
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.setPadding(0, 20, 0, 20)
        textView.layoutParams = layoutParams
        textView.setTextColor(Color.WHITE)
        textView.text = text
        container.addView(textView)
        if (tag != "") {
            tagMap[tag] = textView
        }
        return this
    }

    fun addMessage(text: String, size: Float, tag: String = ""): DialogPrompt {
        val textView = TextView(context)
        textView.textSize = size
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.layoutParams = layoutParams
        textView.setTextColor(Color.WHITE)
        textView.text = text
        container.addView(textView)
        if (tag != "") {
            tagMap[tag] = textView
        }
        return this
    }

    fun addEditText(tag: String = ""): DialogPrompt {
        val editText = EditText(context)
        editText.setTextColor(Color.WHITE)
        editText.textSize = 16f
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            editText.textCursorDrawable = null
        }
        val colorStateList = ColorStateList.valueOf(Color.WHITE)
        editText.backgroundTintList = colorStateList
        container.addView(editText)
        if (tag != "") {
            tagMap[tag] = editText
        }
        return this
    }

    fun addAutoCompleteEditText(tag: String = "", autoCompleteList: List<String>): DialogPrompt {
        val autoCompleteTextView = AutoCompleteTextView(context)
        autoCompleteTextView.setTextColor(Color.WHITE)
        autoCompleteTextView.textSize = 16f
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            autoCompleteTextView.textCursorDrawable = null
        }
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, autoCompleteList)
        autoCompleteTextView.setAdapter(adapter)
        val colorStateList = ColorStateList.valueOf(Color.WHITE)
        autoCompleteTextView.backgroundTintList = colorStateList
        container.addView(autoCompleteTextView)
        if (tag != "") {
            tagMap[tag] = autoCompleteTextView
        }
        return this
    }

    fun addButtons(vararg buttons: Button): DialogPrompt {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.gravity = Gravity.CENTER
        container.addView(linearLayout)

        for (button in buttons) {
            val newButton = button.button()
            linearLayout.addView(newButton)
            if (button.tag != "") {
                tagMap[button.tag] = newButton
            }
        }
        return this
    }

    fun addSpinner(spinnerList: Array<String>, tag: String = ""): DialogPrompt {
        val layoutParamsSpinner = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, MetricConversion.getDPMetric(40, context))
        val spinner = Spinner(context)
        spinner.background = ContextCompat.getDrawable(context, R.drawable.wow_spinner)
        spinner.gravity = Gravity.CENTER
        spinner.textAlignment = View.TEXT_ALIGNMENT_CENTER
        spinner.setPadding(0, 0, 0, 0)
        spinner.layoutParams = layoutParamsSpinner
        container.addView(spinner)
        if (tag != "") {
            tagMap[tag] = spinner
        }
        setAdapter(spinnerList, spinner)
        return this
    }

    private fun setAdapter(spinnerList: Array<String>, spinner: Spinner) {
        val arrayAdapter: ArrayAdapter<*> = object : ArrayAdapter<String?>(context, android.R.layout.simple_dropdown_item_1line, spinnerList) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.textSize = 18f
                tv.gravity = Gravity.CENTER
                tv.setBackgroundColor(Color.parseColor("#272931"))
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.WHITE)
                }
                return view
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                try {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 18f
                    view.gravity = Gravity.CENTER
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    fun show() {
        dialog!!.show()
        dialog!!.window?.setGravity(Gravity.CENTER)
        dialog!!.window?.setLayout(MetricConversion.getDPMetric(320, context), WindowManager.LayoutParams.WRAP_CONTENT)
        dialog!!.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        dialog!!.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog!!.addContentView(container, layoutParams)
    }

    fun dismiss() {
        dialog?.dismiss()
    }

    fun setCancellable(cancellable: Boolean) {
        dialog?.setCancelable(cancellable)
    }

    fun setOnCancelListener(cancelListerner: () -> Unit): DialogPrompt {
        dialog?.setOnCancelListener { cancelListerner() }
        return this
    }
}