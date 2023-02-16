package com.ibrahimethemsen.customview101

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.ibrahimethemsen.customview101.databinding.AvatarViewBinding

class AvatarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet) {

    private val binding = AvatarViewBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun setAvatar(avatar: Avatar) {
        if (avatar.avatarImage != null) {
            binding.avatarImage.apply {
                visibility = View.VISIBLE
                setImageResource(avatar.avatarImage)
            }
        } else {
            binding.apply {
                avatarImage.visibility = View.GONE
                val name = avatar.avatarName?.first()?.uppercase()
                val surname = avatar.avatarSurname?.first()?.uppercase()
                val title = context.getString(R.string.avatar_name_surname, name, surname)
                avatarText.text = title
            }
        }
    }
}
data class Avatar(
    @DrawableRes val avatarImage: Int? = null,
    val avatarName: String? = null,
    val avatarSurname: String? = null
)