import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.sude.ogrencibilgisistemi.DetayActivity
import com.sude.ogrencibilgisistemi.R
import java.io.Serializable

class OgrenciAdapter(private var ogrenciListesi: ArrayList<Ogrenci>) : RecyclerView.Adapter<OgrenciAdapter.OgrenciViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OgrenciViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ogrenci_cardview, parent, false)
        return OgrenciViewHolder(view)
    }

    override fun onBindViewHolder(holder: OgrenciViewHolder, position: Int) {
        val ogrenci = ogrenciListesi[position]
        holder.adSoyadTextView.text = "Öğrenci Adı: "+ ogrenci.adSoyad
        holder.tcTextView.text = "Tc: " + ogrenci.tc
        holder.ogrenciKanTextView.text = "Kan Grubu: " + ogrenci.kanGrubu

        Glide.with(holder.ogrenciResimImageView).load("https://firebasestorage.googleapis.com/v0/b/obss-ec815.appspot.com/o/resimler%2f"+ogrenci.resim+"?alt=media&token=560a66a9-4d10-4312-9cb8-aab6fcc17eb2").into(holder.ogrenciResimImageView)
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, DetayActivity::class.java)
            intent.putExtra("ogrenci", ogrenci)
            holder.itemView.context.startActivity(intent)
        });
    }
    fun setOgrenciListesi(ogrenciListesi: ArrayList<Ogrenci>) {
        this.ogrenciListesi = ogrenciListesi
    }

    fun getOgrenciListesi(): ArrayList<Ogrenci> {
        return ogrenciListesi
    }

    override fun getItemCount(): Int {
        return ogrenciListesi.size
    }

    inner class OgrenciViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val adSoyadTextView: TextView = itemView.findViewById(R.id.ogrenciAdiTxt)
        val tcTextView: TextView = itemView.findViewById(R.id.ogrenciTctxt)
        val ogrenciKanTextView: TextView = itemView.findViewById(R.id.ogrenciKantxt)
        val ogrenciResimImageView: ImageView = itemView.findViewById(R.id.ogrenciFoto)
    }
}


class OgrenciGetir{
    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("ogrenciler")

    fun ogrenciVerileriniCek(onOgrenciListesiAlindi: (ArrayList<Ogrenci>) -> Unit) {
        val ogrenciListesi = ArrayList<Ogrenci>()

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ogrenciListesi.clear()

                for (ogrenciSnapshot in snapshot.children) {
                    val adSoyad = ogrenciSnapshot.child("adSoyad").getValue(String::class.java)
                    val kanGrubu = ogrenciSnapshot.child("kanGrubu").getValue(String::class.java)
                    val tc = ogrenciSnapshot.child("tc").getValue(String::class.java)
                    val akrabaKonum1 = ogrenciSnapshot.child("akrabaKonum1").getValue(String::class.java)
                    val akrabaKonum2 = ogrenciSnapshot.child("akrabaKonum2").getValue(String::class.java)
                    val akrabaKonum3 = ogrenciSnapshot.child("akrabaKonum3").getValue(String::class.java)
                    val akrabaNo1 = ogrenciSnapshot.child("akrabaNo1").getValue(String::class.java)
                    val akrabaNo2 = ogrenciSnapshot.child("akrabaNo2").getValue(String::class.java)
                    val akrabaNo3 = ogrenciSnapshot.child("akrabaNo3").getValue(String::class.java)
                    val akrabaUnvani1 = ogrenciSnapshot.child("akrabaUnvanı1").getValue(String::class.java)
                    val akrabaUnvani2 = ogrenciSnapshot.child("akrabaUnvanı2").getValue(String::class.java)
                    val akrabaUnvani3 = ogrenciSnapshot.child("akrabaUnvanı3").getValue(String::class.java)
                    val anneNo = ogrenciSnapshot.child("anneNo").getValue(String::class.java)
                    val babaNo = ogrenciSnapshot.child("babaNo").getValue(String::class.java)
                    val gecirdigiHastaliklar = ogrenciSnapshot.child("gecirdigiHastaliklar").getValue(String::class.java)
                    val kullandigiIlaclar = ogrenciSnapshot.child("kullandigiIlaclar").getValue(String::class.java)
                    val resim = ogrenciSnapshot.child("resim").getValue(String::class.java)

                    if (adSoyad != null && kanGrubu != null && tc != null && akrabaKonum1 != null && akrabaKonum2 != null && akrabaKonum3 != null && akrabaNo1 != null && akrabaNo2 != null && akrabaNo3 != null && akrabaUnvani1 != null && akrabaUnvani2 != null && akrabaUnvani3 != null && anneNo != null && babaNo != null && gecirdigiHastaliklar != null && kullandigiIlaclar != null && resim != null) {
                        val ogrenci = Ogrenci(adSoyad, kanGrubu, tc, akrabaKonum1, akrabaKonum2, akrabaKonum3, akrabaNo1, akrabaNo2, akrabaNo3, akrabaUnvani1, akrabaUnvani2, akrabaUnvani3, anneNo, babaNo, gecirdigiHastaliklar, kullandigiIlaclar, resim)
                        ogrenciListesi.add(ogrenci)
                    }
                }

                onOgrenciListesiAlindi(ogrenciListesi)
            }

            override fun onCancelled(error: DatabaseError) {
                // Hata durumunda yapılacak işlemler
            }
        })
    }
}

data class Ogrenci(val adSoyad: String, val kanGrubu: String, val tc: String, val akrabaKonum1: String, val akrabaKonum2: String, val akrabaKonum3: String, val akrabaNo1: String, val akrabaNo2: String, val akrabaNo3: String, val akrabaUnvani1: String, val akrabaUnvani2: String, val akrabaUnvani3: String, val anneNo: String, val babaNo: String, val gecirdigiHastaliklar: String, val kullandigiIlaclar: String, val resim: String) :
    Serializable