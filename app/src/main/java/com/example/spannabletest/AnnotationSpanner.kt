package com.example.spannabletest

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.*

class AnnotationSpanner {

    fun applyAnnotationSpans(spannedString: SpannedString): SpannableString {
        val annotations = spannedString.getSpans(0, spannedString.length, android.text.Annotation::class.java)
        val spannableString = SpannableString(spannedString)
        for (annotation in annotations) {
            spannableString.setSpan(newSpan(annotation.key, annotation.value),
                    spannedString.getSpanStart(annotation),
                    spannedString.getSpanEnd(annotation),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        }
        return spannableString
    }

    fun newSpan(spanType:String, args:String): CharacterStyle{
        return when(spanType){
            "strike" -> StrikethroughSpan()
            "under" -> UnderlineSpan()
            "relative" -> RelativeSizeSpan(args.toFloat())
            "abs" -> {
                var a = args.split(',')
                AbsoluteSizeSpan(a[0].toInt(), a[1].toBoolean())
            }
            "bgcolor" -> BackgroundColorSpan(Color.parseColor(args))
            "fgcolor" -> ForegroundColorSpan(Color.parseColor(args))
            "style" -> StyleSpan(args.toStyle())
            "typeface" -> TypefaceSpan(args)
            else -> RelativeSizeSpan(1f)
        }
    }
}

//style string i.e. "bold | italic"
fun String.toStyle():Int{
    var style = 0
    this.replace("\\s".toRegex(), "").split('|').forEach {
        val s = when(it.toLowerCase()){
            "bold" -> 1
            "italic" -> 2
            "bold_italic" -> 3
            else -> 0
        }
        style = style or s
    }
    return style
}