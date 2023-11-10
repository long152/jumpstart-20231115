package com.greenfossil.jumpstart.day2

import java.time.LocalDateTime

case class Task(id: Long, description: String, created: LocalDateTime, completedOn: Option[LocalDateTime]):
  def isCompleted: Boolean = completedOn.nonEmpty
