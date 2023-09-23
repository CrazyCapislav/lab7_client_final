package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat
@JsonTypeName("addIfMax")
@JacksonXmlRootElement(localName = "addIfMax")
class AddIfMax : Command() {
    override val commandName: String = "add_if_max {element}"
    override fun writeString() {
        println("Добавить элемент если id больше максимального")
    }
}