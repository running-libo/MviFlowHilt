package com.example.flowmvihilt.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * 支持View Binding Activity
 */
abstract class BaseBindingActivity<VB : ViewBinding>(
    val block: (LayoutInflater) -> VB
) : AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding) { "The property of binding has been destroyed." }

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = block(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}