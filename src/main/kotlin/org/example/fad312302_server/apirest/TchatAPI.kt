package org.example.fad312302_server.apirest

import org.example.fad312302_server.model.MessageBean
import org.example.fad312302_server.model.MessageService
import org.example.fad312302_server.model.TeacherService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("tchat")
class TchatAPI(val messageService: MessageService)  {

    // http://localhost:8080/tchat/saveMessage
    // {"pseudo": "toto","message": "coucou" }
    @PostMapping("/saveMessage")
    fun saveMessage(@RequestBody message: MessageBean) {
        println("/saveMessage message=$message")

        messageService.addMessage(message)
    }


    // http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    fun allMessages(): List<MessageBean> {
        println("/allMessages")

        return messageService.getAll()
    }


    // http://localhost:8080/tchat/filter?filter=coucou&pseudo=toto
    @GetMapping("/filter")
    fun filter(filter: String? = null, pseudo: String? = null): List<MessageBean> {
        println("/filter filter=$filter pseudo=$pseudo")

        return messageService.filter(filter ?: "", pseudo ?: "%")

//        return list.filter {
//            //Soit on n'a pas de filtre soit on garde ceux qui correspondent aux filtres
//            (filter == null || it.message.contains(filter))
//                    &&
//                    (pseudo == null || it.pseudo.equals(pseudo, true))
//        }
    }



}

