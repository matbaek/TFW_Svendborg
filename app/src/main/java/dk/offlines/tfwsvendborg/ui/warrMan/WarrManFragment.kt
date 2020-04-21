package dk.offlines.tfwsvendborg.ui.warrMan

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import dk.offlines.tfwsvendborg.R
import kotlinx.android.synthetic.main.warr_man_fragment.*

class WarrManFragment: Fragment() {
    private lateinit var webview : WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val view = inflater.inflate(R.layout.warr_man_fragment, container, false)
        return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(webView: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    progressBar.visibility = View.VISIBLE
                }
                if (newProgress == 100) {

                    // There is a bug with Google Docs that sometimes you get blank screen
                    // instead of a PDF file. To avoid just reload when you get it. 4Head
                    if (webView?.contentHeight == 0) {
                        Toast.makeText(activity, "Loading...", Toast.LENGTH_SHORT).show()
                        webView.reload()
                    }


                    progressBar.visibility = View.GONE
                }
            }

        }


        val url = "http://offlines.dk/Warriors_Manual.pdf"
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url)
    }
    internal open class DefaultWebChromeClient : WebChromeClient() {

    }

}