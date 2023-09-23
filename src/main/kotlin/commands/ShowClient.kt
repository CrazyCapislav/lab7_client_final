package commands


import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat

@JsonTypeName("show")
@JacksonXmlRootElement(localName = "show")
class ShowClient : Command() {

    override val commandName: String = "show"
    override fun writeString() {
        println("Имена элементов коллекции")
    }

}
