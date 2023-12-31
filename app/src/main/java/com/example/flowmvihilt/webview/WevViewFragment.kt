package com.example.flowmvihilt.webview

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.lifecycle.lifecycleScope
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentWebviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WevViewFragment: BaseBindingFragment<FragmentWebviewBinding>({
    FragmentWebviewBinding.inflate(it)
}) {
    private var url: String?= null
    @Inject
    lateinit var wanWebViewClient: WanWebViewClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        url = arguments?.getString("url")
        setSetting()
        initWebView()
        initRefreshLoadMore()
    }

    private fun setSetting() {
        var isLoadPicMode = context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.getBoolean("loadPic", true)
        binding.webview.settings.apply {
            javaScriptEnabled = true //支持javascript

            setSupportZoom(true) // 设置可以支持缩放
            builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
            displayZoomControls = false //隐藏屏幕中的虚拟缩放按钮

            textZoom = 100 //字体百分比，替代原API:setTextSize

            allowFileAccess = true //设置可以访问文件

            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //自适应屏幕
            useWideViewPort = true //将图片调整到适合webview的大小
            loadWithOverviewMode = true // 缩放至屏幕的大小
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //优先使用缓存

            loadsImagesAutomatically = !isLoadPicMode!! //设置无图模式
        }
    }

    private fun initWebView() {
        with(binding.webview) {
            webViewClient = wanWebViewClient
            webChromeClient = WanWebChromeClient(
                loadProgress = {
                    binding.progressbar.progress = it //webview进度条更新
                }, loadFinish = {
                    lifecycleScope.launch {
                        delay(100)
                        binding.smartRefreshLayout.finishRefresh()
                        binding.progressbar.visibility = View.GONE  //隐藏webview进度条
                    }
                })
        }
        url?.let { binding.webview.loadUrl(it) }
    }

    private fun initRefreshLoadMore() {
        binding.smartRefreshLayout.setOnRefreshListener {
            binding.webview.reload()
            binding.smartRefreshLayout.finishRefresh()
        }
    }

}