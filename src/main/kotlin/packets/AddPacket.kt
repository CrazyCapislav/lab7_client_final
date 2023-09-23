package packets

import commands.AddClient
import models.Flat

data class AddPacket(val addClient: AddClient, val flat: Flat) : PackageData()

