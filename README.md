# Tutorial APAP
## Authors 

* Nadia Sabrina - 1906399606 - A

----

## Tutorial 2
1. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link
berikut setelah menjalankan program: http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut
dapat terjadi

Bisa dilihat bahwa url tersebut mengirimkan parameter berupa id, nama, alamat, dan no telepon
dari sebuah kebun safari. Seluruh parameter yang diberikan akan diproses oleh controller 
yaitu dengan assign setiap string ke valuenya masing-masing. Kemudian controller akan
membuat objek kebun safari baru dengan atribut yang didapatkan dari parameter. Setelah
pembuatan objek baru berhasil dilakukan, controller akan merender halaman html "add-kebun-safari"
sebagai respond. HTML tersebut berisikan ucapan yang menunjukkan objek berhasil ditambahkan.

2. Menurut kamu anotasi @Autowired pada class Controller tersebut
merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja
@Autowired tersebut dalam konteks service dan controller yang telah kamu buat

@Autowired merupakan implementasi dari konsep dependency injection. Pada
konteks service dan controller, @Autowired mempunyai fitur component-scan yang di mana
@Autowired akan melihat isi package yang kita sebutkan yang akan dilanjutkan dengan pencarian
class yang mengandung anotasi @controller, service, component. 
Setelah ditemukan, @Autowired akan men-inject seluruh dependency yang diperlukan class tersebut.

Dalam case ini, @Autowired yang dimasukkan ke kelas controller akan melakukan component-scan
ke kelas service. 

3. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link
berikut:
http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%
20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Halaman akan menunjukkan adanya error di karenakan pada method addKebunSafari, 
param request noTelepon diset menjadi required=true yang berarti wajib dikirimkan. 
Namun, url tersebut tidak mencantumkan no telepon yang menyebabkan error. 

4. Jika Papa APAP ingin melihat Kebun Safari dengan nama Papa APAP,
link apa yang harus diakses?

Link yang harus diakses adalah http://localhost:8080/kebun-safari?id=1 
karena Kebun Safari dengan nama Papa Apap mempunyai id=1 dan pencarian 
tersebut dapat dilakukan dengan menggunakan method getKebunSafariById dengan
syarat pemberian id di url harus dipenuhi. 

5. Pertanyaan 5: Tambahkan 1 contoh Kebun Safari lainnya sesukamu. Lalu cobalah
untuk mengakses http://localhost:8080/ , apa yang akan ditampilkan? Sertakan juga
bukti screenshotmu.

yang ditampilkan adalah daftar kebun safari yang telah dimasukkan dan atribut tiap
objek kebun safari. 

View All![View All](https://ibb.co/6t3fhNX)
View All![View All](https://ibb.co/vDh5pYL)


---
## Tutorial 1
### What I have learned today
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
Issue Tracker adalah sebuah fitur untuk membantu pengguna dalam mendaftarkan apa saja issue/masalah
yang terjadi selama proses development. Timeline yang tersedia juga akan membantu kita dalam menyimpan
informasi berupa apa saja issue yang pernah didaftarkan sebelumnya.

Masalah yang dapat diselesaikan diantaranya adalah bugs, ideas tracking, feedback tracking
dan semacamnya. 


2. Apa perbedaan dari git merge dan git merge --squash?
Git merge akan melakukan proses merging atau penyatuan perubahan file dari branch ke master
satu-persatu sesuai dengan tiap commit yang ada sedangkan git merge --squash akan melakukan
proses merging yang akan memproses seluruh commit sekaligus untuk disatukan ke branch
master. 

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?
- Memudahkan kolaborasi pengerjaan project development
- Memudahkan pelacakan dan penyimpanan seluruh dokumentasi dan isi dari project 

### Spring
4. Apa itu library & dependency?
Library adalah sebuah kumpulan kode yang sudah dirancang untuk memenuhi fungsionalitas 
tertentu dan dapat digunakan langsung oleh developer lain. 

Dependency adalah kumpulan library yang mana sebuah aplikasi bergantung pada kumpulan
library tersebut untuk menjalankan fungsionalitasnya. 

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
Sebuah tool yang membantu mengautomasikan dan memanage java project untuk dijalankan. 
Maven mampu menyederhanakan proses build project sehingga banyak orang yang menggunakannya. 
Alternatif Maven diantaranya adalah Gradle, CMake, GNU Make, and Meson.


6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?
Mengatur proses manajemen transaksi, pengaturan data ke database, pengembangan
fasilitas mailing

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?
@RequestParam digunakan untuk menerima query parameter pada method di Controller 
@PathVariable digunakan untuk mendapatkan sebuah value yang ter-embed di URL.

Jika suatu project menggunakan banyak path dan ingin menghindari conflict
antar path, @RequestParam dapat digunakan. Sebaliknya, jika path yang digunakan
pada proses pengembangan dapat dipastikan dihandle dengan baik, maka 
@PathVariable dapat digunakan. 

### What I did not understand
- [ ] Library Serializable
- [ ] Annotation GetMapping 
- [ ] Hal-hal lain yang bisa dilakukan dengan thymeleaf 
- [ ] Arti dari file file dan susunannya dalam folder IsPalindrome 






