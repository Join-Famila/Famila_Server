package join.famila.club.response

import java.time.LocalDateTime
import join.famila.club.infrastructure.Schedule

class ScheduleResponse(
    val id: Long,

    val clubId: Long,

    val startedAt: LocalDateTime,

    val dues: Long,

    val capacity: Long,

    val location: String,

    val createdAt: LocalDateTime,
) {
    constructor(schedule: Schedule) : this(
        id = schedule.id,
        clubId = schedule.club.id,
        startedAt = schedule.startedAt,
        dues = schedule.dues,
        capacity = schedule.capacity,
        location = schedule.location,
        createdAt = schedule.createdAt,
    )
}
