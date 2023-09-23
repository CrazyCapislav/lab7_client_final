package commands
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat
/**
 * Один из классов команд
 */
@JsonTypeName("removeById")
@JacksonXmlRootElement(localName = "removeById")
class RemoveByIdClient : Command() {

    override val commandName: String = "remove_by_id id"
    override fun writeString() {
        println("Удалить по id")
    }
}