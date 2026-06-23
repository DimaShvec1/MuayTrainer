package com.example.muaythaitrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muaythaitrainer.R
import com.example.muaythaitrainer.adapter.TechniqueAdapter
import com.example.muaythaitrainer.model.Technique
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TechniquesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TechniqueAdapter
    private lateinit var fabScrollTop: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_techniques, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_techniques)
        fabScrollTop = view.findViewById(R.id.fab_scroll_top)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val techniques = listOf(
            // ===== УДАРЫ РУКАМИ =====
            Technique(
                id = 1,
                name = "🥊 Джеб",
                description = "Быстрый прямой удар передней рукой. Это основа всей ударной техники в боксе и ММА. Джеб используется для разведки, контроля дистанции и подготовки следующих атак. Он должен быть быстрым и точным, а не сильным. Джеб задаёт темп всему бою и является самым частым ударом в единоборствах.",
                videoId = "uP6j3qQV3ww",
                category = "Удары руками",
                imageResId = R.drawable.jabq,
                detailImageResId = R.drawable.jab
            ),
            Technique(
                id = 2,
                name = "🥊 Панч (Punch)",
                description = "Мощный прямой удар задней рукой с полным разворотом корпуса и бедра. В отличие от джеба, панч наносится с весом тела и является основным нокаутирующим ударом в боксе. Он требует хорошей координации и часто следует за джебом в комбинации 'джеб-панч'.",
                videoId = "z5lRj9kQ1fk",
                category = "Удары руками",
                imageResId = R.drawable.punchq,
                detailImageResId = R.drawable.punch
            ),
            Technique(
                id = 3,
                name = "🥊 Хук",
                description = "Боковой удар согнутой в локте под 90° рукой. Хук наносится в корпус или голову сбоку и может пройти сквозь защиту противника. Это мощный короткий удар, требующий хорошей координации и вращения корпуса. Особенно эффективен на средней и ближней дистанции.",
                videoId = "pIuVfGz5ZSE",
                category = "Удары руками",
                imageResId = R.drawable.hookq,
                detailImageResId = R.drawable.hook
            ),
            Technique(
                id = 4,
                name = "🥊 Апперкот",
                description = "Удар снизу вверх по траектории дуги, наносимый в подбородок или корпус противника. Апперкот — это нокаутирующий удар, особенно эффективный в ближнем бою и в клинче. Он поднимает голову противника вверх и открывает его для следующих атак.",
                videoId = "jEucCb8dF2M",
                category = "Удары руками",
                imageResId = R.drawable.uppercutq,
                detailImageResId = R.drawable.uppercut
            ),
            Technique(
                id = 5,
                name = "🥊 Свинг",
                description = "Широкий боковой удар с замахом, наносимый с большой амплитудой и полным весом тела. Это очень мощный, но технически сложный удар, требующий хорошей координации и чувства равновесия. Свинг часто используется для шокирующего эффекта и может пробить даже плотную защиту.",
                videoId = "sVYdFfGz5ZS",
                category = "Удары руками",
                imageResId = R.drawable.swingq,
                detailImageResId = R.drawable.swing
            ),
            Technique(
                id = 6,
                name = "🥊 Бэкфист",
                description = "Удар тыльной стороной кулака — неожиданный и быстрый удар, часто используемый в ММА. Наносится с разворота или в прыжке. Бэкфист не является самым сильным ударом, но он отлично сбивает противника с толку и создаёт пространство для следующих атак.",
                videoId = "bFgK7lRcN2M",
                category = "Удары руками",
                imageResId = R.drawable.backfistq,
                detailImageResId = R.drawable.backfist
            ),

            // ===== УДАРЫ НОГАМИ =====
            Technique(
                id = 7,
                name = "🦵 Лоу-кик",
                description = "Удар ногой по бедру или голени соперника. Это база тайского бокса и один из самых эффективных ударов в единоборствах. Лоу-кик сбивает баланс, замедляет движения и наносит серьёзный урон ногам противника. С нескольких попаданий соперник начинает хромать и терять подвижность.",
                videoId = "DskqD5kVjJc",
                category = "Удары ногами",
                imageResId = R.drawable.lowkickq,
                detailImageResId = R.drawable.lowkick
            ),
            Technique(
                id = 8,
                name = "🦵 Мидл-кик",
                description = "Удар ногой в корпус, обычно в область печени или солнечного сплетения. Мидл-кик наносится подъёмом или голенью и считается одним из самых болезненных ударов. Попадание в печень может остановить противника одним ударом, поэтому этот приём высоко ценится в ММА и кикбоксинге.",
                videoId = "0-5IuDoKvWI",
                category = "Удары ногами",
                imageResId = R.drawable.midkickq,
                detailImageResId = R.drawable.midkick
            ),
            Technique(
                id = 9,
                name = "🦵 Хай-кик",
                description = "Удар ногой в голову, требующий отличной растяжки и координации. Хай-кик — один из самых зрелищных ударов в единоборствах. Если он достигает цели, это почти всегда нокаут. Идеальный хай-кик идёт по дуге сверху вниз и наносится голенью или подъёмом.",
                videoId = "hUZ4xOpLmB8",
                category = "Удары ногами",
                imageResId = R.drawable.highkickq,
                detailImageResId = R.drawable.highkick
            ),
            Technique(
                id = 10,
                name = "🦵 Бэк-кик",
                description = "Удар ногой с разворота назад, наносимый пяткой или подошвой. Бэк-кик — опасный и неожиданный удар, часто используемый в карате и кикбоксинге. Он требует отличной координации и чувства равновесия. При правильном исполнении этот удар может стать нокаутирующим.",
                videoId = "qZ5vW4rXcT8",
                category = "Удары ногами",
                imageResId = R.drawable.backkickq,
                detailImageResId = R.drawable.backkick
            ),
            Technique(
                id = 11,
                name = "🦵 Джампинг-кик",
                description = "Прыжковый удар ногой, выполняемый с разбега или с места. Джампинг-кик — очень зрелищный и мощный удар, требующий хорошей физической формы и координации. Часто используется в тхэквондо и кикбоксинге, но требует идеального тайминга для успешного применения в бою.",
                videoId = "mF9kQ3rXcT8",
                category = "Удары ногами",
                imageResId = R.drawable.jumpingkickq,
                detailImageResId = R.drawable.jumpingkick
            ),
            Technique(
                id = 12,
                name = "🦵 Кресент-кик",
                description = "Дугообразный удар ногой, выполняемый по траектории полумесяца. Наносится сбоку или сверху, часто в голову противника. Кресент-кик — это классика карате и тхэквондо. Он не самый сильный, но очень неожиданный и может попасть в голову с неудобного для защиты угла.",
                videoId = "nC8vB7mXcT8",
                category = "Удары ногами",
                imageResId = R.drawable.crescentkickq,
                detailImageResId = R.drawable.crescentkick
            )
        )

        adapter = TechniqueAdapter(techniques) { technique ->
            val fragment = TechniqueDetailFragment.newInstance(technique)
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter

        // КНОПКА "ВВЕРХ"
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    fabScrollTop.show()
                } else {
                    fabScrollTop.hide()
                }
            }
        })

        fabScrollTop.setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
            fabScrollTop.hide()
        }
    }
}