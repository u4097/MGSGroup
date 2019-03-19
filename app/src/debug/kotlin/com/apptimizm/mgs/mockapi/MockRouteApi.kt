package com.apptimizm.mgs.mockapi

import com.apptimizm.mgs.datasource.model.route.BugEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.networking.RouteApi
import com.apptimizm.mgs.presentation.model.route.Bug
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockRouteApi(mockRetrofit: MockRetrofit) : RouteApi {

    override fun getRouteAsync(page: Int, pageSize: Int): Deferred<Response<RouteResponseEntity>> {
        val response = RouteResponseEntity("1","3","0",mockRoutes)
        return delegate.returningResponse(response).getRouteAsync(1,3)
    }

    private val delegate: BehaviorDelegate<RouteApi> =
        mockRetrofit.create(RouteApi::class.java)


    private val mockRoutes = listOf(
        RouteEntity(
            id = "214124",
            address = ", , п. Рязановское, поворот на Щорса в сторону Ерино",
            bugs = listOf(BugEntity(
                name = "0.8",
                plan = 6,
                fact = 0
            )),
            counterparty = "СНТ №7 ПМЗ",
            costByOne = "1066",
            contractNumber = "3737BM283D970",
            isNightShift = false,
            county = "",
            area = "",
            street = "п. Рязановское, поворот на Щорса в сторону Ерино",
            status = "not_active",
            note = "",
            coordinates = "55.711322, 37.366512",
            contact = "8-903-221-49-79, 8-905-545-73-18",
            talon = false,
            exportOnScheduleDate = "2019-03-16",
            getOutExportTimeStart = "07:00:00",
            getOutExportTimeEnd = "21:00:00",
            factOnExportDatetime = null,
            executor = "f02d49cd-3b8e-45ec-89ce-a14c417536f6",
            schedule = "3999"
        ),
        RouteEntity(
            id = "214125",
            address = ", , д. Мостовское д.2",
            bugs = listOf(BugEntity(
                name = "0.8",
                plan = 3,
                fact = 0
            )),
            counterparty = "Адм. Рязановское 3х0,8",
            costByOne = "1073",
            contractNumber = "3737BM283D977",
            isNightShift = false,
            county = "",
            area = "",
            street = "д. Мостовское д.2",
            status = "not_active",
            note = "",
            coordinates = "55.711322, 37.366519",
            contact = "8-903-221-49-79, 8-905-545-73-18",
            talon = false,
            exportOnScheduleDate = "2019-03-16",
            getOutExportTimeStart = "07:00:00",
            getOutExportTimeEnd = "21:00:00",
            factOnExportDatetime = null,
            executor = "f02d49cd-3b8e-45ec-89ce-a14c417536f6",
            schedule = "3999"
        ),
        RouteEntity(
            id = "214126",
            address = ", , д. Мостовское д.2",
            bugs = listOf(BugEntity(
                name = "8m3",
                plan = 1,
                fact = 0
            )),
            counterparty = "Адм. Рязановское 1х8",
            costByOne = "1074",
            contractNumber = "3737BM283D978",
            isNightShift = false,
            county = "",
            area = "",
            street = "п. Рязановское, поворот на Щорса в сторону Ерино",
            status = "not_active",
            note = "",
            coordinates = "55.711322, 37.366512",
            contact = "8-903-221-49-79, 8-905-545-73-18",
            talon = false,
            exportOnScheduleDate = "2019-03-16",
            getOutExportTimeStart = "07:00:00",
            getOutExportTimeEnd = "21:00:00",
            factOnExportDatetime = null,
            executor = "f02d49cd-3b8e-45ec-89ce-a14c417536f6",
            schedule = "3999"
        ),
        RouteEntity(
            id = "214127",
            address = ", , Фабрика 1-го мая д.52",
            bugs = listOf(BugEntity(
                name = "0.8",
                plan = 8,
                fact = 0
            )),
            counterparty = "СНТ №7 ПМЗ",
            costByOne = "1066",
            contractNumber = "3737BM283D970",
            isNightShift = false,
            county = "",
            area = "",
            street = "п. Рязановское, поворот на Щорса в сторону Ерино",
            status = "not_active",
            note = "",
            coordinates = "55.711322, 37.366512",
            contact = "8-903-221-49-79, 8-905-545-73-18",
            talon = false,
            exportOnScheduleDate = "2019-03-16",
            getOutExportTimeStart = "07:00:00",
            getOutExportTimeEnd = "21:00:00",
            factOnExportDatetime = null,
            executor = "f02d49cd-3b8e-45ec-89ce-a14c417536f6",
            schedule = "3999"
        )
    )

}
