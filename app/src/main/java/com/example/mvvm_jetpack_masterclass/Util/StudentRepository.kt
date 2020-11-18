package com.example.mvvm_jetpack_masterclass.Util

import kotlinx.coroutines.delay

class StudentRepository {
    suspend fun getStudents():List<StudentModel>
    {
        delay(2000)
        val Users= listOf<StudentModel>(
          StudentModel("Rhea",1,"rhea@gmail.com"),
          StudentModel("Ria",2,"ria@gmail.com"),
          StudentModel("Riya",3,"riya@gmail.com"),
          StudentModel("Priya",4,"priya@gmail.com")
        )
        return Users
    }
}