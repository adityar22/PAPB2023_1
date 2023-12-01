<h1 align="center">
  ♻️ Simpala App
</h1>

## ❓ About 
Simpala App adalah sebuah aplikasi untuk pelaporan sampah yang dibangun menggunakan Kotlin. Aplikasi ini merupakan hasil dari _project-based_ pada mata kuliah Pengembangan Aplikasi Permainan Bergerak.

## 📁 Repositories
- Main: https://github.com/Capstone-A10-DTETI-2023
- API: https://github.com/saddansyah/papb-be (using Node.js)

## 🔍 Features
- Tampilan List Laporan (Room dan Retrofit API)
    - Data _dummy_ dari API (https://api-papb.saddansyah.space/api/reports) -> _caching_ ke Room Database
- Tampilan Detail Laporan
    - Integrasi aplikasi Google Maps untuk melihat lokasi pelaporan (Intent)
- Cari laporan berdasarkan judul
- Tambah laporan
- Tampilan List Berita (Retrofit API)
   - Integrasi sumber berita melalui peramban (Intent)
   - Data dari API (https://api-papb.saddansyah.space/api/news)
### Upcoming!
- Map View

## ⚙️ Technology Stack
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)

## ⌨ Commit Naming Convention
- `feat` is for adding a new feature
- `fix` is for fixing a bug
- `refactor` is for changing code for peformance or convenience purpose (e.g. readibility)
- `chore` is for everything else (writing documentation, formatting, adding tests, cleaning useless code etc.).  
<br/>After the category, there should be a ":" announcing the commit description. You could use (scope) to specify which scope are you working to.

### Example
- `feat: add post controller` <br/>
- `chore: update readme` <br/>
- `fix(auth): fix signin input now showing`

---
<p align="center">
  Copyright 2023 - PAPB Kelompok 1
</p>
