IQ Puzzler Pro Solver
Penjelasan Singkat
Program ini adalah sebuah solver untuk permainan IQ Puzzler Pro menggunakan algoritma Brute Force. Tujuannya adalah untuk mengisi papan dengan potongan-potongan puzzle yang diberikan tanpa ada tumpang tindih atau ruang kosong. Program ini ditulis dalam bahasa Java dan menggunakan GUI berbasis Swing untuk menampilkan papan dan solusi.

Requirement Program
Berikut adalah requirement untuk menjalankan program ini:

Java Development Kit (JDK):

Pastikan JDK sudah terinstal di komputer Anda. Anda dapat mengeceknya dengan menjalankan perintah berikut di terminal:

bash
Copy
java -version
Jika JDK belum terinstal, unduh dan instal dari situs resmi Oracle.

Visual Studio Code (VSCode):

Program ini dapat dibuka dan dijalankan menggunakan VSCode. Pastikan VSCode sudah terinstal di komputer Anda.

Cara Mengkompilasi Program
Buka folder proyek di VSCode.

Buka terminal di VSCode (Ctrl + `).

Jalankan perintah berikut untuk mengkompilasi semua file Java:

bash
Copy
javac -d bin src/backend/*.java src/frontend/*.java src/Main.java
Perintah ini akan mengkompilasi semua file Java dan menyimpan file .class di folder bin.

Cara Menjalankan Program
Setelah program dikompilasi, jalankan program dengan perintah berikut di terminal:

bash
Copy
java -cp bin src.Main
Program akan membuka GUI untuk memuat file input dan menyelesaikan puzzle.

Menggunakan GUI:

Klik tombol "Load Test Case" untuk memilih file input (contoh: test.txt).

Setelah file input dimuat, klik tombol "Solve" untuk menjalankan solver.

Solusi akan ditampilkan di area teks, dan papan akan diperbarui secara visual.

Struktur Direktori
Berikut adalah struktur direktori proyek ini:

Copy
C:.
│   README.md
│
├───bin
│   ├───backend
│   │       Board.class
│   │       Piece.class
│   │       Solver.class
│   │
│   ├───frontend
│   │       BoardPanel.class
│   │       GUI.class
│   │
│   └───src
│           Main.class
│
├───src
│   │   Main.java
│   │
│   ├───backend
│   │       Board.java
│   │       Piece.java
│   │       Solver.java
│   │
│   └───frontend
│           BoardPanel.java
│           GUI.java
│
└───test
        test.txt (untuk test case)
Author / Identitas Pembuat
Program ini dibuat oleh:
Lukas Raja Agripa
13523158
K3 
Institut Teknologi Bandung

Catatan
Jika Anda menemukan masalah atau memiliki pertanyaan, silakan buka issue di repositori ini atau hubungi pembuat melalui email: lukasraja72@gmail.com.

