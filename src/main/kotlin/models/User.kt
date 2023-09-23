package models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "user")

data class User(
    @JacksonXmlProperty(localName = "username")
    val username: String,
    @JacksonXmlProperty(localName = "password")
    val password: String)

