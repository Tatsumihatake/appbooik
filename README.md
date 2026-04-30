# appBook2 - Aplikasi Katalog Buku

**appBook2** adalah aplikasi Android berbasis Kotlin yang dirancang untuk memberikan pengalaman menjelajah katalog buku dengan antarmuka yang modern dan intuitif. Aplikasi ini dilengkapi dengan fitur onboarding yang menarik dan sistem navigasi yang mudah digunakan.

## 🚀 Fitur Utama

- **Splash Screen**: Layar pembuka yang profesional saat aplikasi pertama kali dijalankan.
- **Onboarding Interaktif**: Memandu pengguna baru melalui fitur-fitur utama aplikasi menggunakan `ViewPager2`.
- **Navigasi Menu Bawah**: Perpindahan halaman yang mulus antara **Home**, **Search**, dan **Library**.
- **Penyimpanan Status**: Menggunakan `SharedPreferences` untuk memastikan layar onboarding hanya muncul pada peluncuran pertama.
- **UI Responsif**: Desain yang bersih dan modern dengan fokus pada keterbacaan.

## 🛠️ Teknologi yang Digunakan

- **Bahasa**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: Android SDK (XML Layouts)
- **Komponen**:
  - `ViewPager2` (untuk Onboarding)
  - `BottomNavigationView` (untuk Navigasi)
  - `SharedPreferences` (untuk Manajemen Status)
- **Tools**: Android Studio, Gradle (KTS)

## 📋 Prasyarat

Sebelum menjalankan proyek ini, pastikan Anda memiliki:
- Android Studio Ladybug atau versi terbaru.
- JDK 17 atau yang lebih baru.
- Koneksi internet untuk mengunduh dependensi Gradle.

## ⚙️ Instalasi

1. **Clone repositori ini:**
   ```bash
   git clone https://github.com/Tatsumihatake/appbooik.git
   ```
2. **Buka proyek di Android Studio:**
   - Pilih `File > Open`.
   - Arahkan ke folder hasil clone.
3. **Sinkronisasi Gradle:**
   - Tunggu hingga proses sinkronisasi selesai.
4. **Jalankan Aplikasi:**
   - Hubungkan perangkat Android atau jalankan Emulator.
   - Klik tombol **Run** (Ikon Play hijau).

## 📂 Struktur Folder Utama

- `app/src/main/java/com/example/appbook2/`: Berisi logika kode (Activities & Adapters).
- `app/src/main/res/layout/`: Berisi desain antarmuka (XML).
- `app/src/main/res/drawable/`: Berisi aset gambar dan ikon.

## 👤 Kontributor

- **Ilham** (Tatsumihatake)

---
*Dibuat dengan ❤️ menggunakan Kotlin.*
