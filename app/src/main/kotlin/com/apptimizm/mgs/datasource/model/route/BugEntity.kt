package com.apptimizm.mgs.datasource.model.route

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.domain.model.route.BugModel
import com.squareup.moshi.Json

data class BugEntity(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "plan")
    val plan: Int? = 0,
    @field:Json(name = "fact")
    val fact: Int? = 0
)

fun BugEntity.mapToDomain(): BugModel = BugModel(
    name = this.name,
    plan = this.plan,
    fact = this.fact
)


fun List<BugEntity>.mapToDomain(): List<BugModel> = map {
    it.mapToDomain()
}


fun Resource<List<BugEntity>>.mapToDomain(): Resource<List<BugModel>> = Resource<List<BugModel>>(
    state = state,
    data = data?.mapToDomain(),
    message = message
)

