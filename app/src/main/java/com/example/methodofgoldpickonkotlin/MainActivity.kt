package com.example.methodofgoldpickonkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.methodofgoldpickonkotlin.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var x1: Double = 0.0
    private var y1: Double = 0.0
    private var t1 = 0.0
    private var t2 = 0.0
    private var goldenSection = GoldenSection()
    private var g1: Double = 0.0
    private var g2: Double = 0.0
    private var j1: Double = 0.0
    private var j2: Double = 0.0
    private lateinit var functionMath: String

    //   private val entries = ArrayList<Entry>() swe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        sss()
    }

    /*
        private fun swe() {
            // Массив координат точек линии

            entries.add(Entry(g1.toFloat(), j1.toFloat()))
            entries.add(Entry(g2.toFloat(), j2.toFloat()))
            entries.add(Entry(t1.toFloat(), t2.toFloat()))

            /*Toast.makeText(this, j1.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, j2.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, g1.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, g2.toString(), Toast.LENGTH_SHORT).show()*/
            Toast.makeText(this, t1.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, t2.toString(), Toast.LENGTH_SHORT).show()

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
    */
    @SuppressLint("SetTextI18n")
    private fun sss() {
        binding.btnX.setOnClickListener {
            if (binding.etCount.text?.isEmpty() == true) {
                Toast.makeText(this, "Заполните текст", Toast.LENGTH_SHORT).show()
            } else x1 = binding.etCount.text.toString().toInt().toDouble()
        }
        binding.btnY.setOnClickListener {
            if (binding.etCount.text?.isEmpty() == true) {
                Toast.makeText(this, "Заполните текст", Toast.LENGTH_SHORT).show()
            } else y1 = binding.etCount.text.toString().toInt().toDouble()
        }
        binding.btnEnterFunction.setOnClickListener {
            if (binding.etFunction.text?.isEmpty() == true) {
                Toast.makeText(this, "Заполните текст", Toast.LENGTH_SHORT).show()
            } else {
                functionMath = binding.etFunction.text.toString()
                Toast.makeText(this@MainActivity, eval(functionMath).toString(), Toast.LENGTH_LONG)
                    .show()
            }
        }
        binding.btnCoordinate.setOnClickListener {
            t1 = (goldenSection.findMin(x1, y1, 0.001)) //Минимум
            t2 = (goldenSection.findMax(x1, y1, 0.001)) //Максимум
            binding.funMin.text = "$t1 Минимум нашей функции".trimMargin()
            binding.funMax.text = "$t2 Максимум нашей функции".trimMargin()
        }
    }

    inner class GoldenSection {
        private val phi = (1 + sqrt(5.0)) / 2 //Постоянная фи
        fun funny(x: Int) {}
        fun f(x: Double): Double {
            //return (x + 1).pow(3.0) + 5 * x.pow(2.0) //Наша функция
            //return (2 * x.pow(2.0) - 1) / (sqrt(x.pow(2.0)- 2))
            return 0.0
        }

        fun findMin(a: Double, b: Double, e: Double): Double { //Нахождение минимума
            var athis = a //Возвращяется в виде x1
            var bthis = b //Возвращяется в виде y1
            while (true) {
                this@MainActivity.g1 = bthis - (bthis - athis) / phi
                this@MainActivity.g2 = athis + (bthis - athis) / phi
                if (f(g1) >= f(g2)) {
                }//Если q1 больше или равен q2 выполняем athis равен q1
                if (f(g1) >= f(g2)) {
                    athis = g1
                } else { //В другом случае bthis равен q2
                    bthis = g2
                }
                if (abs(bthis - athis) < e) break
            }
            return (athis + bthis) / 2
        }

        fun findMax(a: Double, b: Double, e: Double): Double { //нахождение максимума
            var athis = a //Возвращяется в виде x2
            var bthis = b //Возвращяется в виде y2
            while (true) {
                this@MainActivity.j1 = bthis - (bthis - athis) / phi
                this@MainActivity.j2 = athis + (bthis - athis) / phi
                if (f(j1) <= f(j2)) {
                    athis = j1
                } else {
                    bthis = j2
                }
                if (abs(bthis - athis) < e) break
            }
            return (athis + bthis) / 2
        }
    }

    private fun eval(str: String): Double {
        val phi = (1 + sqrt(5.0)) / 2 //Постоянная фи
        fun funny(x: Int) {}
        fun f(x: Double): Double {
            //return (x + 1).pow(3.0) + 5 * x.pow(2.0) //Наша функция
            //return (2 * x.pow(2.0) - 1) / (sqrt(x.pow(2.0)- 2))
            return 0.0
        }

        fun findMin(a: Double, b: Double, e: Double): Double { //Нахождение минимума
            var athis = a //Возвращяется в виде x1
            var bthis = b //Возвращяется в виде y1
            while (true) {
                this@MainActivity.g1 = bthis - (bthis - athis) / phi
                this@MainActivity.g2 = athis + (bthis - athis) / phi
                if (f(g1) >= f(g2)) {
                }//Если q1 больше или равен q2 выполняем athis равен q1
                if (f(g1) >= f(g2)) {
                    athis = g1
                } else { //В другом случае bthis равен q2
                    bthis = g2
                }
                if (abs(bthis - athis) < e) break
            }
            return (athis + bthis) / 2
        }

        fun findMax(a: Double, b: Double, e: Double): Double { //нахождение максимума
            var athis = a //Возвращяется в виде x2
            var bthis = b //Возвращяется в виде y2
            while (true) {
                this@MainActivity.j1 = bthis - (bthis - athis) / phi
                this@MainActivity.j2 = athis + (bthis - athis) / phi
                if (f(j1) <= f(j2)) {
                    athis = j1
                } else {
                    bthis = j2
                }
                if (abs(bthis - athis) < e) break
            }
            return (athis + bthis) / 2
        }
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) {
                    str[pos].code
                } else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.code)) x *= parseFactor() // multiplication
                    else if (eat('/'.code)) x /= parseFactor() // division
                    else if (eat('^'.code)) {
                        x = x.pow(parseFactor())
                    } else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return +parseFactor() // unary plus
                if (eat('-'.code)) return -parseFactor() // unary minus
                var x: Double
                val startPos = pos
                if (eat('('.code)) { // parentheses
                    x = parseExpression()
                    if (!eat(')'.code)) throw RuntimeException("Missing ')'")
                }
                else if (ch == 'x'.code) {
                    goldenSection.funny(ch)
                    x = str.substring(startPos, pos).toDouble()
                }
                else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) { // numbers
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()

                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.code && ch <= 'z'.code) { // functions
                    while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    if (eat('('.code)) {
                        x = parseExpression()
                        if (!eat(')'.code)) throw RuntimeException("Missing ')' after argument to $func")
                    } else {
                        x = parseFactor()
                    }
                    x = when (func) {
                        "sqrt" -> sqrt(x)
                        "sin" -> sin(Math.toRadians(x))
                        "cos" -> cos(Math.toRadians(x))
                        "tan" -> tan(Math.toRadians(x))
                        else -> throw RuntimeException("Unknown function: $func")
                    }
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                return x
            }
        }.parse()
    }
}