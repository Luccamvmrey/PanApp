package com.example.pan.domain.use_cases.classes

import com.example.pan.domain.use_cases.classes.use_classes.AddStudentToClass
import com.example.pan.domain.use_cases.classes.use_classes.AddTeacherToClass
import com.example.pan.domain.use_cases.classes.use_classes.CreateClass
import com.example.pan.domain.use_cases.classes.use_classes.GetClassesList
import com.example.pan.domain.use_cases.classes.use_classes.GetClassesListFromIds

data class PanClassUseCases(
    val createClass: CreateClass,
    val getClassesList: GetClassesList,
    val addStudentToClass: AddStudentToClass,
    val getClassesListFromIds: GetClassesListFromIds,
    val addTeacherToClass: AddTeacherToClass
)
