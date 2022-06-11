package com.example.methodofgoldpickonkotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.methodofgoldpickonkotlin.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var x1: Double = 0.0
    private var y1: Double = 0.0
    private var x2: Double = 0.0
    private var y2: Double = 0.0
    private var t1 = 0.0
    private var t2 = 0.0
    private var goldenSection = GoldenSection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        sss()
        binding.btnChart.setOnClickListener {
            swe()
        }
    }

    private fun swe() {
        // Массив координат точек линии
        val entries = ArrayList<Entry>()
        entries.add(Entry(t1.toFloat(), t2.toFloat()))
        entries.add(Entry(x1.toFloat(), y2.toFloat()))
        entries.add(Entry(x2.toFloat(), y2.toFloat()))

        // На основании массива точек создаем вторую линию с названием
        val datasetSecond = LineDataSet(entries, "График золотого сечения")
        // График будет зеленого цвета
        datasetSecond.color = Color.GREEN
        // График будет плавным
        datasetSecond.mode = LineDataSet.Mode.CUBIC_BEZIER
        // Линии графиков соберем в один массив
        val dataSets: ArrayList<ILineDataSet?> = ArrayList()
        dataSets.add(datasetSecond)
        // Создадим переменную  данных для графика
        val data = LineData(dataSets)
        // Передадим данные для графика в сам график
        binding.chart.data = data
        // График будет анимироваться 0.5 секунды
        binding.chart.animateY(500)
        binding.chart.invalidate()
    }

    @SuppressLint("SetTextI18n")
    private fun sss() {
        binding.button.setOnClickListener {
            if (binding.editTextText.text?.isEmpty() == true) {
                Toast.makeText(this, "Заолните текст", Toast.LENGTH_SHORT).show()
            } else x1 = binding.editTextText.text.toString().toInt().toDouble()
        }
        binding.button2.setOnClickListener {
            if (binding.editTextText.text?.isEmpty() == true) {
                Toast.makeText(this, "Заолните текст", Toast.LENGTH_SHORT).show()
            } else y1 = binding.editTextText.text.toString().toInt().toDouble()
        }
        binding.button5.setOnClickListener {
            if (binding.editTextText.text?.isEmpty() == true) {
                Toast.makeText(this, "Заолните текст", Toast.LENGTH_SHORT).show()
            } else x2 = binding.editTextText.text.toString().toInt().toDouble()
        }
        binding.button4.setOnClickListener {
            if (binding.editTextText.text?.isEmpty() == true) {
                Toast.makeText(this, "Заолните текст", Toast.LENGTH_SHORT).show()
            } else y2 = binding.editTextText.text.toString().toInt().toDouble()
        }
        if (x1 != null && x2 != null && y1 != null && y2 != null) {
            binding.button3.setOnClickListener {
                t1 = (goldenSection.findMin(x1, y1, 0.001)) //Минимум
                t2 = (goldenSection.findMax(x2, y2, 0.001)) //Максимум
                binding.funMin.text = "$t1 Минимум нашей функции".trimMargin()
                binding.funMax.text = "$t2 Максимум нашей функции".trimMargin()
            }
        }
    }

    class GoldenSection {
        private val phi = (1 + sqrt(5.0)) / 2
        private fun f(x: Double): Double {
            return (x + 1).pow(3.0) + 5 * x.pow(2.0)
        }

        fun findMin(a: Double, b: Double, e: Double): Double {
            var athis = a
            var bthis = b
            var x1: Double
            var x2: Double
            while (true) {
                x1 = bthis - (bthis - athis) / phi
                x2 = athis + (bthis - athis) / phi
                if (f(x1) >= f(x2)) athis = x1 else bthis = x2
                if (abs(bthis - athis) < e) break
            }
            return (athis + bthis) / 2
        }

        fun findMax(a: Double, b: Double, e: Double): Double {
            var athis = a
            var bthis = b
            var x1: Double
            var x2: Double
            while (true) {
                x1 = bthis - (bthis - athis) / phi
                x2 = athis + (bthis - athis) / phi
                if (f(x1) <= f(x2)) athis = x1 else bthis = x2
                if (abs(bthis - athis) < e) break
            }
            return (athis + bthis) / 2
        }
    }
}