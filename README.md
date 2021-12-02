# Tutorial APAP
## Authors 

* Nadia Sabrina - 1906399606 - A


----
## Tutorial 8 

1. Ceritakan langkah - langkah yang kalian lakukan untuk solve LATIHAN no.1, dan mengapa kalian melakukan langkah - langkah tersebut?

Pada file itemlist/index.js, terdapat function handleSubmitItem yang mengelola proses submit item dengan tambahan kode berikut:
this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })

Kode di atas berarti saat ingin menambahkan item, state dari setiap atribut akan di-set menjadi default value yaitu string kosong dan 0 sehingga setiap kali menambahkan tidak terdapat value lain yang ada pada form. 

2.Jelaskan fungsi dari async dan await!
Async dan Await adalah sebuah cara yang dapat digunakan untuk mengatasi masalah pada proses asynchronous. Await hanya dapat digunakan pada function yang mengimplementasikan async. Kembalian dari suatu function async harus berbentuk promise. Setiap baris yang menggunakan await akan menunda eksekusi sampai proses asynchronous selesai. 


3.Masukkan jawaban dari Screenshot yang diperintahkan di halaman 9 pada Component Lifecycle pada pertanyaan ini.
https://ibb.co/GP9tn8M
https://ibb.co/JjtzX1L
https://ibb.co/NV2pG2r
https://ibb.co/NmhPvP8
https://ibb.co/JjtzX1L
https://ibb.co/qDZHryN
https://ibb.co/b7DBZr3

4. Jelaskan fungsi dari componentDidMount, shouldComponentUpdate,
componentDidUpdate, componentWillReceiveProps, componentWillUnmount

-ComponentDidMount : 
Merupakan component lifecycle yang dapat dimanfaatkan untuk pengambilan data dan juga dapat digunakan sebagai penanda tahap akhir dari mounting lifecycle. Data yang diambil dapat berasal dari external atau API lainnya. Fungsi ini dipanggil saat proses loading telah selesai dilakukan pada HTML yang dirender. Digunakan pada use case pengambilan data API yang akan dirender. 

-shouldComponentUpdate :
Component yang umum dimanfaatkan pada proses untuk menampilkan perubahan pada component (bisa ditampilkan atau tidak) dan memiliki output boolean. Bernilai true jika perubahan ditampilkan dan false jika tidak ditampilkan. Fungsi akan dipanggil pada saat state atau props mengalami perubahan atau update. Digunakan pada use case pengubahan suatu komponen dan ingin melakukan proses rendering ulang. 

-componentDidUpdate : 
Umumnya dimanfaatkan untuk berinteraksi dengan environment selain React. Fungsi dijalankan ketika suatu instance dari object mengalami update. Digunakan pada use case updating component. 

-componentWillReceiveProps : 
Component yang dimanfaatkan untuk melakukan perubahan state berdasarkan current props dan new props. Fungsi dipanggil ketika terjadi update pada props. Digunakan pada use case update state. 

-componentWillUnmount : 
Component yang dimanfaatkan untuk melakukan action seperti membatalkan network request atau pembersihan timer yang invalid. Fungsi dipanggil ketika ada suatu component yang terhapus pada DOM. Digunakan pada use case penghapusan interval waktu running function. 


----
## Tutorial 7 
1. Jelaskan apa yang Anda lakukan di latihan dalam satu paragraf per-soal. Berikan screenshot
sebagai ilustrasi dari apa yang Anda jelaskan

No1 : Class-based 
https://ibb.co/XSmfbPs
https://ibb.co/dtWC04M

Pada no 1, saya menyimpan daftar list item yang ada pada cart ke dalam variabel remItems kemudian
saya membuat variabel newItem untuk merujuk ke sebuah item yang akan dihapus dari cart. Pada 
variabel targetInd saya akan menyimpan id dari item yang akan dihapus sehingga jika targetInd 
tidak kosong atau 0, balance akan ditambahkan dengan price dari item yang akan dihapus lalu status 
item tersebut menjadi di luar cart (inCart = false), item dikeluarkan dari array remItems dan item 
dikembalikan ke list shop item, serta cartItem akan digantikan menjadi list remItems. Saya juga 
menambahkan sebuah event jika icon delete dihapus yang mana akan memanggil function penghapusan 
item dari cart. 

No 2 : Class-based
https://ibb.co/QYv59G1

Pada handleAddItem, saya mengubah balance dengan mengubah state balance menjadi balance yang sudah
dikurangi dengan harga item yang ditambahkan ke cart dengan syarat balance awal melebihi atau sama
dengan price item yang akan dimasukkan ke cart. Pada handleDeleteItemFromCart, saya juga mengubah
state dari balance yang mana balance akan ditambah dengan harga dari item yang dihapus dari 
daftar cartItem. 

No 3 : Class-based
https://ibb.co/QYv59G1

Pada handleAddItem, akan terjadi proses pengecekan balance sebelum item dimasukkan ke dalam cart. 
Jika balance kurang dari harga item yang ingin dimasukkan, maka item tersebut tidak dapat dimasukkan
ke dalam cart dan akan muncul alert yang memberi informasi bahwa balance tidak cukup. 



2. Menurut pemahaman kamu selama pengerjaan tutorial ini, apa perbedaan antara state dan
props?

Props berasal dari kata property dan secara konsep mirip dengan atribut pada tag HTML. 
Props bersifat read-only sehingga umumnya digunakan untuk berkomunikasi antar component. 

State merupakan data yang tersimpan pada suatu component. State bersifat private pada
component yang memilikinya. Jika props valuenya didapatkan dari component lain, 
state justru dapat mengubah dan menyimpan data yang dimilikinya sendiri. 

Dengan demikian, props cocok digunakan untuk melakukan komunikasi antar component
sedangkan state lebih cocok digunakan untuk mengolah data yang bersifat private. 


3. Menurut kamu, apakah sebaiknya kita menggunakan component (e.g. List, Item) dalam
React? sebutkan alasannya.

Iya, karena component bersifat independent dan reusable. Mereka dapat berdiri sendiri
seperti kepingan lego namun juga bisa disusun bersamaan untuk tujuan tertentu. 
Component juga reusable yang berarti kita dapat menggunakan component
berkali kali di sebuah application. Dengan demikian, component bersifat sangat
fleksibel dan dapat memudahkan proses development aplikasi. 

4. Apa perbedaan class component dan functional component?

Class component dapat menggunakan state dan props sedangkan functional component 
hanya dapat menggunakan props yang menyebabkan functional component disebut juga 
dengan stateless component atau UI component karena tanpa state, component
tersebut hanya berfokus untuk menghandle sebuah tampilan antarmuka. 

5. Dalam react, apakah perbedaan component dan element?

Element adalah sebuah objek paling sederhana di react yang menggambarkan hal yang kita
ingin munculkan di halaman. Elemen dapat didefinisikan sebagai representasi virtual
dari DOM. Di sisi lain, komponen mirip dengan function. Jika function menerima input
berupa parameter atau argumen dan mereturn suatu nilai, komponen menerima input
yang biasa disebut dengan props dan hasil returnnya berupa react element. 

----
## Tutorial 6 
1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode
   yang telah anda buat) konsep tersebut diimplementasi?
    Otentikasi - Proses identifikasi pengguna
    Otorisasi - Proses pemberian wewenang kepada pengguna mengenai hak atau tugas tertentu
    
    Proses otorisasi terjadi di dalam code yang ada pada file WebSecurityConfig dimana
   .antMatchers("/user/**").hasAuthority("Admin") --> mempunyai arti semua url yang diawali /user/ 
    hanya dapat diakses oleh user dengan role "Admin". 

    Proses otentikasi terjadi di dalam method public void configAuthentication(AuthenticationManagerBuilder auth)
    dimana objek user dengan password berbentuk hash akan di-encode untuk kemudian masuk ke proses
    autentikasi dengan AuthenticationManagerBuilder 

2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya.
    BCryptPasswordEncoder adalah sebuah encoder dan validator password yang ada pada modul spring boot security.
    BCryptPasswordEncoder menggunakan algortima Bcrypt yang merupakan one-way encryption algorithm. Proses encoding
    dimulai dengan penyimpanan data password berbentuk kode abstrak ke dalam database kemudian saat user login,
    BCryptPasswordEncoder akan memanggil sebuah fungsi untuk encode kode abstrak password pengguna dan 
    memverifikasi password dengan membandingkannya dengan input password pengguna.

3. Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa
   demikian?
    Hashing lebih appropriate untuk digunakan dalam penyimpanan password. 
    Hashing merupakan one-way function yang berarti ketika sudah mengubah bentuk password ke dalam hash,
    maka akan sulit untuk mendapatkan password semula. Di sisi lain, encryption adalah two-way function
    sehingga proses transformasi bisa dilakukan dua arah dan memudahkan kita untuk mendapatkan password
    dalam bentuk semula.
    
4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!
   UUID mempunyai kepanjangan universally unique identifier yang mana adalah label dengan ukuran 128-bit 
    yang digunakan untuk mengidentifikasi informasi dalam sistem komputer. UUID dibuat secara acak namun
    dijamin tidak ada yang sama di antara karakternya sehingga dapat digunakan sebagai primary key dalam 
    sebuah model. 

5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut
   padahal kita sudah memiliki class UserRoleServiceImpl.java?
    UserDetailsServiceImpl berperan pada proses otentikasi pada saat tahapan penyocokan username dan password
    yang diambil dari form login dengan data pengguna yang ada di database sedangkan RoleServiceImpl digunakan
    untuk mendapatkan daftar role dan otoritas atau wewenang dari tiap role. 

----
## Tutorial 5 

1. Apa itu Postman? Apa kegunaannya?
postman adalah sebuah aplikasi atau ekstensi yang berfungsi sebagai REST client 
dan digunakan dalam pengujian website, seperti testing rest, testing API, dll.

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty
Anotasi @JsonIgnoreProperties digunakan pada level class untuk menandakan 
property atau kumpulan property yang perlu diabaikan. 
Anotasi @JsonProperty berfungsi untuk memetakan nama property yang diberikan
oleh JSON terhadap property terkait di kelas java. 

3. Apa kegunaan atribut WebClient?
WebClient dapat dimanfaatkan untuk melakukan HTTP request ke REST service 
yang ada di luar aplikasi. 

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?
ResponseEntity merepresentasikan seluruh HTTP response yang terdiri dari status code, 
headers, dan body agar kita dapat mengkonfigurasikan HTTP response. 

BindingResult adalah sebuah interface menentukan bagaimana objek yang menyimpan hasil 
validasi harus menyimpan dan mengambil hasil validasi seperti errors, percobaan 
bind yang disallowed fields, dll. 


----
## Tutorial 4
1. Jelaskan perbedaan th:include dan th:replace!
th:include akan memasukkan konten dari sebuah fragment ke dalam parent tagnya, contoh parent tag: <div>
th:replace akan menggantikan parent tag yang dimiliki menjadi konten dari fragmen 

2. Jelaskan apa fungsi dari th:object!
th:object digunakan untuk menspesifikasikan sebuah objek yang mana atribut dari objek tersebut
akan dikirim ke dalam tag yang menyertainya. 

3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?
${...} digunakan untuk mendeklarasikan sebuah variable 
*{...} juga dapat digunakan untuk mendeklarasikan sebuah variable namun expression tersebut 
hanya akan dieksekusi pada object yang sebelumnya telah dipilih

----
## Tutorial 3

1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model
(@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)

@AllArgsConstructor : membuat constructor dengan dengan 1 argument pada setiap field pada class yang memiliki anotasi\
@NoArgsConstructor : membuat constructor tanpa argument pada class yang memiliki anotasi tersebut\
@Setter : membuat method setter pada setiap field yang dimiliki class\
@Getter : membuat method getter pada setiap field yang dimiliki class\
@Entity : class yang mempunyai anotasi tersebut berarti dapat dipetakan ke sebuah table\ 
@Table  : anotasi yang dapat diguanakan untuk menspesifikasikan detail dari table yang akan diimplementasikan ke entity\

2. Pada class CabangDB, terdapat method findByNoCabang, apakah kegunaan dari method
    tersebut?
    
    findByNoCabang melakukan filtering pencarian objek CabangModel berdasarkan atribut noCabangnya 
    
3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn

    @JoinTable menyimpan id dari kedua table pada table yang terpisah \
    @JoinColumn menyimpan id dari entity lain di column yang baru pada table yang sama\
    

4. Pada class PegawaiModel, digunakan anotasi @JoinColumn pada atribut cabang, apa
    kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa
    perbedaan nullable dan penggunaan anotasi @NotNull?

    name : nama dari column tersebut 
    referencedColumnName : nama column yang direferensikan column yang mempunyai anotasi @JoinColumn 
    nullable : bisa/tidaknya nilai null dimasukkan ke dalam column, true jika nilai bisa null dan false jika tidak bisa
    
    @JoinColumn(nullable = false) : JPA specification, semua dependency yang dibutuhkan sudah ditambahkan, hanya
    bekerja jika Hibernate generate the column dan mengecek null constraintnya. 

    @NotNull : BeanValidation specification, dependency masih harus ditambahkan, validation bekerja pada Java
    Application pada saat tahap pre-update. 
    
   
5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER

    Jika terdapat 2 entitas yang berhubungan, misalnya dalam case ini cabang yang memiliki pegawai,
    FetchType.EAGER akan memuat objek pegawai bersamaan dengan dimuatnya objek cabang sedangkan 
    FetchType.LAZY hanya akan memuat objek pegawai hanya jika method getPegawai() dipanggil. 

   

----

## Tutorial 2
1. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link
berikut setelah menjalankan program: 
http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx 
Apa yang terjadi? Jelaskan mengapa hal tersebut
dapat terjadi?

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

link Screenshots : 
https://ibb.co/6t3fhNX
https://ibb.co/vDh5pYL


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






