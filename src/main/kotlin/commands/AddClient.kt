package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Coordinates
import models.Flat
import models.Furnish
import models.House
import java.sql.Timestamp

@JacksonXmlRootElement(localName = "Add")
@JsonTypeName("add")
class AddClient : Command() {

    override val commandName: String = "add {element}"

    override fun writeString() {
        println("Введите имя элемента")
    }

    fun start(hashSet: HashSet<Flat>): HashSet<Flat> {
        addElement(hashSet, createElement())
        return hashSet
    }

    private fun createElement(): Flat {

        val id: Long = 0
        println("Введите имя")
        var read: String = readLine().toString()
        val name: String = nullBlankCheck(read)
        println("Введите координаты (x)")
        val x = getLongInput()
        println("Введите координаты (y)")
        read = getLongInput().toString()
        while (read.toLong() <= -566) {
            println("Должно быть больше -566!")
            read = getLongInput().toString()
        }
        val y = read.toLong()
        println("Введите площадь")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val area = read.toInt()
        println("Введите количество комнат")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val numberOfRooms = read.toInt()
        //
        println("Введите жилую площадь (десятичная дробь)")
        read = getDoubleInput().toString()
        while (read.toDouble() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val livingSpace = read.toDouble()
        //
        println("Введите расстояние до метро в футах")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val timeToMetroOnFoot = read.toInt()
        println("Нужен ли мебель?(2/1)")
        read = readLine().toString()
        val furnishFlag: Boolean
        while (true) {
            when (read) {
                "2" -> {
                    furnishFlag = true; break
                }

                "1" -> {
                    furnishFlag = false; break
                }

                else -> {
                    println("Неправильное значение введите еще раз!(2/1)"); read = readLine().toString()
                }
            }
        }
        val furnish: Furnish?
        if (furnishFlag) {
            println("Введите наличие мебели (FINE, BAD, LITTLE)")
            read = readLine().toString()
            while (true) {
                read = when (read) {
                    "BAD" -> {
                        break
                    }

                    "FINE" -> {
                        break
                    }

                    "LITTLE" -> {
                        break
                    }

                    else -> {
                        println("Неправильное значение введите еще раз!(FINE, BAD, LITTLE"); readLine().toString()
                    }

                }
            }
            furnish = createFurnish(read)
        } else {
            furnish = null
        }
        println("Нужен ли дом?(2/1)")
        read = readLine().toString()
        val houseFlag: Boolean
        while (true) {
            when (read) {
                "2" -> {
                    houseFlag = true; break
                }

                "1" -> {
                    houseFlag = false; break
                }

                else -> {
                    println("Неправильное значение введите еще раз!(Да/Нет)"); read = readLine().toString()
                }
            }
        }
        val house: House?
        if (houseFlag) {

            println("Введите параметры дома (название)")

            val houseName = readLine().toString()

            println("Введите параметры дома (год)")

            val houseYear = getLongInput()

            println("Введите параметры дома (количество лифтов)")

            val houseNumberOfLifts = getLongInput()
            house = createHouse(houseName, houseYear, houseNumberOfLifts)
        } else {
            house = null
        }
        return Flat(
            id,
            name,
            createCoordinates(x, y),
            area,
            numberOfRooms,
            livingSpace,
            timeToMetroOnFoot,
            furnish,
            house
        )
    }

    fun createElement2(): Flat {
//        println("Введите id")
        val id: Long = 0
        println("Введите имя")
        var read: String = readLine().toString()
        val name: String = nullBlankCheck(read)
        println("Введите координаты (x)")
        val x = getLongInput()
        println("Введите координаты (y)")
        read = getLongInput().toString()
        while (read.toLong() <= -566) {
            println("Должно быть больше -566!")
            read = getLongInput().toString()
        }
        val y = read.toLong()
        println("Введите площадь")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val area = read.toInt()
        println("Введите количество комнат")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val numberOfRooms = read.toInt()
        //
        println("Введите жилую площадь (десятичная дробь)")
        read = getDoubleInput().toString()
        while (read.toDouble() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val livingSpace = read.toDouble()
        //
        println("Введите расстояние до метро в футах")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val timeToMetroOnFoot = read.toInt()
        println("Нужна ли мебель?(2/1)")
        read = readLine().toString()
        val furnishFlag: Boolean
        while (true) {
            when (read) {
                "2" -> {
                    furnishFlag = true; break
                }

                "1" -> {
                    furnishFlag = false; break
                }

                else -> {
                    println("Неправильное значение введите еще раз!(2/1)"); read = readLine().toString()
                }
            }
        }
        val furnish: Furnish?
        if (furnishFlag) {
            println("Введите наличие мебели (FINE, BAD, LITTLE)")
            read = readLine().toString()
            while (true) {
                read = when (read) {
                    "BAD" -> {
                        break
                    }

                    "FINE" -> {
                        break
                    }

                    "LITTLE" -> {
                        break
                    }

                    else -> {
                        println("Неправильное значение введите еще раз!(FINE, BAD, LITTLE"); readLine().toString()
                    }

                }
            }
            furnish = createFurnish(read)
        } else {
            furnish = null
        }
        println("Нужен ли дом?(2/1)")
        read = readLine().toString()
        val houseFlag: Boolean
        while (true) {
            when (read) {
                "2" -> {
                    houseFlag = true; break
                }

                "1" -> {
                    houseFlag = false; break
                }

                else -> {
                    println("Неправильное значение введите еще раз!(Да/Нет)"); read = readLine().toString()
                }
            }
        }
        val house: House?
        if (houseFlag) {

            println("Введите параметры дома (название)")

            val houseName = readLine().toString()

            println("Введите параметры дома (год)")

            val houseYear = getLongInput()

            println("Введите параметры дома (количество лифтов)")

            val houseNumberOfLifts = getLongInput()
            house = createHouse(houseName, houseYear, houseNumberOfLifts)
        } else {
            house = null
        }
        val creationDate = Timestamp(System.currentTimeMillis())
        val username = ""
        return Flat(
            id,
            name,
            createCoordinates(x, y),
            area,
            numberOfRooms,
            livingSpace,
            timeToMetroOnFoot,
            furnish,
            house,
            creationDate,
            username
        )
    }
}

private fun nullBlankCheck(string: String): String {
    var string1 = string
    while (string1.isBlank()) {
        println("Пустая строка! Введите значение")
        string1 = readLine().toString()
    }
    return string1
}

fun createFurnish(string: String): Furnish {
    return when (string) {
        "FINE" -> Furnish.FINE
        "BAD" -> Furnish.BAD
        "LITTLE" -> Furnish.LITTLE
        else -> {
            println("WTF"); Furnish.BAD
        }
    }
}

private fun createCoordinates(x: Long, y: Long): Coordinates {
    return Coordinates(x, y)
}


fun createHouse(name: String, year: Long, numberOfLifts: Long): House {
    return House(name, year, numberOfLifts)
}


private fun addElement(hashSet: HashSet<Flat>, element: Flat) {
    hashSet.add(element)
}


fun getLongInput(): Long {
    while (true) {
        try {
            val input = readLine()!!
            return input.toLong()
        } catch (e: NumberFormatException) {
            println("Неверный формат числа, попробуйте еще раз")
        }
    }
}

fun getIntInput(): Int {
    while (true) {
        try {
            val input = readLine()!!
            return input.toInt()
        } catch (e: NumberFormatException) {
            println("Неверный формат числа, попробуйте еще раз")
        }
    }
}


fun getDoubleInput(): Double {
    while (true) {
        try {
            val input = readLine()!!
            return input.toDouble()
        } catch (e: NumberFormatException) {
            println("Неверный формат числа, попробуйте еще раз")
        }
    }
}
