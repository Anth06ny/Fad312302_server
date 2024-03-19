package org.example.fad312302_server.apirest

import org.example.fad312302_server.model.StudentBean
import org.example.fad312302_server.model.TeacherBean
import org.example.fad312302_server.model.TeacherService
import org.springframework.web.bind.annotation.*

//Classe contenant des méthodes associées à des URL retournant du JSON (APIRest)
@RestController
class MyRestController(val teacherService: TeacherService) {



    //http://localhost:8080/test
    @GetMapping("/test")
    fun testMethode(): String {
        println("/test")

        return "<b>helloWorld</b>"
    }

    /* -------------------------------- */
    // Test bdd
    /* -------------------------------- */

    //http://localhost:8080/addTeacher?name=bobby&code=6
    @GetMapping("/addTeacher")
    fun addTeacher(name:String , code:Int) : List<TeacherBean> {
        println("/addTeacher name=$name code=code")

        teacherService.createTeacher(name, code)
//
//        return  teacherService.getAll()
        return listOf()
    }

    /* -------------------------------- */
    // POST
    /* -------------------------------- */

    //http://localhost:8080/increment
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/increment")
    fun increment (@RequestBody student: StudentBean): StudentBean {
        println("/increment  : $student")
        student.note++

        return student
    }

    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/receiveStudent")
    fun receiveStudent(@RequestBody student: StudentBean) {
        println("/receiveStudent : $student")

        //traitement, mettre en base…
        //Retourner d'autres données
    }

    /* -------------------------------- */
    // GET
    /* -------------------------------- */

    //http://localhost:8080/boulangerie?nbCroissant=5&nbSandwich=7
    @GetMapping("/boulangerie")
    fun boulangerie(nbCroissant: Int = 0, nbSandwich:Int=0): Double {
        //name contiendra bob
        //note contiendra 12
        println("/boulangerie : nbCroissant=$nbCroissant nbSandwich=$nbSandwich")

        return nbCroissant * 0.95 +  nbSandwich* 4
    }

    //http://localhost:8080/getStudent
    @GetMapping("/getStudent")
    fun getStudent(): StudentBean {
        println("/getStudent")

        return StudentBean("toto", 12)
    }

    //http://localhost:8080/createStudent?name=bob&notation=12
    @GetMapping("/createStudent")
    fun createStudent(name: String = "", @RequestParam(value = "notation") note: Int = 0): StudentBean {
        //name contiendra bob
        //note contiendra 12
        println("/createStudent : name=$name note=$note")

        return StudentBean(name, note)
    }

    //http://localhost:8080/max?p1=1&p2=5
    @GetMapping("/max")
    fun max(p1 : Int? = null, p2: Int? = null): Int? {
        println("/max : p1=$p1 p2=$p2")

        if(p1 == null){
            return p2
        }
        if(p2 == null){
            return p1
        }
        return kotlin.math.max(p1, p2)
    }

    //http://localhost:8080/maxV2?p1=1&p2=5
    @GetMapping("/maxV2")
    fun maxV2(p1 : String? = null, p2: String? = null): Int? {
        println("/maxV2 : p1=$p1 p2=$p2")

        val p1Int = p1?.toIntOrNull()
        val p2Int = p2?.toIntOrNull()

        if(p1Int == null){
            return p2Int
        }
        if(p2Int == null){
            return p1Int
        }
        return kotlin.math.max(p1Int, p2Int)
    }




}