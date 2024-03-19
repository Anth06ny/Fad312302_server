package org.example.fad312302_server.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

//A quoi ca ressemble
@Entity
@Table(name = "message")
data class MessageBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var pseudo: String? =null,
    var message: String = "")

//Accés base de donnée
@Repository
interface MessageRepository : JpaRepository<MessageBean, Long> {
    fun findByMessageContainsAndPseudoLike(message:String, pseudo:String)  :List<MessageBean>
}


//Intélligence
@Service
class MessageService(val rep:MessageRepository) {

    fun addMessage(messageBean: MessageBean){
        if(messageBean.message.isNullOrBlank()){
            throw Exception("Message is missing")
        }
        else if(messageBean.pseudo.isNullOrBlank()){
            throw Exception("Pseudo is missing")
        }
        rep.save(messageBean)
    }

    fun getAll() = rep.findAll()

    fun filter(message:String, pseudo:String) = rep.findByMessageContainsAndPseudoLike(message, pseudo)

}

