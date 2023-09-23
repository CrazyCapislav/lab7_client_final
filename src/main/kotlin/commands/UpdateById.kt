package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.*

@JsonTypeName("updateById")
@JacksonXmlRootElement(localName = "updateById")
class UpdateById : Command() {

    override val commandName: String = "update id {element}"
    override fun writeString() {
        println("Изменение элемента")
    }
}