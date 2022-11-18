package tictactoe

class Game() {
    
    var isCircleWin: Boolean = false
    var isCrossWin: Boolean = false
    var isCoordinate: Boolean = false
    var hasBlank: Boolean = false
    var status: String = "Unknown"
    var player: Char = 'X'
    val regexDigit = Regex("""\d \d""")
    val regexOneThree = Regex("""[1-3]{1} [1-3]{1}""")
    val grid = mutableListOf(
        MutableList(3){ ' ' },
        MutableList(3){ ' ' },
        MutableList(3){ ' ' }
    )
 
    fun printGrid() {
    
        println("---------")
        println("| ${grid[0].joinToString(" ")} |")
        println("| ${grid[1].joinToString(" ")} |")
        println("| ${grid[2].joinToString(" ")} |")
        println("---------")    
        
    }

    fun handleVerticalWin() {
        
        for (i in 0..2) {
            if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                if (grid[0][i] == 'O') {
                    isCircleWin = true    
                } else if (grid[0][i] == 'X') {
                    isCrossWin = true
                }                
            }             
        }        
        
    }

    fun handleHorizontalWin() {
        
        for (i in 0..2) {     
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
                if (grid[i][0] == 'O') {
                    isCircleWin = true    
                } else if (grid[i][0] == 'X') {
                    isCrossWin = true
                }
            }
        }    
        
    }

    fun handleDiagonalWin() {

        for (i in 0..2 step 2) {
            if (i == 0) {
                if (grid[0][i] == grid[1][i + 1] && grid[0][i] == grid[2][i + 2]) {
                    if (grid[0][i] == 'O'){
                        isCircleWin = true  
                    } else if (grid[0][i] == 'X') {
                        isCrossWin = true
                    }
                }
            } else if (i == 2) {
                if (grid[0][i] == grid[1][i - 1] && grid[0][i] == grid[2][i - 2]) {
                    if (grid[0][i] == 'O') {
                        isCircleWin = true
                    } else if (grid[0][i] == 'X') {
                        isCrossWin = true
                    }
                }
            } 
        }
        
    }

    fun checkBlank() {
        
        loopblank@ for (i in 0..2) {
            for (j in 0..2) {
                if (grid[i][j] == ' ') {
                    hasBlank = true
                    break@loopblank
                } else {
                    hasBlank = false
                }
            }
        }
        
    }
    
    fun checkGameStatus() {

        handleVerticalWin()
        handleHorizontalWin()
        handleDiagonalWin()
        checkBlank()

        when {
            isCircleWin == true && isCrossWin == false -> {
                status = "O wins"
                println(status)
            } 
            isCircleWin == false && isCrossWin == true -> {
                status = "X wins"
                println(status)
            }
            isCircleWin == false && isCrossWin == false && hasBlank == false -> {
                status = "Draw"
                println(status)
            } 
        }
        
    }
    
    fun handleInputCoordinates() {
        
        while (true) {
            
            try {          
                val userInputCoordinates: String = readln()
                val (userInputX, userInputY) = userInputCoordinates.split(" ")
                if (!regexDigit.matches(userInputCoordinates)) {
                    println("You should enter numbers!")
                } else if (!regexOneThree.matches(userInputCoordinates)) {
                    println("Coordinates should be from 1 to 3!")
                } else if (grid[userInputX.toInt() - 1][userInputY.toInt() - 1] != ' ') {
                    println("This cell is occupied! Choose another one!")
                } else {
                    grid[userInputX.toInt() - 1][userInputY.toInt() - 1] = player
                    player = 'O'
                    break
                }   
            } catch (e: IndexOutOfBoundsException) {
                println("You should enter numbers!") 
            }

        }        
        
    }
    
}

fun main() {
    
    val game = Game()
    
    game.printGrid()
    
    while (game.status == "Unknown") {
        game.handleInputCoordinates()
        game.printGrid()
        game.checkGameStatus()
    }
             
}
