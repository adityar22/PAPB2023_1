<h1 align="center">
  ♻️ Simpala App
</h1>

## ❓ About 
Simpala App adalah sebuah aplikasi untuk pelaporan sampah yang dibangun menggunakan Kotlin. Aplikasi ini merupakan hasil dari _project-based_ pada mata kuliah Pengembangan Aplikasi Permainan Bergerak.

## 📁 Repositories
- Main: https://github.com/adityar22/PAPB2023_1
- API: https://github.com/saddansyah/papb-be (using Node.js)

## 🔍 Features
- Tampilan List Laporan (Room dan Retrofit API)
    - Data _dummy_ dari API (https://api-papb.saddansyah.space/api/reports) -> _caching_ ke Room Database
- Tampilan Detail Laporan
    - Integrasi aplikasi Google Maps untuk melihat lokasi pelaporan (Intent)
- Cari laporan berdasarkan judul
- Tambah laporan
- Tampilan list dan detail Berita (Retrofit API)
   - Integrasi sumber berita melalui peramban (Intent)
   - Data dari API (https://api-papb.saddansyah.space/api/news)
   - Notifikasi ketika terdapat berita baru
### Upcoming!
- Authentication + Authorization
- Map View
- Evidence Rate (membutuhkan auth)

## 📱 Preview
![image](https://github.com/adityar22/PAPB2023_1/assets/73093118/8a46106e-3db1-41bf-a5b9-4524e5dc1fc9)
![image](https://github.com/adityar22/PAPB2023_1/assets/73093118/9c532b74-8d19-4068-bac0-ae89f4dff3b4)
![image](https://github.com/adityar22/PAPB2023_1/assets/73093118/0b185ed1-7b04-43a5-93bb-18de7a2196ed)
![image](https://github.com/adityar22/PAPB2023_1/assets/73093118/271c8f2e-51c4-48fb-8dc2-c27b2868e862)
![image](https://github.com/adityar22/PAPB2023_1/assets/73093118/83a9de59-c870-45c9-91c8-ecdae4c908a9)
![image](https://github.com/adityar22/PAPB2023_1/assets/73093118/03ac502c-f8c5-4f82-8649-7092919e874a)


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
