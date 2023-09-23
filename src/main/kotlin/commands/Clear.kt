package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import models.Flat
import java.io.File
import java.io.FileOutputStream

@JsonTypeName("clear")
@JacksonXmlRootElement(localName = "clear")
class Clear : Command() {

    override val commandName: String = "clear"
    override fun writeString() {
        println("Очистка коллекции")
    }
}