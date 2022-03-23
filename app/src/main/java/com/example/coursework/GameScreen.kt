package com.example.coursework

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer


class GameScreen : AppCompatActivity() {
    companion object{                                                       //Created global variables
        var leftAnswer = 0
        var rightAnswer = 0
        val leftNumList = mutableListOf<String>()
        val rightNumList = mutableListOf<String>()
        val operators = listOf("+", "-", "*", "/")
        lateinit var mathProblem: String
        const val startCount: Long = 50000
        const val countInterval: Long = 1000
        var remainingTime: Long = 50000
        const val timeAdd: Long = 10000
    }

    private lateinit var b1: TextView
    private lateinit var b2: TextView
    private lateinit var b3: TextView
    lateinit var countTime: TextView
    private lateinit var greatButton: Button
    private lateinit var equalButton: Button
    private lateinit var lessButton: Button
    private lateinit var timerCount: CountDownTimer
                                                                        //assigned components
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        b1 = findViewById(R.id.textView6)
        b2 = findViewById(R.id.textView7)
        b3 = findViewById(R.id.textView9)
        countTime = findViewById(R.id.textView10)                                   //assigned textview

        greatButton = findViewById(R.id.GreaterButton)
        equalButton = findViewById(R.id.EqualButton)
        lessButton = findViewById(R.id.LessButton)
                                                                                        //assigned buttons
        buttonCheck()                                                               //Button click function called
    }
    private fun calculate(x: Int, y: Int, operator: String): Int {                  //Function to calculate expressions

        if (operator == "+") {
            return x + y                                    //adds two parameters

        } else if (operator == "-") {
            return x - y                                    //deducts two parameters

        }else if (operator == "*") {
            return x * y                                    //multiplies two parameters

        }else if (operator == "/") {
            return x / y                                    //divides two parameters

        }
        return 0
    }

    private fun main() {     //One common function for both left and right expressions
        val leftExpressionCount = (1..4).random()    // to check how many random expressions
        val rightExpressionCount = (1..4).random()
        val operatorList = mutableListOf<String>()

        fun left() {          //Function to generate and calculate left expressions
            leftNumList.clear()          //empties array
            operatorList.clear()
            for (i in 0..leftExpressionCount) {

                val randomNumber = (1..20).random()
                leftNumList.add(randomNumber.toString())   //random number generated and added to list
            }

            if (leftNumList.size == 2) {
                operatorList.add(operators.random())    //adds a random operator to a list

                if(operatorList[0] == "/"){
                    while (leftNumList[0].toInt() % leftNumList[1].toInt() != 0) {   //to check if sub expression has no remainder
                        leftNumList[0] = ((1..20).random()).toString()
                        leftNumList[1] = ((1..20).random()).toString()
                    }
                }

                mathProblem = leftNumList[0] + operatorList[0] + leftNumList[1]    //the whole expression is stored to a variable
                leftAnswer =
                    calculate(                        // function called to calculate and find total
                        leftNumList[0].toInt(),
                        leftNumList[1].toInt(),
                        operatorList[0]
                    )

                b1.text = mathProblem              // Question is printed to activity

            } else if (leftNumList.size == 3) {             //when the amount of random numbers is 3
                operatorList.add(operators.random())
                operatorList.add(operators.random())

                if (operatorList[0] == "/") {
                    while (leftNumList[0].toInt() % leftNumList[1].toInt() != 0) {
                        leftNumList[0] = ((1..20).random()).toString()
                        leftNumList[1] = ((1..20).random()).toString()
                    }
                }

                if (operatorList[1] == "/") {
                    if (operatorList[0] == "/") {
                        while (leftNumList[0].toInt() % leftNumList[2].toInt() != 0) {
                            leftNumList[0] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(leftNumList[0].toInt(), leftNumList[1].toInt(),
                                operatorList[0]
                            ) % leftNumList[2].toInt() !=0){
                            leftNumList[0] = ((1..20).random()).toString()
                            leftNumList[1] = ((1..20).random()).toString()
                        }
                    }
                }


                mathProblem =
                    "(" + leftNumList[0] + operatorList[0] + leftNumList[1] + ")" + operatorList[1] + leftNumList[2]
                leftAnswer =
                    calculate(
                        leftNumList[0].toInt(),
                        leftNumList[1].toInt(),
                        operatorList[0]
                    )
                leftAnswer =
                    calculate(leftAnswer, leftNumList[2].toInt(), operatorList[1])

                b1.text = mathProblem

            } else if (leftNumList.size == 4) {                 //when the amount of random numbers is 4
                operatorList.add(operators.random())
                operatorList.add(operators.random())
                operatorList.add(operators.random())

                if (operatorList[0] == "/") {
                    while (leftNumList[0].toInt() % leftNumList[1].toInt() != 0) {
                        leftNumList[0] = ((1..20).random()).toString()
                        leftNumList[1] = ((1..20).random()).toString()
                    }
                }

                if (operatorList[1] == "/") {
                    if (operatorList[0] == "/") {
                        while (leftNumList[0].toInt() % leftNumList[2].toInt() != 0) {
                            leftNumList[0] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(leftNumList[0].toInt(), leftNumList[1].toInt(),
                                operatorList[0]
                            ) % leftNumList[2].toInt() !=0){
                            leftNumList[0] = ((1..20).random()).toString()
                            leftNumList[1] = ((1..20).random()).toString()
                        }
                    }
                }

                if(operatorList[2] == "/"){
                    if (operatorList[0] == "/") {
                        while (leftNumList[0].toInt() % leftNumList[2].toInt() != 0) {
                            leftNumList[0] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(leftNumList[0].toInt(), leftNumList[1].toInt(),
                                operatorList[0]
                            ) % leftNumList[2].toInt() !=0){
                            leftNumList[0] = ((1..20).random()).toString()
                            leftNumList[1] = ((1..20).random()).toString()
                            leftNumList[2] = ((1..20).random()).toString()
                        }
                    }
                    if (operatorList[1] == "/"){
                        while(calculate(calculate(leftNumList[0].toInt(),
                                leftNumList[1].toInt(), operatorList[0]
                            ), leftNumList[2].toInt(), operatorList[1]
                            ) != leftNumList[3].toInt()){
                            leftNumList[0] = ((1..20).random()).toString()
                            leftNumList[1] = ((1..20).random()).toString()
                            leftNumList[2] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(calculate(leftNumList[0].toInt(),
                                leftNumList[1].toInt(), operatorList[0]
                            ), leftNumList[2].toInt(), operatorList[1]
                            ) != leftNumList[3].toInt()){
                            leftNumList[0] = ((1..20).random()).toString()
                            leftNumList[1] = ((1..20).random()).toString()
                            leftNumList[2] = ((1..20).random()).toString()
                        }
                    }
                }


                mathProblem =
                    "(" + "(" + leftNumList[0] + operatorList[0] + leftNumList[1] + ")" + operatorList[1] + leftNumList[2] + ")" + operatorList[2] + leftNumList[3]
                leftAnswer =
                    calculate(
                        leftNumList[0].toInt(),
                        leftNumList[1].toInt(),
                        operatorList[0]
                    )
                leftAnswer =
                    calculate(leftAnswer, leftNumList[2].toInt(), operatorList[1])
                leftAnswer =
                    calculate(leftAnswer, leftNumList[3].toInt(), operatorList[2])

                b1.text = mathProblem

            } else {
                leftNumList[0].toInt()
                mathProblem = leftNumList[0]
                leftAnswer = leftNumList[0].toInt()
                b1.text = mathProblem
            }
        }

        fun right() {                                               //Function to generate and calculate right expressions
            rightNumList.clear()
            operatorList.clear()
            for (i in 0..rightExpressionCount) {

                val randomNumber = (1..20).random()
                rightNumList.add(randomNumber.toString())
            }

            if (rightNumList.size == 2) {
                operatorList.add(operators.random())

                if(operatorList[0] == "/"){
                    while (rightNumList[0].toInt() % rightNumList[1].toInt() != 0) {
                        rightNumList[0] = ((1..20).random()).toString()
                        rightNumList[1] = ((1..20).random()).toString()
                    }
                }

                mathProblem = rightNumList[0] + operatorList[0] + rightNumList[1]
                rightAnswer =
                    calculate(
                        rightNumList[0].toInt(),
                        rightNumList[1].toInt(),
                        operatorList[0]
                    )

                b2.text = mathProblem

            } else if (rightNumList.size == 3) {
                operatorList.add(operators.random())
                operatorList.add(operators.random())

                if (operatorList[0] == "/") {
                    while (rightNumList[0].toInt() % rightNumList[1].toInt() != 0) {
                        rightNumList[0] = ((1..20).random()).toString()
                        rightNumList[1] = ((1..20).random()).toString()
                    }
                }

                if (operatorList[1] == "/") {
                    if (operatorList[0] == "/") {
                        while (rightNumList[0].toInt() % rightNumList[2].toInt() != 0) {
                            rightNumList[0] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(rightNumList[0].toInt(), rightNumList[1].toInt(),
                                operatorList[0]
                            ) % rightNumList[2].toInt() !=0){
                            rightNumList[0] = ((1..20).random()).toString()
                            rightNumList[1] = ((1..20).random()).toString()
                        }
                    }
                }


                mathProblem =
                    "(" + rightNumList[0] + operatorList[0] + rightNumList[1] + ")" + operatorList[1] + rightNumList[2]
                rightAnswer =
                    calculate(
                        rightNumList[0].toInt(),
                        rightNumList[1].toInt(),
                        operatorList[0]
                    )
                rightAnswer =
                    calculate(rightAnswer, rightNumList[2].toInt(), operatorList[1])

                b2.text = mathProblem


            } else if (rightNumList.size == 4) {
                operatorList.add(operators.random())
                operatorList.add(operators.random())
                operatorList.add(operators.random())

                if (operatorList[0] == "/") {
                    while (rightNumList[0].toInt() % rightNumList[1].toInt() != 0) {
                        rightNumList[0] = ((1..20).random()).toString()
                        rightNumList[1] = ((1..20).random()).toString()
                    }

                }

                if (operatorList[1] == "/") {
                    if (operatorList[0] == "/") {
                        while (rightNumList[0].toInt() % rightNumList[2].toInt() != 0) {
                            rightNumList[0] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(rightNumList[0].toInt(), rightNumList[1].toInt(),
                                operatorList[0]
                            ) % rightNumList[2].toInt() !=0){
                            rightNumList[0] = ((1..20).random()).toString()
                            rightNumList[1] = ((1..20).random()).toString()
                        }
                    }
                }

                if(operatorList[2] == "/"){
                    if (operatorList[0] == "/") {
                        while (rightNumList[0].toInt() % rightNumList[2].toInt() != 0) {
                            rightNumList[0] = ((1..20).random()).toString()
                        }
                    }else{
                        while(calculate(rightNumList[0].toInt(), rightNumList[1].toInt(),
                                operatorList[0]
                            ) % rightNumList[2].toInt() !=0){
                            rightNumList[0] = ((1..20).random()).toString()
                            rightNumList[1] = ((1..20).random()).toString()
                            rightNumList[2] = ((1..20).random()).toString()
                        }
                    }
                    if (operatorList[1] == "/"){
                        while(calculate(calculate(rightNumList[0].toInt(),
                                rightNumList[1].toInt(), operatorList[0]
                            ), rightNumList[2].toInt(), operatorList[1]
                            ) != rightNumList[3].toInt()){
                            rightNumList[0] = ((1..20).random()).toString()
                            rightNumList[1] = ((1..20).random()).toString()
                            rightNumList[2] = ((1..20).random()).toString()
                        }

                    }else{
                        while(calculate(calculate(rightNumList[0].toInt(),
                                rightNumList[1].toInt(), operatorList[0]
                            ), rightNumList[2].toInt(), operatorList[1]
                            ) != rightNumList[3].toInt()){
                            rightNumList[0] = ((1..20).random()).toString()
                            rightNumList[1] = ((1..20).random()).toString()
                            rightNumList[2] = ((1..20).random()).toString()
                        }
                    }
                }


                mathProblem =
                    "(" + "(" + rightNumList[0] + operatorList[0] + rightNumList[1] + ")" + operatorList[1] + rightNumList[2] + ")" + operatorList[2] + rightNumList[3]
                rightAnswer =
                    calculate(
                        rightNumList[0].toInt(),
                        rightNumList[1].toInt(),
                        operatorList[0]
                    )
                rightAnswer =
                    calculate(rightAnswer, rightNumList[2].toInt(), operatorList[1])
                rightAnswer =
                    calculate(leftAnswer, rightNumList[3].toInt(), operatorList[2])

                b2.text = mathProblem

            } else {
                rightNumList[0].toInt()
                mathProblem = rightNumList[0]
                rightAnswer = rightNumList[0].toInt()
                b2.text = mathProblem
            }
        }
        left()
        right()                 //left and right functions called to generate expressions
    }

    private val correct = "CORRECT!"            //correct and wrong messages created
    private val wrong = "WRONG!"


    var correctCount = 0
    var wrongCount = 0                          //variables to count correct and wrong answers

    private fun buttonCheck() {                   //function to validate answer and check button click
        main()                                    //common function called to generate expressions
        timer()                                   //timer started

        greatButton.setOnClickListener {                //assigning if great button clicked
            if (leftAnswer > rightAnswer) {             //checking which is greater
                b3.text = correct                       //message stored in correct variable is displayed
                b3.setTextColor(Color.parseColor("#00FF00"))                //Correct Answer color set to green
                correctCount++                          //when correct, correct variable increases
                if(correctCount % 5== 0){
                    addMoreTime()                          //more time function called based on multiples of 5
                }

            } else {
                b3.text = wrong                                               //message stored in wrong variable is displayed
                b3.setTextColor(Color.parseColor("#FF0000"))        //wrong answer color set to red
                wrongCount++
            }
            main()                  //function called to display new question
        }

        equalButton.setOnClickListener {                                //assigning if equal button clicked
            if (leftAnswer == rightAnswer) {                            //checking if both are equal
                b3.text = correct
                b3.setTextColor(Color.parseColor("#00FF00"))
                correctCount++
                if(correctCount % 5== 0){
                    addMoreTime()
                }
            } else {
                b3.text = wrong
                b3.setTextColor(Color.parseColor("#FF0000"))
                wrongCount++
            }
            main()
        }

        lessButton.setOnClickListener {                                        //assigning if equal button clicked
            if (leftAnswer < rightAnswer) {                                     //checking which is greater
                b3.text = correct
                b3.setTextColor(Color.parseColor("#00FF00"))
                correctCount++
                if(correctCount % 5== 0){
                    addMoreTime()
                }
            } else {
                b3.text = wrong
                b3.setTextColor(Color.parseColor("#FF0000"))
                wrongCount++
            }
            main()
        }
    }


    private fun timer(){                                            //function to have timer running
        timerCount = object: CountDownTimer(startCount, countInterval){
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                val timeLeft = millisUntilFinished/1000             //tick set to 1000ms (one second)
                countTime.text = timeLeft.toString()                //timer displayed
            }
            override fun onFinish() {
                countTime.text = "Correct Answers: $correctCount\n Wrong Answers: $wrongCount"
                greatButton.isEnabled = false           //buttons disabled after game ends
                equalButton.isEnabled = false
                lessButton.isEnabled = false
            }
        }.start()
    }
                                                                            //https://www.youtube.com/watch?v=ubhPK84-0og
    private fun addMoreTime(){                                          //function to add time based on correct answers
        timerCount.cancel()
        remainingTime += timeAdd                                            //extra time added
        countTime.text = remainingTime.toString()
        timerCount = object: CountDownTimer(remainingTime, countInterval){
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                val timeLeft = millisUntilFinished/1000
                countTime.text = timeLeft.toString()                                //new timer displayed
            }
            override fun onFinish() {
                countTime.text = "Correct Answers: $correctCount\n Wrong Answers: $wrongCount"          //correct and wrong answers printed when timer ends
                greatButton.isEnabled = false
                equalButton.isEnabled = false
                lessButton.isEnabled = false
            }
        }.start()
    }
}