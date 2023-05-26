import com.fasterxml.jackson.dataformat.xml.XmlMapper
import commands.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ConnectException
import java.net.Socket

class Client(private val serverHost: String, private val serverPort: Int) {
    private val xmlMapper = XmlMapper()
    private lateinit var socket: Socket
    private lateinit var reader: BufferedReader
    private lateinit var writer: PrintWriter
    fun start() {
        while (true) {
            try {
                connectToServer()

                println("Соединение с сервером установлено.")
                println("Введите команду (help)")

                val userInputReader = BufferedReader(InputStreamReader(System.`in`))

                while (true) {
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

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }
                        "addIfMax" -> {
                            val addIfMax = AddIfMax()
                            val add = AddClient()
                            val flat = add.createElement2()
                            val addXmlString = xmlMapper.writeValueAsString(addIfMax)
                            sendMessage(addXmlString)
                            val flatXmlString = xmlMapper.writeValueAsString(flat)
                            sendMessage(flatXmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }
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
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }
                        "clear" -> {
                            val clear = ClearClient()
                            val xmlString = xmlMapper.writeValueAsString(clear)
                            sendMessage(xmlString)

                            val responseXml = readMessage()
                            val response = parseMessage(responseXml)
                            println("Ответ сервера: ${response.getContent()}")
                        }

                        "someCommand" -> {
                            // Выполнение действий для команды "someCommand"
                            // ...
                        }
                        else -> {
                            // Обработка нераспознанной команды
                            println("Нераспознанная команда.")
                        }
                    }
                }

                println("Работа клиента завершена.")

                disconnectFromServer()
                break
            } catch (e: ConnectException) {
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

    private fun sendMessage(message: String) {
        writer.println(message)
    }

    private fun readMessage(): String {
        return reader.readLine()
    }

    private fun parseMessage(xmlString: String): Message {
        return xmlMapper.readValue(xmlString, Message::class.java)
    }
}


fun main() {
    val serverHost = "localhost"
    val serverPort = 8080
    val clientApplication = Client(serverHost, serverPort)
    clientApplication.start()
}
