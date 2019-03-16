package com.apptimizm.mgs.domain.model.route

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.presentation.model.route.Bug


data class BugModel(
    val name: String? = "",
    val plan: Int? = 0,
    val fact: Int? = 0
)

fun BugModel.mapToPresentation(): Bug = Bug (
    name = this.name,
    plan = this.plan,
    fact = this.fact
)

fun List<BugModel>.mapToPresentation(): List<Bug> = map {
    it.mapToPresentation()
}


fun Resource<List<BugModel>>.mapToDomain(): Resource<List<Bug>> = Resource<List<Bug>>(
    state = state,
    data = data?.mapToPresentation(),
    message = message
)

