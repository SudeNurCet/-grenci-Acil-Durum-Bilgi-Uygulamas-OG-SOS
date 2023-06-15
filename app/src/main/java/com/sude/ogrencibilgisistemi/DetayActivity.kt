package com.sude.ogrencibilgisistemi

import Ogrenci
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        val ogrenci = intent.getSerializableExtra("ogrenci") as Ogrenci

        val resim = findViewById<ImageView>(R.id.resim)
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/obss-ec815.appspot.com/o/resimler%2f"+ogrenci.resim+"?alt=media&token=560a66a9-4d10-4312-9cb8-aab6fcc17eb2").into(resim)

        val adSoyad = findViewById<TextView>(R.id.adSoyad)
        adSoyad.text = "Ad Soyad: " + ogrenci.adSoyad

        val tc = findViewById<TextView>(R.id.tc)
        tc.text = "TC: " + ogrenci.tc

        val kanGrubu = findViewById<TextView>(R.id.kangrubu)
        kanGrubu.text = "Kan Grubu: " + ogrenci.kanGrubu

        val rahatsizlik = findViewById<TextView>(R.id.rahatsizliklar)
        rahatsizlik.text = "Geçirdiği Rahatsızlıklar: " +ogrenci.gecirdigiHastaliklar

        val ilac = findViewById<TextView>(R.id.ilaclar)
        ilac.text = "Kullandığı İlaçlar: " +ogrenci.kullandigiIlaclar

        val anneNo = findViewById<TextView>(R.id.anneNo)
        anneNo.text = "Anne No: " +ogrenci.anneNo

        val babaNo = findViewById<TextView>(R.id.babaNo)
        babaNo.text = "Baba No: " +ogrenci.babaNo

        val akrabaUnvanı1 = findViewById<TextView>(R.id.akrabaUnvanı1)
        akrabaUnvanı1.text = "1. Akraba Unvan: " +ogrenci.akrabaUnvani1

        val akrabaNo1 = findViewById<TextView>(R.id.akrabaNo1)
        akrabaNo1.text = "1. Akraba No: " +ogrenci.akrabaNo1

        val akrabaKonum1 = findViewById<TextView>(R.id.akrabaKonum1)
        akrabaKonum1.text = "1. Akraba Adres: " +ogrenci.akrabaKonum1

        val akrabaUnvanı2 = findViewById<TextView>(R.id.akrabaUnvanı2)
        akrabaUnvanı2.text = "2. Akraba Unvan: " +ogrenci.akrabaUnvani2

        val akrabaNo2 = findViewById<TextView>(R.id.akrabaNo2)
        akrabaNo2.text = "2. Akraba No: " +ogrenci.akrabaNo2

        val akrabaKonum2 = findViewById<TextView>(R.id.akrabaKonum2)
        akrabaKonum2.text = "2. Akraba Konum: " +ogrenci.akrabaKonum2

        val akrabaUnvanı3 = findViewById<TextView>(R.id.akrabaUnvanı3)
        akrabaUnvanı3.text = "3. Akraba Unvan: " +ogrenci.akrabaUnvani3

        val akrabaNo3 = findViewById<TextView>(R.id.akrabaNo3)
        akrabaNo3.text = "3. Akraba No: " +ogrenci.akrabaNo3

        val akrabaKonum3 = findViewById<TextView>(R.id.akrabaKonum3)
        akrabaKonum3.text = "3. Akraba Konum: " +ogrenci.akrabaKonum3


    }
}