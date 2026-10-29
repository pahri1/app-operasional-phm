package com.phm.operasional;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String HOME_URL = "https://pondokhijauminning.com/rekap/login.php";
    private static final int FILE_CHOOSER_REQUEST = 401;
    private static final int GREEN = Color.rgb(27, 117, 48);
    private static final int DARK_GREEN = Color.rgb(18, 78, 34);
    private static final int TEXT = Color.rgb(35, 45, 38);
    private static final int MUTED = Color.rgb(105, 117, 108);
    private WebView webView;
    private ProgressBar progressBar;
    private ValueCallback<Uri[]> filePathCallback;
    private boolean portalOpen = false;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(DARK_GREEN);
        getWindow().setNavigationBarColor(Color.WHITE);
        showSplash();
        new Handler(Looper.getMainLooper()).postDelayed(this::showHome, 950);
    }

    private void showSplash() {
        LinearLayout splash = new LinearLayout(this);
        splash.setOrientation(LinearLayout.VERTICAL);
        splash.setGravity(Gravity.CENTER);
        splash.setBackgroundColor(Color.WHITE);
        ImageView logo = new ImageView(this);
        logo.setImageResource(com.phm.operasional.R.drawable.phm_logo);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        splash.addView(logo, new LinearLayout.LayoutParams(dp(174), dp(174)));
        TextView title = text("App Operasional PHM", 23, GREEN, true);
        title.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(-1, -2);
        tp.topMargin = dp(18);
        splash.addView(title, tp);
        TextView subtitle = text("PT. PONDOK HIJAU MINNING", 12, MUTED, false);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.topMargin = dp(8);
        splash.addView(subtitle, sp);
        ProgressBar spinner = new ProgressBar(this);
        spinner.setIndeterminateTintList(android.content.res.ColorStateList.valueOf(GREEN));
        LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(dp(30), dp(30));
        pp.topMargin = dp(28);
        splash.addView(spinner, pp);
        setContentView(splash);
    }

    private void showHome() {
        portalOpen = false;
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.rgb(247, 249, 247));
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(20), dp(18), dp(20), dp(24));
        scroll.addView(page);

        LinearLayout brandRow = new LinearLayout(this);
        brandRow.setGravity(Gravity.CENTER_VERTICAL);
        ImageView logo = new ImageView(this);
        logo.setImageResource(com.phm.operasional.R.drawable.phm_logo);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        brandRow.addView(logo, new LinearLayout.LayoutParams(dp(54), dp(54)));
        LinearLayout brandText = new LinearLayout(this);
        brandText.setOrientation(LinearLayout.VERTICAL);
        brandText.setPadding(dp(12), 0, 0, 0);
        brandText.addView(text("APP OPERASIONAL PHM", 16, DARK_GREEN, true));
        TextView company = text("PT. PONDOK HIJAU MINNING", 10, MUTED, false);
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-2, -2);
        cp.topMargin = dp(4);
        brandText.addView(company, cp);
        brandRow.addView(brandText, new LinearLayout.LayoutParams(0, -2, 1));
        page.addView(brandRow);

        LinearLayout hero = new LinearLayout(this);
        hero.setOrientation(LinearLayout.VERTICAL);
        hero.setPadding(dp(20), dp(22), dp(20), dp(22));
        hero.setBackground(background(DARK_GREEN, 22));
        LinearLayout.LayoutParams hp = new LinearLayout.LayoutParams(-1, -2);
        hp.topMargin = dp(24);
        page.addView(hero, hp);
        TextView eyebrow = text("PORTAL OPERASIONAL", 11, Color.rgb(205, 237, 211), true);
        hero.addView(eyebrow);
        TextView welcome = text("Selamat Datang di PHM", 23, Color.WHITE, true);
        LinearLayout.LayoutParams wp = new LinearLayout.LayoutParams(-1, -2);
        wp.topMargin = dp(10);
        hero.addView(welcome, wp);
        TextView desc = text("Akses sistem kerja perusahaan melalui satu aplikasi yang praktis di ponsel Anda.", 14, Color.rgb(232, 244, 234), false);
        desc.setLineSpacing(dp(3), 1f);
        LinearLayout.LayoutParams dpv = new LinearLayout.LayoutParams(-1, -2);
        dpv.topMargin = dp(9);
        hero.addView(desc, dpv);
        TextView open = text("Masuk ke Sistem PHM   ›", 15, DARK_GREEN, true);
        open.setGravity(Gravity.CENTER);
        open.setPadding(dp(12), dp(15), dp(12), dp(15));
        open.setBackground(background(Color.WHITE, 14));
        open.setOnClickListener(v -> openPortal());
        LinearLayout.LayoutParams op = new LinearLayout.LayoutParams(-1, -2);
        op.topMargin = dp(20);
        hero.addView(open, op);

        TextView section = text("MENU OPERASIONAL", 13, TEXT, true);
        LinearLayout.LayoutParams secp = new LinearLayout.LayoutParams(-1, -2);
        secp.topMargin = dp(25);
        page.addView(section, secp);
        page.addView(featureCard("Rekap PA & Alat Berat", "Rekap unit, aktivitas, dan operasional alat berat.", "▦"));
        page.addView(featureCard("Sewa Alat Berat", "Akses data pendapatan dan transaksi sewa.", "Rp"));
        page.addView(featureCard("Logistik Order", "Akses proses permintaan dan pemantauan logistik.", "⇄"));
        page.addView(featureCard("Laporan & Rekap", "Lihat laporan sesuai menu dan hak akses akun.", "▤"));
        TextView note = text("Menu akan dibuka melalui sistem PHM. Fitur yang tersedia mengikuti hak akses akun Anda.", 11, MUTED, false);
        note.setGravity(Gravity.CENTER);
        note.setLineSpacing(dp(2), 1f);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-1, -2);
        np.topMargin = dp(16);
        page.addView(note, np);

        LinearLayout bottom = new LinearLayout(this);
        bottom.setGravity(Gravity.CENTER);
        bottom.setPadding(0, dp(16), 0, 0);
        TextView about = text("Tentang Aplikasi  •  PHM", 11, MUTED, false);
        bottom.addView(about);
        about.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setTitle("App Operasional PHM")
            .setMessage("Aplikasi pendamping sistem operasional PT. PONDOK HIJAU MINNING.\n\nData tetap dikelola oleh server website PHM dan memerlukan koneksi internet.")
            .setPositiveButton("Tutup", null).show());
        page.addView(bottom);
        setContentView(scroll);
    }

    private View featureCard(String title, String description, String symbol) {
        LinearLayout card = new LinearLayout(this);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(15), dp(14), dp(15), dp(14));
        card.setBackground(background(Color.WHITE, 16));
        card.setElevation(dp(1));
        LinearLayout.LayoutParams cardp = new LinearLayout.LayoutParams(-1, -2);
        cardp.topMargin = dp(10);
        TextView icon = text(symbol, symbol.equals("Rp") ? 15 : 22, GREEN, true);
        icon.setGravity(Gravity.CENTER);
        icon.setBackground(background(Color.rgb(233, 245, 235), 12));
        card.addView(icon, new LinearLayout.LayoutParams(dp(48), dp(48)));
        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);
        info.setPadding(dp(13), 0, 0, 0);
        info.addView(text(title, 14, TEXT, true));
        TextView sub = text(description, 11, MUTED, false);
        sub.setLineSpacing(dp(2), 1f);
        LinearLayout.LayoutParams subp = new LinearLayout.LayoutParams(-1, -2);
        subp.topMargin = dp(5);
        info.addView(sub, subp);
        card.addView(info, new LinearLayout.LayoutParams(0, -2, 1));
        TextView arrow = text("›", 25, GREEN, true);
        arrow.setPadding(dp(8), 0, 0, 0);
        card.addView(arrow);
        card.setOnClickListener(v -> openPortal());
        card.setClickable(true);
        card.setFocusable(true);
        // Store layout params for the caller.
        card.setLayoutParams(cardp);
        return card;
    }

    private void openPortal() {
        portalOpen = true;
        LinearLayout shell = new LinearLayout(this);
        shell.setOrientation(LinearLayout.VERTICAL);
        shell.setBackgroundColor(Color.WHITE);
        LinearLayout toolbar = new LinearLayout(this);
        toolbar.setGravity(Gravity.CENTER_VERTICAL);
        toolbar.setPadding(dp(8), dp(7), dp(12), dp(7));
        toolbar.setBackgroundColor(Color.WHITE);
        TextView back = text("‹", 32, DARK_GREEN, true);
        back.setGravity(Gravity.CENTER);
        toolbar.addView(back, new LinearLayout.LayoutParams(dp(42), dp(44)));
        back.setOnClickListener(v -> showHome());
        LinearLayout titles = new LinearLayout(this);
        titles.setOrientation(LinearLayout.VERTICAL);
        titles.setPadding(dp(8), 0, 0, 0);
        titles.addView(text("Sistem Operasional PHM", 14, TEXT, true));
        TextView domain = text("pondokhijauminning.com", 10, MUTED, false);
        LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(-2, -2);
        dlp.topMargin = dp(2);
        titles.addView(domain, dlp);
        toolbar.addView(titles, new LinearLayout.LayoutParams(0, -2, 1));
        TextView refresh = text("⟳", 24, GREEN, true);
        refresh.setGravity(Gravity.CENTER);
        toolbar.addView(refresh, new LinearLayout.LayoutParams(dp(38), dp(44)));
        refresh.setOnClickListener(v -> { if (webView != null) webView.reload(); });
        shell.addView(toolbar);
        View divider = new View(this);
        divider.setBackgroundColor(Color.rgb(228, 234, 229));
        shell.addView(divider, new LinearLayout.LayoutParams(-1, dp(1)));

        FrameLayout webFrame = new FrameLayout(this);
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(true);
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        settings.setLoadsImagesAutomatically(true);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String host = uri.getHost() == null ? "" : uri.getHost().toLowerCase();
                if (host.equals("pondokhijauminning.com") || host.endsWith(".pondokhijauminning.com")) return false;
                if ("https".equalsIgnoreCase(uri.getScheme())) {
                    try { startActivity(new Intent(Intent.ACTION_VIEW, uri)); } catch (Exception ignored) { }
                }
                return true;
            }
            @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
            }
            @Override public void onPageFinished(WebView view, String url) {
                if (progressBar != null) progressBar.setVisibility(View.GONE);
            }
            @Override public void onReceivedError(WebView view, WebResourceRequest request, android.webkit.WebResourceError error) {
                if (request.isForMainFrame()) Toast.makeText(MainActivity.this, "Halaman gagal dimuat. Periksa koneksi internet lalu tekan muat ulang.", Toast.LENGTH_LONG).show();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                    progressBar.setVisibility(newProgress >= 100 ? View.GONE : View.VISIBLE);
                }
            }
            @Override public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback, FileChooserParams params) {
                if (filePathCallback != null) filePathCallback.onReceiveValue(null);
                filePathCallback = callback;
                Intent intent = params.createIntent();
                try { startActivityForResult(intent, FILE_CHOOSER_REQUEST); }
                catch (Exception e) {
                    filePathCallback = null;
                    Toast.makeText(MainActivity.this, "Pemilih file tidak tersedia di perangkat ini.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(-1, dp(3), Gravity.TOP);
        webFrame.addView(webView, new FrameLayout.LayoutParams(-1, -1));
        webFrame.addView(progressBar, p);
        shell.addView(webFrame, new LinearLayout.LayoutParams(-1, 0, 1));
        setContentView(shell);
        webView.loadUrl(HOME_URL);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_REQUEST && filePathCallback != null) {
            Uri[] results = WebChromeClient.FileChooserParams.parseResult(resultCode, data);
            filePathCallback.onReceiveValue(results);
            filePathCallback = null;
        }
    }

    @Override public void onBackPressed() {
        if (portalOpen) {
            if (webView != null && webView.canGoBack()) webView.goBack();
            else showHome();
        } else {
            new AlertDialog.Builder(this)
                .setTitle("Keluar dari aplikasi?")
                .setMessage("Anda akan menutup App Operasional PHM.")
                .setNegativeButton("Batal", (d, w) -> d.dismiss())
                .setPositiveButton("Keluar", (d, w) -> finish())
                .show();
        }
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }
    private GradientDrawable background(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }
    private int dp(int value) { return (int)(value * getResources().getDisplayMetrics().density + 0.5f); }
}
