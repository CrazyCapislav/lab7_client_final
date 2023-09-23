package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat

@JsonTypeName("removeGreater")
@JacksonXmlRootElement(localName = "removeGreater")
class RemoveGreater : Command() {

    override val commandName: String = "remove_lower {element}"
    override fun writeString() {
        println("Удалить id ниже чем")
    }

}