# App Operasional PHM — Proyek Android (Versi 2)

Aplikasi Android dengan tampilan beranda native, splash screen logo PHM, menu pintasan, navigasi kembali, dan portal website PHM di dalam aplikasi.

- **Nama aplikasi:** App Operasional PHM
- **Perusahaan:** PT. PONDOK HIJAU MINNING
- **Package:** `com.phm.operasional`
- **Minimum Android:** 6.0 (API 23)
- **Portal:** https://pondokhijauminning.com/rekap/login.php

## Fitur aplikasi
- Splash screen dengan logo PHM.
- Beranda Android khusus dengan kartu menu Rekap PA & Alat Berat, Sewa Alat Berat, Logistik Order, serta Laporan & Rekap.
- Tombol masuk ke sistem PHM yang membuka portal dalam aplikasi.
- Tombol kembali dan muat ulang pada halaman portal.
- Tombol kembali Android mengikuti riwayat halaman web; dari beranda, pengguna diminta konfirmasi sebelum keluar.
- Mendukung pemilih file dari halaman web jika situs meminta upload dokumen.
- Data dan login tetap menggunakan server website PHM; aplikasi tidak menyimpan password di kode sumber.

## Membuat file APK
1. Pasang Android Studio dan Android SDK Platform 35.
2. Ekstrak ZIP proyek.
3. Android Studio → **Open** → pilih folder `PHMAndroid`.
4. Tunggu Gradle Sync dan izinkan Android Studio mengunduh komponen build yang diminta.
5. Pilih **Build → Build APK(s)**.
6. APK debug umumnya tersedia di `app/build/outputs/apk/debug/app-debug.apk`.

Untuk APK rilis, gunakan **Build → Generate Signed Bundle / APK** dan simpan keystore secara aman.

## Catatan pengujian
- Proyek sumber ini belum dikompilasi menjadi APK di lingkungan pembuatan ini karena Android SDK/Gradle tidak tersedia.
- Pintasan menu pada beranda saat ini membuka portal PHM. Rute halaman khusus untuk setiap modul belum diasumsikan karena perlu diverifikasi pada sistem setelah login.
- Memerlukan koneksi internet. Fitur portal yang tersedia tetap mengikuti akun dan hak akses pada website PHM.

## Build APK otomatis lewat GitHub (tanpa memasang Android Studio di HP)
Proyek ini menyertakan workflow `.github/workflows/android-apk.yml` untuk membangun APK di GitHub Actions.
1. Di browser, buat repository baru di GitHub.
2. Upload seluruh isi folder proyek ini ke repository (termasuk folder `.github`). Pastikan `app/`, `build.gradle`, `settings.gradle`, dan folder `.github` berada di akar repository.
3. Buka tab **Actions**, pilih **Build App Operasional PHM APK**, lalu tekan **Run workflow** jika workflow belum berjalan otomatis.
4. Setelah status selesai hijau, buka hasil run tersebut. Di bagian **Artifacts**, unduh `App-Operasional-PHM-APK`.
5. Ekstrak ZIP artifact dan pindahkan `app-debug.apk` ke HP Android. Buka file tersebut untuk memasang; jika diminta, izinkan instalasi dari sumber tersebut.

APK debug ditandatangani untuk pengujian, bukan rilis Play Store. Ponsel harus mengizinkan pemasangan aplikasi dari sumber yang dipilih. Build membutuhkan internet di GitHub Actions untuk mengunduh Gradle dan Android SDK.
