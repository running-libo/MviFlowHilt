package com.example.basemodule.network


/**
 * create by libo
 * create on 2023/6/28
 * description 网络请求地址
 */
interface Api {

    companion object {
        const val SERVER_ADDRESS_RELEASE = "https://www.wanandroid.com/"

        /**
         * 首页
         */
        const val HOME_PAGE = "article/list/{page}/json"

        /**
         * 置顶数据
         */
        const val TOP_DATA = "article/top/json"

        /**
         * banner
         */
        const val BANNER = "banner/json"

        /**
         * 问答
         */
        const val QUESTION = "wenda/list/{page}/json"

        /**
         * 体系
         */
        const val SYSTEM = "tree/json"

        /**
         * 微信公众号标题
         */
        const val WX_ARTICAL = "wxarticle/chapters/json"

        /**
         * 微信公众号列表数据
         */
        const val WX_ARTICAL_LIST = "wxarticle/list/{id}/{page}/json"
    }

}