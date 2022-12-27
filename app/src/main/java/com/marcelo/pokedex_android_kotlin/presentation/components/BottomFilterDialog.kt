package com.marcelo.pokedex_android_kotlin.presentation.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marcelo.pokedex_android_kotlin.databinding.BottomSheetItemSortBinding

class BottomFilterDialog() : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetItemSortBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetItemSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSmall.setOnClickListener {
            onItemClickListener?.invoke("small")
        }
        binding.btnHigh.setOnClickListener {
            onItemClickListener?.invoke("high")
        }
        binding.btnAZ.setOnClickListener {
            onItemClickListener?.invoke("a-z")
        }
        binding.btnZA.setOnClickListener {
            onItemClickListener?.invoke("z-a")
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}