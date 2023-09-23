import com.fasterxml.jackson.dataformat.xml.XmlMapper
import commands.*
import models.User
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.security.MessageDigest

class Client(private val serverHost: String, private val serverPort: Int) {
    private val xmlMapper = XmlMapper()
    private lateinit var socket: Socket
    private lateinit var reader: BufferedReader
    private lateinit var writer: PrintWriter
    private lateinit var user: User
    fun start() {
        while (true) {
            try {
                connectToServer()
                println("Соединение с сервером установлено.")


                val userInputReader = BufferedReader(InputStreamReader(System.`in`))
                while (true) {
                    println("Введите '1' для авторизации, '2' для регистрации или 'exit' для выхода:")
                    val choice = userInputReader.readLine()

                    when (choice) {
                        "1" -> {
                            val auto = Auto()
                            println("Введине логин")
                            val login = userInputReader.readLine()
                            println("Введите пароль")
                            val password = userInputReader.readLine()
                            val hash = hashPassword(password)
                            auto.execute(login, hash)
                            val xmlString = xmlMapper.writeValueAsString(auto)
                            sendMessage(xmlString)
                            val userXmlString = xmlMapper.writeValueAsString(User(login, hash))
                            sendMessage(userXmlString)
                            val responseXml = readMessage()
                            val response = parseMessage(responseXml).getContent()

                            if (response == "true" ) {
                                println("Авторизация успешна.")
                                user = User(login, hash)
                                break
                            } else {
                                println("Ошибка авторизации. Попробуйте еще раз.")
                            }
                        }
                        "2" -> {
                            val reg = Register()
                            println("Введине логин")
                            val login = userInputReader.readLine()
                            println("Введите пароль")
                            val password = userInputReader.readLine()
                            val hash = hashPassword(password)
                            reg.execute(login, hash)
                            val xmlString = xmlMapper.writeValueAsString(reg)
                            sendMessage(xmlString)
                            val userXmlString = xmlMapper.writeValueAsString(User(login, hash))
                            sendMessage(userXmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml).getContent()
                            println(response)
                            if (response == "true" ) {
                                println("Регистрация успешна.")
                                user = User(login, hash)
                                break
                            } else {
                                println("Ошибка регистрации. Попробуйте еще раз.")
                            }
                        }
                        "exit" -> {
                            disconnectFromServer()
                            println("Работа клиента завершена.")
                            break
                        }
                        else -> {
                            println("Неверный выбор. Попробуйте еще раз.")
                        }
                    }
                }

                while (true) {
                    println("Введите команду (help)")
                    val userInput = userInputReader.readLine()
                    val parts = userInput.split(" ")

                    when (parts.firstOrNull()) {
                        "exit" -> break
                        "help" -> {

                            val help = HelpClient()
                            val xmlString = xmlMapper.writeValueAsString(help)
                            sendMessage(xmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }
                        "print" -> {
                            val print = PrintUniqueTimeToMetroOnFoot()
                            val xmlString = xmlMapper.writeValueAsString(print)
                            sendMessage(xmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }
                        "metro" -> {

                            val metro = AverageOfTimeToMetroOnFoot()
                            val xmlString = xmlMapper.writeValueAsString(metro)
                            sendMessage(xmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }
                        "add" -> {

                            val add = AddClient()
                            val flat = add.createElement2()
                            val addXmlString = xmlMapper.writeValueAsString(add)
                            sendMessage(addXmlString)

                            val flatXmlString = xmlMapper.writeValueAsString(flat)
                            sendMessage(flatXmlString)

                            val userXmlString = xmlMapper.writeValueAsString(user)
                            sendMessage(userXmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml).getContent()

                            printR(response)
                        }
//                        "addIfMax" -> {
//
//                            val addIfMax = AddIfMax()
//                            val add = AddClient()
//                            val flat = add.createElement2()
//                            val addXmlString = xmlMapper.writeValueAsString(addIfMax)
//                            sendMessage(addXmlString)
//                            val flatXmlString = xmlMapper.writeValueAsString(flat)
//                            sendMessage(flatXmlString)
//                            val userXmlString = xmlMapper.writeValueAsString(user)
//                            sendMessage(userXmlString)
//                            val responseXml = readMessage()
//                            val response = parseMessage(responseXml)
//                            println("Ответ сервера: ${response.getContent()}")
//                        }
                        "updateId" -> {

                            val id = parts.getOrNull(1)
                            if (id != null) {
                                val updateById = UpdateById()
                                val xmlString = xmlMapper.writeValueAsString(updateById)
                                sendMessage(xmlString)
                                val xmlStringId = xmlMapper.writeValueAsString(id)
                                sendMessage(xmlStringId)
                                val add = AddClient()
                                val flat = add.createElement2()
                                val flatXmlString = xmlMapper.writeValueAsString(flat)
                                sendMessage(flatXmlString)

                                val userXmlString = xmlMapper.writeValueAsString(user)
                                sendMessage(userXmlString)

                                val responseXml = readMessage()
                                val response = parseMessage(responseXml)
                                println("Ответ сервера: ${response.getContent()}")
                            } else {
                                println("Некорректный формат команды.")
                            }
                        }
                        "removeById" -> {
                            val id = parts.getOrNull(1)
                            if (id != null) {
                                val removeById = RemoveByIdClient()
                                val xmlString = xmlMapper.writeValueAsString(removeById)
                                sendMessage(xmlString)
                                val xmlStringId = xmlMapper.writeValueAsString(id)
                                sendMessage(xmlStringId)

                                val userXmlString = xmlMapper.writeValueAsString(user)
                                sendMessage(userXmlString)

                                val responseXml = readMessage()
                                val response = parseMessage(responseXml)
                                println("Ответ сервера: ${response.getContent()}")
                            } else {
                                println("Некорректный формат команды.")
                            }
                        }
                        "NOR" -> {
                            val NOR = parts.getOrNull(1)
                            if (NOR != null) {
                                val removeAllByNumberOfRooms = RemoveAllByNumberOfRooms()
                                val xmlString = xmlMapper.writeValueAsString(removeAllByNumberOfRooms)
                                sendMessage(xmlString)
                                val xmlStringId = xmlMapper.writeValueAsString(NOR)
                                sendMessage(xmlStringId)

                                val userXmlString = xmlMapper.writeValueAsString(user)
                                sendMessage(userXmlString)

                                val responseXml = readMessage()
                                val response = parseMessage(responseXml)
                                println("Ответ сервера: ${response.getContent()}")
                            } else {
                                println("Некорректный формат команды.")
                            }
                        }
                        "removeLower" -> {

                            val id = parts.getOrNull(1)
                            if (id != null) {
                                val removeLower = RemoveLower()
                                val xmlString = xmlMapper.writeValueAsString(removeLower)
                                sendMessage(xmlString)
                                val xmlStringId = xmlMapper.writeValueAsString(id)
                                sendMessage(xmlStringId)
                                val userXmlString = xmlMapper.writeValueAsString(user)
                                sendMessage(userXmlString)

                                val responseXml = readMessage()
                                val response = parseMessage(responseXml)
                                println("Ответ сервера: ${response.getContent()}")
                            } else {
                                println("Некорректный формат команды.")
                            }
                        }
                        "removeGreater" -> {

                            val id = parts.getOrNull(1)
                            if (id != null) {
                                val removeGreater = RemoveGreater()
                                val xmlString = xmlMapper.writeValueAsString(removeGreater)
                                sendMessage(xmlString)
                                val xmlStringId = xmlMapper.writeValueAsString(id)
                                sendMessage(xmlStringId)
                                val userXmlString = xmlMapper.writeValueAsString(user)
                                sendMessage(userXmlString)

                                val responseXml = readMessage()
                                val response = parseMessage(responseXml)
                                println("Ответ сервера: ${response.getContent()}")
                            } else {
                                println("Некорректный формат команды.")
                            }
                        }
                        "show" -> {

                            val show = ShowClient()
                            val xmlString = xmlMapper.writeValueAsString(show)
                            sendMessage(xmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml).getContent()
                            printR(response)
                        }
                        "clear" -> {
                            val clear = Clear()
                            val xmlString = xmlMapper.writeValueAsString(clear)
                            sendMessage(xmlString)

                            val userXmlString = xmlMapper.writeValueAsString(user)
                            sendMessage(userXmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }

                        else -> {
                            println("Нераспознанная команда.")
                        }
                    }
                }

                println("Работа клиента завершена.")

                disconnectFromServer()
                break
            } catch (e: Exception) {
                println("Не удалось подключиться к серверу. Повторная попытка через 5 секунд...")
                Thread.sleep(5000)
            }
        }
    }

    private fun connectToServer() {
        socket = Socket(serverHost, serverPort)
        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        writer = PrintWriter(socket.getOutputStream(), true)
    }

    private fun disconnectFromServer() {
        reader.close()
        writer.close()
        socket.close()
    }
    private fun hashPassword(password: String): String {
        val sha512 = MessageDigest.getInstance("SHA-512")
        val bytes = password.toByteArray()
        val digest = sha512.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }

    private fun sendMessage(message: String) {
        writer.println(message)
    }

    private fun readMessage(): String {
        return reader.readLine()
    }

    private fun parseMessage(xmlString: String): Message {
        return xmlMapper.readValue(xmlString, Message::class.java)
    }
    private fun printR(response: String) {
        val modifiedResponse = response.replace("|||", "\n")
        println("Ответ сервера:\n$modifiedResponse")
    }
}



fun main() {
    val serverHost = "localhost"
    val serverPort = 8080
    val clientApplication = Client(serverHost, serverPort)
    clientApplication.start()
}
