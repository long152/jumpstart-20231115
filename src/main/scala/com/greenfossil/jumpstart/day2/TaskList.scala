package com.greenfossil.jumpstart.day2

import java.time.LocalDateTime

object TaskList:

  private var tasks: List[Task] = Nil
  private var taskCounter: Long = 0L

  /**
   * Add task to task list
   * @return Newly created task
   */
  def addTasks(newTasks: String*): Seq[Task] = this.synchronized:
    newTasks.map: task =>
      val newCounter = taskCounter + 1
      val newTask = Task(newCounter, task, LocalDateTime.now, None)
      tasks = tasks :+ newTask
      taskCounter = newCounter
      newTask

  /**
   * Complete task that satisfies `whereFn` predicate
   */
  def completeTask(whereFn: Task => Boolean): Unit = this.synchronized:
    tasks = tasks.map:task =>
      if !whereFn(task) then task
      else task.copy(completedOn = Option(LocalDateTime.now))

  /**
   * Delete task that satisfies `whereFn` predicate
   */
  def deleteTask(whereFn: Task => Boolean): Unit = this.synchronized:
    tasks = tasks.filterNot(whereFn)

  /**
   * Find tasks that satisifies `whereFn` predicate
   */
  def findTasks(whereFn: Task => Boolean): List[Task] = tasks.filter(whereFn)

  /**
   * Find the first tasks that satisfies `whereFn` predicate
   */
  def findTask(whereFn: Task => Boolean): Option[Task] = tasks.find(whereFn)


