package dev.agustacandi.learn.pestsentry.ui.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentBoardingBinding

class BoardingFragment : BaseFragment<FragmentBoardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBoardingBinding = FragmentBoardingBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewpager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    override fun initObservers() {
    }

}