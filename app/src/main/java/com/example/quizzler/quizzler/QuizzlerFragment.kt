package com.example.quizzler.quizzler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quizzler.R
import com.example.quizzler.databinding.FragmentQuizzlerBinding

class QuizzlerFragment: Fragment() {

    private lateinit var binding: FragmentQuizzlerBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quizzler, container, false)

        return binding.root
    }
}